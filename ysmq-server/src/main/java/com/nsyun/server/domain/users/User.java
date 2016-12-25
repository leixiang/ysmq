package com.nsyun.server.domain.users;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user")
public class User implements Serializable {
	
	public User(Long id, String username, String password, String nickName, String email, String mobile,
			boolean activated, String activationKey, String resetPasswordKey, Long salt, String referralsMobile,
			String headerImgUrl, Integer sex, String city, Set<Authority> roles, Date createdDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickName = nickName;
		this.email = email;
		this.mobile = mobile;
		this.activated = activated;
		this.activationKey = activationKey;
		this.resetPasswordKey = resetPasswordKey;
		this.salt = salt;
		this.referralsMobile = referralsMobile;
		this.headerImgUrl = headerImgUrl;
		this.sex = sex;
		this.city = city;
		this.roles = roles;
		this.createdDate = createdDate;
	}

	public User(User user)
	{
		this.id = user.id;
		this.username = user.username;
		this.password = user.password;
		this.nickName = user.nickName;
		this.email = user.email;
		this.mobile = user.mobile;
		this.activated = user.activated;
		this.activationKey = user.activationKey;
		this.resetPasswordKey = user.resetPasswordKey;
		this.salt = user.salt;
		this.referralsMobile = user.referralsMobile;
		this.headerImgUrl = user.headerImgUrl;
		this.sex = user.sex;
		this.city = user.city;
		this.roles = user.roles;
		this.createdDate = user.createdDate;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", length = 50)
	private String username;

	@Column(name = "password", length = 500)
	private String password;

	@Column(name = "nick_name", length = 255)
	private String nickName;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "mobile", length = 255)
	private String mobile;

	@Column(name = "activated", length = 1)
	private boolean activated = true;

	@Column(name = "activationkey", length = 100)
	private String activationKey;

	@Column(name = "resetpasswordkey", length = 100)
	private String resetPasswordKey;

	@Column(name = "salt", length = 20)
	private Long salt;

	@Column(name = "referrals_mobile", length = 255)
	private String referralsMobile; // 介绍人手机号码
	
	//用户头像图片地址
	@Column(name = "header_img_url", length = 255)
	private String headerImgUrl;
	
	//性别 1 男 2 女 3 保密
	@Column(name = "sex", length = 2)
	private Integer sex;
	
	//性别 1 男 2 女 3 保密
	@Column(name = "city", length = 20)
	private String city;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinTable(name = "user_authority", joinColumns = {@javax.persistence.JoinColumn(name = "user_id") }, inverseJoinColumns = { @javax.persistence.JoinColumn(name = "role_id") })
	private Set<Authority> roles;
	
	private Date createdDate;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getActivated() {
		return this.activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getActivationKey() {
		return this.activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public String getResetPasswordKey() {
		return this.resetPasswordKey;
	}

	public void setResetPasswordKey(String resetPasswordKey) {
		this.resetPasswordKey = resetPasswordKey;
	}

	public Set<Authority> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Authority> authorities) {
		this.roles = authorities;
	}

	public Long getSalt() {
		return this.salt;
	}

	public void setSalt(Long salt) {
		this.salt = salt;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	

	public String getReferralsMobile() {
		return referralsMobile;
	}

	public void setReferralsMobile(String referralsMobile) {
		this.referralsMobile = referralsMobile;
	}


	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	
	@Transient
	public String getCreatedDateStr() {
		if(null != this.getCreatedDate()) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.format(this.getCreatedDate());
		}
		return "";
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeaderImgUrl() {
		return headerImgUrl;
	}

	public void setHeaderImgUrl(String headerImgUrl) {
		this.headerImgUrl = headerImgUrl;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}