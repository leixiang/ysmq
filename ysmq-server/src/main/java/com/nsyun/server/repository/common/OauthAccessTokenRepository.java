package com.nsyun.server.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nsyun.server.domain.common.OauthAccessToken;

public interface OauthAccessTokenRepository extends JpaRepository<OauthAccessToken, Long>{
	 @Query("SELECT u FROM OauthAccessToken u WHERE u.tokenId = :tokenId")
	 OauthAccessToken findByTokenId(@Param("tokenId") String tokenId);
	 
	 @Query("SELECT u FROM OauthAccessToken u WHERE u.refreshToken = :refreshToken")
	 OauthAccessToken findByRefreshToken(@Param("refreshToken") String refreshToken);
	 
	 @Query("SELECT u FROM OauthAccessToken u WHERE u.authenticationId = :authenticationId")
	 OauthAccessToken findByAuthenticationId(@Param("authenticationId") String authenticationId);
	 
	 @Query("SELECT u FROM OauthAccessToken u WHERE u.authenticationId = :authenticationId order by u.id asc")
	 List<OauthAccessToken> findByAuthenticationIdList(@Param("authenticationId") String authenticationId);
	 
	 @Query("SELECT u FROM OauthAccessToken u WHERE u.clientId = :clientId and u.userName = :userName")
	 List<OauthAccessToken> findByClientIdAndUserName(@Param("clientId") String clientId, @Param("userName") String userName);
	 
	 
	 @Query("SELECT u FROM OauthAccessToken u WHERE u.clientId = :clientId ")
	 List<OauthAccessToken> findByClientId(@Param("clientId") String clientId);
}
