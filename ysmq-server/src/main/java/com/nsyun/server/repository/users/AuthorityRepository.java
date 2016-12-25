package com.nsyun.server.repository.users;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nsyun.server.domain.users.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
