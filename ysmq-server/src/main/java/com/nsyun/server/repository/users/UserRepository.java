package com.nsyun.server.repository.users;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsyun.server.domain.users.User;

@Repository
public  interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>
{
  @Query("SELECT count(u.id) FROM User u WHERE LOWER(u.username) = LOWER(:username)  ")
  public Integer findByUsername(@Param("username") String username);

  @Query("SELECT count(u.id) FROM User u WHERE LOWER(u.mobile) = LOWER(:mobile) ")
  public Integer findByMobile(@Param("mobile") String mobile);

  
  @Query("SELECT count(u.id) FROM User u WHERE LOWER(u.username) = LOWER(:username) and LOWER(u.id) = LOWER(:id) ")
  public Integer findByUsername(@Param("username") String username,@Param("id") String id);

  @Query("SELECT count(u.id) FROM User u WHERE LOWER(u.mobile) = LOWER(:mobile) and LOWER(u.id) = LOWER(:id) ")
  public Integer findByMobile(@Param("mobile") String mobile,@Param("id") String id);

  @Query("SELECT u FROM User u WHERE LOWER(u.id) = LOWER(:id)")
  public abstract User findUserById(@Param("id") String id);

  @Query("SELECT max(u.id) FROM UserAccount u  ")
  public Integer findTopAccountById();

  @Query("SELECT max(u.id) FROM User u  ")
  public Integer findTopById();

  @Query("select c.id, c.username from User c where 1=1 ")
  public List<User> findUser();

 
  @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)")
  User findByUsernameCaseInsensitive(@Param("username") String username);

  @Query("SELECT u FROM User u WHERE LOWER(u.mobile) = LOWER(:mobile)")
  User findUserByMobile(@Param("mobile") String mobile);

  @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
  User findByEmail(@Param("email") String email);
  
  

  @Query
  User findByEmailAndActivationKey(String email, String activationKey);

  @Query
  User findByEmailAndResetPasswordKey(String email, String resetPasswordKey);
  
}