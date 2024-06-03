package com.javainuse.bootmysqlcrud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javainuse.bootmysqlcrud.entity.DAOUser;

public interface UserRepository extends JpaRepository<DAOUser, Integer> {
	@Query(value = "SELECT * FROM landportal.member WHERE userid = :userid", nativeQuery = true)
	Optional<DAOUser> findByUserid(String userid);
}
