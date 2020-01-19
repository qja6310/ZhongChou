package cn.com.newloading.service;

import cn.com.newloading.bean.User;

public interface UserService {

	/**
	 * 用户登陆
	 * @param name
	 * @param password
	 * @return
	 */
	User doLogin(String name,String password);
	
	User queryUserInfo(String userId);

	String saveUpdate(User user);
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	String addUser(User user);
	
}
