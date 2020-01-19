package cn.com.newloading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.newloading.bean.Admin;
import cn.com.newloading.dao.mapper.AdminMapper;
import cn.com.newloading.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	@Override
	public Admin doLogin(String phone, String password) {
		// TODO Auto-generated method stub
		return adminMapper.doLogin(phone, password);
	}

	@Override
	public Admin queryAdminInfo(String id) {
		// TODO Auto-generated method stub
		return adminMapper.queryAdminInfoById(id);
	}

	@Override
	public String saveUpdate(Admin admin) {
		// TODO Auto-generated method stub
		int count = adminMapper.adminTotalByPhone(admin.getPhone(), admin.getId());
		if(count > 0) {
			return "0003";
		}
		Integer ret = adminMapper.saveUpdate(admin);
		if(ret == 1) {
			return "0010";
		}else {
			return "0011";
		}
	}

}
