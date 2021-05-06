package com.MRSISA2021_T15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.UserRepository;

@Service
public class SystemAdminServiceImpl implements SystemAdminService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String updatePassword(ChangePassword passwords) {
		String message = "";
		SystemAdmin currentUser = (SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!passwordEncoder.matches(passwords.getOldPassword(), currentUser.getPassword())) {
			message = "Wrong old password!";
		} else {
			if (currentUser.getFirstLogin()) {
				currentUser.setFirstLogin(false);
			}
			currentUser.setPassword(passwordEncoder.encode(passwords.getPassword()));
			userRepository.save(currentUser);
		}
		return message;
	}

}
