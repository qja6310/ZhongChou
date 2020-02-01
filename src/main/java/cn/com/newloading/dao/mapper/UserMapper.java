package cn.com.newloading.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.com.newloading.bean.User;
import cn.com.newloading.bean.UserInfo;
import cn.com.newloading.dao.provider.UserProvider;

@Mapper
public interface UserMapper {

	/**
	 * 登陆
	 * @param name
	 * @param password
	 * @return
	 */
	@Select("select id,name,age,sex,phone,password,role,status,create_time from t_user where phone = #{phone} AND password = #{password}")
	User doLogin(@Param("phone")String phone, @Param("password")String password);
	
	/**
	 * 查询用户
	 * @param userId
	 * @return
	 */
	@Select("select id,name,age,sex,phone,password,role,status,create_time from t_user where id = #{userId}")
	User queryUserInfo(@Param("userId")String userId);
	
	/**
	 * 修改用户信息
	 * @param name
	 * @param password
	 * @param userId
	 * @return
	 */
	@UpdateProvider(type = UserProvider.class,method = "editUser")
	Integer editUser(User user);
	
	/**
	 * 新增用户信息
	 * @param user
	 * @return
	 */
	@Insert("insert into t_user(name,age,sex,phone,password,role,status,create_time) values (#{name},#{age},#{sex},#{phone},#{password},#{role},#{status},#{createTime})")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	Integer addUser(User user);
	
	/**
	 * 根据号码查用户数量
	 * @param phone
	 * @return
	 */
	@Select("select count(*) from t_user where phone = #{phone}")
	int userTotalByPhone(@Param("phone")String phone);
	
	@Select("select count(*) from t_user where phone = #{phone} and id != #{id}")
	int userTotalByPhone2(@Param("phone")String phone,@Param("id")String id);
	
	@SelectProvider(type = UserProvider.class,method = "getTotal")
	int getTotal(User user);
	
	@SelectProvider(type = UserProvider.class,method = "queryUserByParams")
	List<User> queryUserByParams(UserInfo user);
	
	@Select("SELECT count(*) from t_project where userId = #{userId} and refuseCount = 3")
	int btgxmCount(@Param("userId")String userId);
	
}
