package com.nsyun.server.services.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nsyun.server.domain.common.OauthClientDetails;
import com.nsyun.server.repository.common.OauthClientDetailsRepository;

@Service("oauthClientDetailsService")
public class OauthClientDetailsService implements ClientDetailsService{

	 private final Logger log = LoggerFactory.getLogger(OauthClientDetailsService.class);

    @Autowired
    private OauthClientDetailsRepository oauthClientDetailsRepository;
    
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException
    {
    	OauthClientDetails oauthClientDetails = oauthClientDetailsRepository.findOne(clientId);
    	log.info("Load client {}", clientId);
    	BaseClientDetails details = new BaseClientDetails();
    	details.setClientId(oauthClientDetails.getClientId());
    	details.setAuthorizedGrantTypes(Arrays.asList(oauthClientDetails.getAuthorizedGrantTypes().split(",")));
    	details.setScope(Arrays.asList(oauthClientDetails.getScope().split(",")));
    	details.setAutoApproveScopes(Arrays.asList("trust"));
    	details.setRefreshTokenValiditySeconds(oauthClientDetails.getRefreshTokenValidity());
    	details.setAccessTokenValiditySeconds(oauthClientDetails.getAccessTokenValidity());
    	
    	if(!StringUtils.isEmpty(oauthClientDetails.getAuthorities())){
    		List<SimpleGrantedAuthority> authSet = new ArrayList<SimpleGrantedAuthority>();
    		
    		List<String> arrList = Arrays.asList(oauthClientDetails.getAuthorities().split(","));
    		for(int i =0; i<arrList.size();i++){
    			SimpleGrantedAuthority cur = new SimpleGrantedAuthority(arrList.get(i));
    			authSet.add(cur);
    		}
    		details.setAuthorities(authSet);
    	}
    	
    	details.setResourceIds(Arrays.asList(oauthClientDetails.getResourceIds().split(",")));
    	
    	details.setClientSecret(oauthClientDetails.getClientSecret());
  
    	return details;
    }
	    	    
}
