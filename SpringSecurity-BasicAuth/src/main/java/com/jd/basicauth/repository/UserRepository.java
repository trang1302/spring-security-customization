package com.jd.basicauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jd.basicauth.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query(value = "SELECT * FROM landportal.member WHERE userid = :userid", nativeQuery = true)
	Optional<User> findByUserid(String userid);
}
