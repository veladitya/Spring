package com.fedex.lhso.ils.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fedex.lhso.ils.entity.Role;
import com.fedex.lhso.ils.entity.User;
import com.fedex.lhso.ils.repository.RoleRepository;
import com.fedex.lhso.ils.repository.UserRepository;
import com.fedex.lhso.ils.views.View;

@RestController
@RequestMapping(value = "/api")
public class BloggerController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@JsonView(View.UserInfo.class)
	@RequestMapping(value="/allUsers", produces="application/json", method=RequestMethod.GET)
	public Iterable<User> users() {		
		return userRepository.findAll();
	}
	
	@RequestMapping(value="/login", produces="application/json")
	public Object login(@RequestBody Map<String, String> loginMap){
		
		List<User> userList = userRepository.findByName(loginMap.get("userName"));
		if(!userList.isEmpty()) {
			User user = new User();
			if(userList.get(0).getPassword().equals(loginMap.get("password"))) {
				user.setName(userList.get(0).getName());
				user.setEmail(userList.get(0).getEmail());
				user.setUserRole(userList.get(0).getUserRole());				
				return user;
			}else {
				return "{\"valid\": false, \"errorMsg\": \"User details not matching.\" }";
			}
		}
		return "{\"valid\": false}";
	}	
	
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
