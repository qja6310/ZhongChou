package cn.com.newloading.dao.provider;

import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import cn.com.newloading.bean.User;
import cn.com.newloading.bean.UserInfo;
import cn.com.newloading.utils.StringUtil;

public class UserProvider {

	private static final String T_USER = "t_user";
	
	public String getTotal(User user) {
		SQL sql = new SQL();
		sql.SELECT("count(*)");
		sql.FROM(T_USER);
		if(StringUtil.isNotBlank(user.getName())) {
			user.setName("%"+user.getName()+"%");
			sql.WHERE("name like #{name}");
		}
		if(StringUtil.isNotBlank(user.getSex())) {
			sql.WHERE("sex = #{sex}");
		}
		if(StringUtil.isNotBlank(user.getPhone())) {
			user.setPhone("%"+user.getPhone()+"%");
			sql.WHERE("phone like #{phone}");
		}
		if(StringUtil.isNotBlank(user.getStatus())) {
			sql.WHERE("status = #{status}");
		}
		return sql.toString();
	}
	
	public String queryUserByParams(UserInfo user) {
		SQL sql = new SQL();
		sql.SELECT("id,name,age,sex,phone,status,create_time createTime");
		sql.FROM(T_USER);
		if(StringUtil.isNotBlank(user.getName())) {
			user.setName("%"+user.getName()+"%");
			sql.WHERE("name like #{name}");
		}
		if(StringUtil.isNotBlank(user.getSex())) {
			sql.WHERE("sex = #{sex}");
		}
		if(StringUtil.isNotBlank(user.getPhone())) {
			user.setPhone("%"+user.getPhone()+"%");
			sql.WHERE("phone like #{phone}");
		}
		if(StringUtil.isNotBlank(user.getStatus())) {
			sql.WHERE("status = #{status}");
		}
		sql.ORDER_BY("create_time desc limit #{start},#{limit}");
		return sql.toString();
	}
	
	public String editUser(User user) {
		SQL sql = new SQL();
		sql.UPDATE(T_USER);
		if(StringUtil.isNotBlank(user.getName())) {
			sql.SET("name = #{name}");
		}
		if(StringUtil.isNotBlank(user.getAge())) {
			sql.SET("age = #{age}");
		}
		if(StringUtil.isNotBlank(user.getSex())) {
			sql.SET("sex = #{sex}");
		}
		if(StringUtil.isNotBlank(user.getPhone())) {
			sql.SET("phone = #{phone}");
		}
		if(StringUtil.isNotBlank(user.getPassword())) {
			sql.SET("password = #{password}");
		}
		if(StringUtil.isNotBlank(user.getStatus())) {
			sql.SET("status = #{status}");
		}
		sql.WHERE("id = #{id}");
		return sql.toString();
	}
	
}
