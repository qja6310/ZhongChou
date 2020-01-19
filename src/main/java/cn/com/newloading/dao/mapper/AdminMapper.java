package cn.com.newloading.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.com.newloading.bean.Admin;

public interface AdminMapper {

	/**
	 * 登录
	 * @param phone
	 * @param password
	 * @return
	 */
	@Select("select id,name,age,sex,phone,password,role from t_admin where phone = #{phone} and password = #{password}")
	Admin doLogin(@Param("phone")String phone, @Param("password")String password);
	
	@Select("select id,name,age,sex,phone,password,role from t_admin where id = #{id}")
	Admin queryAdminInfoById(@Param("id")String id);
	
	@Update("update t_admin set name = #{name},age = #{age},sex = #{sex},phone = #{phone},password = #{password} where id = #{id}")
	Integer saveUpdate(Admin admin);
	
	@Select("select count(*) from t_admin where phone = #{phone} and id != #{id}")
	int adminTotalByPhone(@Param("phone")String phone,@Param("id")String id);
	
}
