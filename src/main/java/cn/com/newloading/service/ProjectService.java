package cn.com.newloading.service;

import java.util.List;

import cn.com.newloading.bean.Project;

public interface ProjectService {

	String addProject(Project project);
	
	int queryProjectCountByParams(Project project);
	
	List<Project> queryProjectByParams(Project project);
	
}
