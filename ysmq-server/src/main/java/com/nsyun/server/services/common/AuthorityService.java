package com.nsyun.server.services.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsyun.server.domain.users.Authority;
import com.nsyun.server.repository.users.AuthorityRepository;

@Service
public class AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	public Authority findById (Integer id) throws Exception {
		try {
			return authorityRepository.findOne(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
