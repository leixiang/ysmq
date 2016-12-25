package com.nsyun.server.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nsyun.server.domain.common.OauthRefreshToken;

public interface OauthRefreshTokenRepository extends JpaRepository<OauthRefreshToken, Long>{
	@Query("SELECT u FROM OauthRefreshToken u WHERE u.tokenId = :tokenId")
	OauthRefreshToken findByTokenId(@Param("tokenId") String tokenId);
}
