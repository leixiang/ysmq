package com.nsyun.server;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.nsyun.server.config.CustomJwtTokenConverter;
import com.nsyun.server.services.common.CustomUserDetailsService;
import com.nsyun.server.services.common.JWTDataTokenStore;

@SpringBootApplication
public class AuthorizationServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

	@Configuration
	@EnableAuthorizationServer
	public static class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

		@Autowired
		private AuthenticationManager authenticationManager;

		@Autowired
		private CustomUserDetailsService userDetailsService;

		@Autowired
		@Qualifier("oauthClientDetailsService")
		private ClientDetailsService clientDetailsService;

		@Bean
		@Qualifier("tokenStore")
		public TokenStore tokenStore() {
			return new JWTDataTokenStore(jwtTokenEnhancer());
		}


		@Bean
		protected JwtAccessTokenConverter jwtTokenEnhancer() {
			return new CustomJwtTokenConverter();
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
			configurer.tokenStore(tokenStore()).tokenEnhancer(jwtTokenEnhancer());
			configurer.authenticationManager(authenticationManager).userDetailsService(userDetailsService);

		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.withClientDetails(clientDetailsService);
		}


		
		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
			oauthServer.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')").checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
			//oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
		}
	

	}

	@Configuration
	@EnableResourceServer
	public static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			
			http.requestMatcher(new OAuthRequestedMatcher() ).antMatcher("/secure/**")
			.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
			.anyRequest().permitAll();
			
		}

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.resourceId("resource");
		}
		
		private static class OAuthRequestedMatcher implements RequestMatcher{

			@Override
			public boolean matches(HttpServletRequest request) {
				// TODO Auto-generated method stub
				String auth = request.getHeader("Authorization");
				boolean haveOauth2Token = (auth != null) && auth.startsWith("Basic");
				boolean havaAccessToken = request.getParameter("access_token")!=null;
				return haveOauth2Token || havaAccessToken;
	
			}
		}
	}
	
	
	@Configuration
	@EnableWebSecurity
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Autowired
		private CustomUserDetailsService userDetailsService;

		/*
		@Autowired
		private Md5PasswordEncoder md5PasswordEncoder;
		*/
        
		
		@Autowired
		public void configure(AuthenticationManagerBuilder auth) throws Exception
		{
			//DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
			//authProvider.setPasswordEncoder(md5PasswordEncoder);
			//authProvider.setUserDetailsService(userDetailsService);
			//ReflectionSaltSource saltSource = new ReflectionSaltSource();
			//saltSource.setUserPropertyToUse("salt");
			auth.userDetailsService(userDetailsService);
			//auth.authenticationProvider(authProvider);
		}
		
		//@Autowired
		//public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			//DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
			//authProvider.setPasswordEncoder(md5PasswordEncoder);
			//authProvider.setUserDetailsService(userDetailsService);
			//ReflectionSaltSource saltSource = new ReflectionSaltSource();
			//saltSource.setUserPropertyToUse("salt");
			//authProvider.setSaltSource(saltSource);
			//auth.parentAuthenticationManager(authenticationManagerBean());
			//auth.authenticationProvider(authProvider);

		//}
		
		

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			/*http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
			.anyRequest().authenticated()
			.and().httpBasic()
			.and().csrf().disable();*/
			
			http
			 .authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
			 .antMatchers("/secure/**").authenticated()
			 .anyRequest().permitAll()
			 .and().httpBasic()
			 .and().csrf().disable();
			
		}

	}

	
}