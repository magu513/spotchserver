package com.javatea.spotchserver.controller;

import com.javatea.spotchserver.User;
import com.javatea.spotchserver.db.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class UserController {
	/*
	TODO HTTPSでの通信
	 */
	@Autowired
	private UserDao ud;

	@RequestMapping(value = "/",method = RequestMethod.POST)
	public void signIn(@RequestParam String username,
					   @RequestParam String email,
					   @RequestParam Date birthday,
					   @RequestParam String password) {
		User u = new User(username,email,birthday);
	}
}
