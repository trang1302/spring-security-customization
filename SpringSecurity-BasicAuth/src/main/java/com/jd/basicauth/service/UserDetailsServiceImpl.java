package com.jd.basicauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jd.basicauth.entity.User;
import com.jd.basicauth.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
    User user = userRepository.findByUserid(userid)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userid));

    return UserDetailsImpl.build(user);
  }

}