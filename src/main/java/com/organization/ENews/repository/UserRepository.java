package com.organization.ENews.repository;

import com.organization.ENews.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
	
	boolean existsByUsername(String username);
	User findByUsername(String username);
    Boolean existsByEmail(String email);

}
