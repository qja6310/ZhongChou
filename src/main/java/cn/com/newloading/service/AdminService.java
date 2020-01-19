package cn.com.newloading.service;

import cn.com.newloading.bean.Admin;

public interface AdminService {

	Admin doLogin(String phone,String password);
	
	Admin queryAdminInfo(String id);
	
	String saveUpdate(Admin admin);
	
}
