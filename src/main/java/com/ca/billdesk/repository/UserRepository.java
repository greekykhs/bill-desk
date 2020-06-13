package com.ca.billdesk.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.ca.billdesk.model.LoginRequest;

public interface UserRepository extends CrudRepository<LoginRequest, Long> {

	@Query("SELECT ud FROM LoginRequest ud WHERE ud.email = :email and ud.password = :password")
	public LoginRequest findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
	
	public LoginRequest findByEmail(String email);
}
