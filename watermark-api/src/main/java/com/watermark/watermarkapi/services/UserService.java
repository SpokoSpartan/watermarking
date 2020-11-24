package com.watermark.watermarkapi.services;

import com.watermark.watermarkapi.entities.User;
import com.watermark.watermarkapi.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			User userAccount = user.get();
			return new org.springframework.security.core.userdetails.User(
					userAccount.getEmail(), userAccount.getPassword(), Collections.emptyList());
		}
		throw new UsernameNotFoundException("User not found.");
	}

	public User getLoggedInUser() throws UsernameNotFoundException{

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if(principal instanceof org.springframework.security.core.userdetails.User) {
			org.springframework.security.core.userdetails.User user=
					((org.springframework.security.core.userdetails.User) principal);
			Optional<User> userOptional = userRepository.findByEmail(user.getUsername());
			if (userOptional.isPresent()) {
				return userOptional.get();
			}
		}
		throw new UsernameNotFoundException("User not found.");
	}
}
