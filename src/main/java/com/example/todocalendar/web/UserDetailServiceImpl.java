package com.example.todocalendar.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.todocalendar.domain.UserEntity;
import com.example.todocalendar.domain.UserRepository;

// Spring security uses this to authenticate and authorize user

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private final UserRepository repository;

	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.repository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity currentUser = repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, currentUser.getPasswordHash(),
				AuthorityUtils.createAuthorityList(currentUser.getRole()));

		return user;
	}

}
