package com.nzb.eshop.inventory.mapper;

import com.nzb.eshop.inventory.model.User;

/**
 * @Description: 测试用户的Mapper接口
 * @author M
 *
 */
public interface UserMapper {

	/**
	 * @Description: 查询测试用户的信息
	 * @return 测试用户的信息
	 */
	public User findUserInfo();

}
