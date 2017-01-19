package com.javatea.spotchserver.controller;

import com.javatea.spotchserver.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class UserController {
	@RequestMapping(value = "/",method = RequestMethod.POST)
	public void signIn(@RequestParam String username,
					   @RequestParam String email,
					   @RequestParam Date birthday) {

	}
}
