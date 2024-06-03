package com.javainuse.bootmysqlcrud.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javainuse.bootmysqlcrud.entity.DAOUser;
import com.javainuse.bootmysqlcrud.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		Optional<DAOUser> daoUser = userRepository.findByUserid(userid);
		if (daoUser == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(daoUser.get().getUserid(), daoUser.get().getPw(),
				new ArrayList<>());
	}

}