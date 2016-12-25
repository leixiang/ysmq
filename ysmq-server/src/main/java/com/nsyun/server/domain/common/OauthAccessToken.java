package com.nsyun.server.domain.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.authentication.UserCredentials;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;


/**
 * The persistent class for the oauth_access_token database table.
 * 
 */
@Entity
@Table(name="oauth_access_token")
public class OauthAccessToken implements Serializable {

	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	private byte[] authentication;
	
	@Lob
	@Column(name="oauth2_request")
	private byte[] oauth2Request;

	@Column(name="authentication_id")
	private String authenticationId;

	@Column(name="client_id")
	private String clientId;

	@Column(name="refresh_token")
	private String refreshToken;
	
	@Lob
	private byte[] token;

	@Column(name="token_id")
	private String tokenId;

	@Column(name="user_name")
	private String userName;
	
	
	@Transient
	private UserRepositoryUserDetails user;

	public OauthAccessToken() {
	}
	
	public OauthAccessToken(final OAuth2AccessToken oAuth2AccessToken,
		      final OAuth2Authentication authentication, final String authenticationId, String jti,
		      String refreshJTI) {
		    this.tokenId = jti;
		    this.token = SerializationUtils.serialize(oAuth2AccessToken);
		    this.authenticationId = authenticationId;
		    this.userName = authentication.getName();
		    this.oauth2Request = SerializationUtils
		        .serialize(SerializationUtils.serialize(authentication.getOAuth2Request()));
		    this.clientId = authentication.getOAuth2Request().getClientId();
		    this.authentication = SerializationUtils.serialize(authentication);
		    this.refreshToken = refreshJTI;
		    this.user = authentication.getUserAuthentication() != null
		        ? (UserRepositoryUserDetails) authentication.getUserAuthentication().getPrincipal() : null;
		  }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public byte[] getAuthentication() {
		return this.authentication;
	}

	public void setAuthentication(byte[] authentication) {
		this.authentication = authentication;
	}

	public String getAuthenticationId() {
		return this.authenticationId;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getRefreshToken() {
		return this.refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public byte[] getToken() {
		return this.token;
	}

	public void setToken(byte[] token) {
		this.token = token;
	}

	public String getTokenId() {
		return this.tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserRepositoryUserDetails getUser() {
		return user;
	}

	public void setUser(UserRepositoryUserDetails user) {
		this.user = user;
	}
}