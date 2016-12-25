package com.nsyun.server.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsyun.server.domain.common.OauthClientDetails;

public interface OauthClientDetailsRepository extends JpaRepository<OauthClientDetails, String> {

}
