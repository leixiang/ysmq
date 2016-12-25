package com.nsyun.server.domain.common;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the oauth_client_details database table.
 * 
 */
@Entity
@Table(name="oauth_client_details")
public class OauthClientDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="access_token_validity")
	private int accessTokenValidity;

	@Lob
	@Column(name="additional_information", length=255)
	private String additionalInformation;

	private byte archived;
	@Column(length=100)
	private String authorities;

	@Column(name="authorized_grant_types", length=100)
	private String authorizedGrantTypes;

	@Column(length=100)
	private String autoapprove;

	@Id
	@Column(name="client_id",length=100)
	private String clientId;

	@Column(name="client_secret",length=60)
	private String clientSecret;

	@Column(name="create_time")
	private Timestamp createTime;

	@Column(name="refresh_token_validity")
	private int refreshTokenValidity;

	@Column(name="resource_ids",length=60)
	private String resourceIds;

	@Column(name="resources_ids",length=60)
	private String resourcesIds;

	@Column(length=60)
	private String scope;

	private byte trusted;

	@Column(name="web_server_redirect_uri", length=500)
	private String webServerRedirectUri;

	public OauthClientDetails() {
	}

	public int getAccessTokenValidity() {
		return this.accessTokenValidity;
	}

	public void setAccessTokenValidity(int accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public String getAdditionalInformation() {
		return this.additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public byte getArchived() {
		return this.archived;
	}

	public void setArchived(byte archived) {
		this.archived = archived;
	}

	public String getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public String getAuthorizedGrantTypes() {
		return this.authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getAutoapprove() {
		return this.autoapprove;
	}

	public void setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
	}

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return this.clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getRefreshTokenValidity() {
		return this.refreshTokenValidity;
	}

	public void setRefreshTokenValidity(int refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getResourceIds() {
		return this.resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getResourcesIds() {
		return this.resourcesIds;
	}

	public void setResourcesIds(String resourcesIds) {
		this.resourcesIds = resourcesIds;
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public byte getTrusted() {
		return this.trusted;
	}

	public void setTrusted(byte trusted) {
		this.trusted = trusted;
	}

	public String getWebServerRedirectUri() {
		return this.webServerRedirectUri;
	}

	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}

}