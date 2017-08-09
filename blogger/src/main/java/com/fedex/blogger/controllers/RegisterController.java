package com.fedex.blogger.controllers;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fedex.blogger.entity.Role;
import com.fedex.blogger.entity.User;
import com.fedex.blogger.repository.RoleRepository;
import com.fedex.blogger.repository.UserRepository;

@RestController
public class RegisterController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@RequestMapping(value = "/available")
	public boolean isUserIdAvailable(@RequestParam String name) {
		List<User> usersList = userRepository.findByName(name);

		if (usersList == null || usersList.isEmpty()) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public Object doRegister(@RequestBody @Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return result.getFieldErrors();
		}

		if (user != null) {
			List<Role> userRolesList = roleRepository.findByName(user.getUserRole());
			user.setRoles(userRolesList);
		}
		User s = userRepository.save(user);
		if (s == null) {
			return false;
		}
		return true;
	}

}
