package com.nzb.eshop.inventory.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.nzb.eshop.inventory.dao.RedisDao;
import com.nzb.eshop.inventory.mapper.UserMapper;
import com.nzb.eshop.inventory.model.User;
import com.nzb.eshop.inventory.service.UserService;

/**
 * @Description: 用户实现service类
 * @author M
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Resource
	private RedisDao redisDao;

	@Override
	public User findUserInfo() {
		return userMapper.findUserInfo();
	}

	@Override
	public User getCachedUserInfo() {
		redisDao.set("cached_user", "{\"name\": \"lisi\", \"age\": \"28\"}");
		String userJSON = redisDao.get("cached_user");

		JSONObject userJSONObject = JSONObject.parseObject(userJSON);

		User user = new User();
		user.setName(userJSONObject.getString("name"));
		user.setAge(userJSONObject.getInteger("age"));
		return user;
	}

}
