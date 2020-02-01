package cn.com.newloading.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.com.newloading.bean.Project;
import cn.com.newloading.dao.provider.ProjectProvider;

@Mapper
public interface ProjectMapper {

	@Insert("insert into t_project(patientName,patientAge,patientSex,hospital,phone,targetMoney,currentMoney,endTime,"
			+ "nowAddress,identityNum,details,applyTime,userId,status,refuseCount,totalMoney) "
			+ "values (#{patientName},#{patientAge},#{patientSex},#{hospital},#{phone},#{targetMoney},#{currentMoney},#{endTime},"
			+ "#{nowAddress},#{identityNum},#{details},#{applyTime},#{userId},#{status},#{refuseCount},#{totalMoney})")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	Integer addProject(Project project);

	@SelectProvider(type = ProjectProvider.class, method = "queryProjectByParams")
	List<Project> queryProjectByParams(Project project);
	
	@SelectProvider(type = ProjectProvider.class, method = "queryProjectCountByParams")
	int queryProjectCountByParams(Project project);

	@Select("select u.name userName,p.* from t_project p inner join t_user u on u.id = p.userId where p.id = #{id}")
	Project queryProjectById(@Param("id")String id);
	
	@UpdateProvider(type=ProjectProvider.class,method = "editProject")
	Integer editProject(Project project);
}
