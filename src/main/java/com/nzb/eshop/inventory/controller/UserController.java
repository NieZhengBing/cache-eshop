package com.nzb.eshop.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nzb.eshop.inventory.model.User;
import com.nzb.eshop.inventory.service.UserService;

/**
 * @Description: 用户controller控制器
 * @author M
 *
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/getUserInfo")
	@ResponseBody
	public User getUserInfo() {
		User user = userService.findUserInfo();
		return user;
	}

	@RequestMapping("/getUserCachedInfo")
	@ResponseBody
	public User getCachedUserInfo() {
		User user = userService.getCachedUserInfo();
		return user;
	}

}
