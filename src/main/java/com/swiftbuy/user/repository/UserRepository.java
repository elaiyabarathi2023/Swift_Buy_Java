package com.swiftbuy.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.swiftbuy.user.model.UserDetails;

@Repository
@RepositoryRestResource(exported=false)
public interface UserRepository extends CrudRepository<UserDetails, Long> {

	UserDetails findByEmail(String email);

	boolean existsByUsername(String username);

   

}
