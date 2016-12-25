package com.nsyun.server.config;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.nsyun.server.domain.common.UserRepositoryUserDetails;
import com.nsyun.server.domain.users.Authority;

public class CustomJwtTokenConverter extends JwtAccessTokenConverter { 

 
 	public CustomJwtTokenConverter() {
		super();
		/*KeyPair keyPair = new KeyStoreKeyFactory(
				new ClassPathResource("keystore.jks"), "foobar".toCharArray())
				.getKeyPair("test");
		this.setKeyPair(keyPair);*/
	}

	@Override 
 	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) { 
 		Map<String, Object> info = new LinkedHashMap<String,Object>(accessToken.getAdditionalInformation()); 
 
 		info.put("user_id", ((UserRepositoryUserDetails) authentication.getPrincipal()).getId()); 
 		info.put("username", ((UserRepositoryUserDetails) authentication.getPrincipal()).getUsername());
 		info.put("login_time", System.currentTimeMillis());
 		
 		
 		
 		DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken); 
 		customAccessToken.setAdditionalInformation(info); 
 		return super.enhance(customAccessToken, authentication); 
 	} 
 } 

