package com.nsyun.server.domain.users;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * ROLE_USER:普通用户
 * ROLE_MANGER：普通管理员
 * ROLE_SUPER：高级管理员
 * ROLE_ADMIN ：超级用户
 * 
 * @ClassName: Authority
 * @Description: 角色表
 * @author: xlh
 * @date: 2016年5月29日
 */
@Entity
@Table(name="authority")
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@NotEmpty
	private String name;

//	@JsonIgnore
//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
//	private Set<User> users = new HashSet<User>();

	@Override
	public String getAuthority() {
		return name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Set<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}

}
