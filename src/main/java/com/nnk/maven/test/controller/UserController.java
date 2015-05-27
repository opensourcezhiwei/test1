package com.nnk.maven.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.maven.test.entity.User;

/**
 * 
 */
@Controller
public class UserController {

	@RequestMapping("/{id}")
	public String view(@PathVariable("id") Long id) {
		User user = new User();
		user.setId(id);
		user.setName("zhang");
		return "aa";
	}

}
