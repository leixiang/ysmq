package com.nsyun.server.domain.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;


/**
 * The persistent class for the oauth_refresh_token database table.
 * 
 */
@Entity
@Table(name="oauth_refresh_token")
public class OauthRefreshToken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Lob
	private byte[] authentication;

	@Lob
	private byte[] token;

	@Column(name="token_id")
	private String tokenId;

	public OauthRefreshToken() {
	}

	public OauthRefreshToken(OAuth2RefreshToken oAuth2RefreshToken,
		      OAuth2Authentication authentication, String jti) {
		    this.token = SerializationUtils.serialize((Serializable) oAuth2RefreshToken);
		    this.authentication = SerializationUtils.serialize((Serializable) authentication);
		    this.tokenId = jti;
		  }
	
	public Long getId() {
		return this.id;
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

}