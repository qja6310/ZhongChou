package cn.com.newloading.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import cn.com.newloading.bean.Project;
import cn.com.newloading.utils.StringUtil;

public class ProjectProvider {

	private static final String T_PROJECT = "t_project";
	private static final String T_USER = "t_user";
	
	public String queryProjectByParams(Project project) {
		SQL sql = new SQL();
		sql.SELECT("u.name userName,p.id,p.patientName,p.patientAge,p.patientSex,p.details,p.currentMoney,p.targetMoney,p.endTime," + 
				"p.phone,p.`status`,p.applyTime,p.refuseCount,p.totalMoney");
		sql.FROM(T_PROJECT + " p inner join " + T_USER + " u on u.id = p.userId");
		if(StringUtil.isNotBlank(project.getUserName())) {
			project.setUserName("%"+project.getUserName()+"%");
			sql.WHERE("u.name like #{userName}");
		}
		if(StringUtil.isNotBlank(project.getPatientName())) {
			project.setPatientName("%"+project.getPatientName()+"%");
			sql.WHERE("p.patientName like #{patientName}");
		}
		if(StringUtil.isNotBlank(project.getPatientSex())) {
			sql.WHERE("p.patientSex = #{patientSex}");
		}
		if(StringUtil.isNotBlank(project.getPhone())) {
			project.setPhone("%"+project.getPhone()+"%");
			sql.WHERE("p.phone like #{phone}");
		}
		if(StringUtil.isNotBlank(project.getStatus())) {
			sql.WHERE("p.`status` = #{status}");
		}
		if(StringUtil.isNotBlank(project.getStartApplyTime())) {
			sql.WHERE("p.applyTime >= DATE_FORMAT(#{startApplyTime},'%Y-%m-%d %H:%i:%s')");
		}
		if(StringUtil.isNotBlank(project.getEndApplyTime())) {
			sql.WHERE("p.applyTime <= DATE_FORMAT(#{endApplyTime},'%Y-%m-%d %H:%i:%s')");
		}
		if(StringUtil.isNotBlank(project.getDetails())) {
			project.setDetails("%"+project.getDetails()+"%");
			sql.WHERE("p.details like #{details} or p.patientName like #{details} or p.disease like #{details}");
		}
		if(StringUtil.isNotBlank(project.getUserId())) {
			sql.WHERE("p.userId = #{userId}");
		}
		String desc = "p.applyTime desc";
		if(project.getStart() != 0 && project.getLimit() !=0) {
			desc += " limit #{start},#{limit}";
		}
		sql.ORDER_BY(desc);
		return sql.toString();
	}
	
	public String queryProjectCountByParams(Project project) {
		SQL sql = new SQL();
		sql.SELECT("count(*)");
		sql.FROM(T_PROJECT + " p inner join " + T_USER + " u on u.id = p.userId");
		if(StringUtil.isNotBlank(project.getUserName())) {
			project.setUserName("%"+project.getUserName()+"%");
			sql.WHERE("u.name like #{userName}");
		}
		if(StringUtil.isNotBlank(project.getPatientName())) {
			project.setPatientName("%"+project.getPatientName()+"%");
			sql.WHERE("p.patientName like #{patientName}");
		}
		if(StringUtil.isNotBlank(project.getPatientSex())) {
			sql.WHERE("p.patientSex = #{patientSex}");
		}
		if(StringUtil.isNotBlank(project.getPhone())) {
			project.setPhone("%"+project.getPhone()+"%");
			sql.WHERE("p.phone like #{phone}");
		}
		if(StringUtil.isNotBlank(project.getStatus())) {
			sql.WHERE("p.`status` = #{status}");
		}
		if(StringUtil.isNotBlank(project.getStartApplyTime())) {
			sql.WHERE("p.applyTime >= #{startApplyTime}");
		}
		if(StringUtil.isNotBlank(project.getEndApplyTime())) {
			sql.WHERE("p.applyTime <= #{endApplyTime}");
		}
		if(StringUtil.isNotBlank(project.getDetails())) {
			project.setDetails("%"+project.getDetails()+"%");
			sql.WHERE("p.details like #{details} or p.patientName like #{details} or p.disease like #{details}");
		}
		return sql.toString();
	}
	
	public String editProject(Project project) {
		SQL sql = new SQL();
		sql.UPDATE(T_PROJECT);
		if(StringUtil.isNotBlank(project.getStatus())) {
			sql.SET("status = #{status}");
		}
		if(StringUtil.isNotBlank(project.getRefuseCount())) {
			sql.SET("refuseCount = #{refuseCount}");
		}
		if(StringUtil.isNotBlank(project.getPatientName())) {
			sql.SET("patientName = #{patientName}");
		}
		if(StringUtil.isNotBlank(project.getPatientAge())) {
			sql.SET("patientAge = #{patientAge}");
		}
		if(StringUtil.isNotBlank(project.getPatientSex())) {
			sql.SET("patientSex = #{patientSex}");
		}
		if(StringUtil.isNotBlank(project.getPhone())) {
			sql.SET("phone = #{phone}");
		}
		if(StringUtil.isNotBlank(project.getIdentityNum())) {
			sql.SET("identityNum = #{identityNum}");
		}
		if(StringUtil.isNotBlank(project.getTargetMoney())) {
			sql.SET("targetMoney = #{targetMoney}");
		}
		if(StringUtil.isNotBlank(project.getHospital())) {
			sql.SET("hospital = #{hospital}");
		}
		if(StringUtil.isNotBlank(project.getEndTime())) {
			sql.SET("endTime = #{endTime}");
		}
		if(StringUtil.isNotBlank(project.getNowAddress())) {
			sql.SET("nowAddress = #{nowAddress}");
		}
		if(StringUtil.isNotBlank(project.getDetails())) {
			sql.SET("details = #{details}");
		}
		if(StringUtil.isNotBlank(project.getTotalMoney())) {
			sql.SET("totalMoney = #{totalMoney}");
		}
		if(StringUtil.isNotBlank(project.getCurrentMoney())) {
			sql.SET("currentMoney = #{currentMoney}");
		}
		sql.WHERE("id = #{id}");
		return sql.toString();
	}
}
