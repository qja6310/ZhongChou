package cn.com.newloading.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.User;
import cn.com.newloading.bean.UserInfo;
import cn.com.newloading.dao.mapper.UserMapper;
import cn.com.newloading.service.UserService;
import cn.com.newloading.utils.TimeUtil;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User doLogin(String name, String password) {
		// TODO Auto-generated method stub
		return userMapper.doLogin(name, password);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String saveUpdate(User user) {
		int count = userMapper.userTotalByPhone2(user.getPhone(),user.getId());
		if(count > 0) {
			return "0003";
		}
		Integer ret = userMapper.editUser(user);
		if(ret == 1) {
			return "0010";
		}else {
			return "0011";
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String addUser(User user) {
		//新增之前查询号码是否可用
		int count = userMapper.userTotalByPhone(user.getPhone());
		if(count > 0) {
			return "0003";
		}
		user.setRole("u");
		user.setStatus("1");
		user.setCreateTime(TimeUtil.dateToString(new Date()));
		Integer ret = userMapper.addUser(user);
		if(ret == 1) {
			return "0000";
		}
		return "0001";
	}

	@Override
	public User queryUserInfo(String userId) {
		// TODO Auto-generated method stub
		return userMapper.queryUserInfo(userId);
	}

	@Override
	public int getTotal(User user) {
		// TODO Auto-generated method stub
		return userMapper.getTotal(user);
	}

	@Override
	public List<User> queryUserByParams(UserInfo user) {
		// TODO Auto-generated method stub
		List<User> userList = userMapper.queryUserByParams(user);
		if(userList != null && userList.size() > 0) {
			for (User u : userList) {
				int btgxmCount = userMapper.btgxmCount(u.getId());
				u.setBtgxmCount(String.valueOf(btgxmCount));
			}
		}
		return userList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String editUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
