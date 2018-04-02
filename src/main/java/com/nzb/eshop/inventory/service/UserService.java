package com.nzb.eshop.inventory.service;

import com.nzb.eshop.inventory.model.User;

/**
 * @Description: 用户service接口
 * @author M
 *
 */
public interface UserService {

	/**
	 * @Description: 查询测试用户的信息
	 * @return 测试用户的信息
	 */
	public User findUserInfo();

	/**
	 * 查詢redis中緩存的用戶信息
	 * 
	 * @return user
	 */
	public User getCachedUserInfo();

}
