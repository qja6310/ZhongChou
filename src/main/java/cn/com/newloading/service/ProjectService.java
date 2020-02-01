package cn.com.newloading.service;

import java.util.List;

import cn.com.newloading.bean.AuditLog;
import cn.com.newloading.bean.Project;

public interface ProjectService {

	String addProject(Project project);
	
	int queryProjectCountByParams(Project project);
	
	List<Project> queryProjectByParams(Project project);
	
	Project queryProjectById(String id);
	
	String auditProject(AuditLog auditLog);
	
	String editProject(Project project);
	
	String donation(String proId,String money,String userId);
}
