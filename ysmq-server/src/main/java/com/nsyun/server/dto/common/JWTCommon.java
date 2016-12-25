package com.nsyun.server.dto.common;

import java.io.Serializable;
import java.util.List;

import com.nsyun.server.domain.common.UserRepositoryUserDetails;

/**
 * store DTO
 * @author apple
 *
 */
public class JWTCommon implements  Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long exp;
	private String jti;
	private String client_id;
	private List<String> authorities;
	private List<String> scope;
	private String user_name;
	private UserRepositoryUserDetails user;
	
	public Long getExp() {
		return exp;
	}
	public void setExp(Long exp) {
		this.exp = exp;
	}
	public String getJti() {
		return jti;
	}
	public void setJti(String jti) {
		this.jti = jti;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public List<String> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	public List<String> getScope() {
		return scope;
	}
	public void setScope(List<String> scope) {
		this.scope = scope;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public UserRepositoryUserDetails getUser() {
		return user;
	}
	public void setUser(UserRepositoryUserDetails user) {
		this.user = user;
	}
}
