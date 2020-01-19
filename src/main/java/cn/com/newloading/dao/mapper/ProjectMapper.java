package cn.com.newloading.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import cn.com.newloading.bean.Project;

@Mapper
public interface ProjectMapper {

	@Insert("insert into t_project(patientName,patientAge,patientSex,hospital,phone,targetMoney,currentMoney,endTime,"
			+ "nowAddress,identityNum,details,applyTime,userId,status,refuseCount) "
			+ "values (#{patientName},#{patientAge},#{patientSex},#{hospital},#{phone},#{targetMoney},#{currentMoney},#{endTime},"
			+ "#{nowAddress},#{identityNum},#{details},#{applyTime},#{userId},#{status},#{refuseCount})")
	Integer addProject(Project project);

}
