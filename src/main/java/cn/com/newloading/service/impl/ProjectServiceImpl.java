package cn.com.newloading.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.AuditLog;
import cn.com.newloading.bean.Project;
import cn.com.newloading.dao.mapper.AuditLogMapper;
import cn.com.newloading.dao.mapper.ProjectMapper;
import cn.com.newloading.service.ProjectService;
import cn.com.newloading.utils.TimeUtil;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectMapper proMapper;
	@Autowired
	private AuditLogMapper auditMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String addProject(Project project) {
		
		//判断至少上传一张图片
		String img = project.getDetails();
		int index = img.indexOf("<img");
		if(index == -1) {//没有上传图片
			return "0003";
		}
		
		project.setApplyTime(TimeUtil.dateToString(new Date()));
		project.setCurrentMoney("0.00");
		project.setStatus("1");
		project.setRefuseCount("0");
		Integer ret = proMapper.addProject(project);
		if(ret == 1) {
			return "0000";
		}else {
			return "0001";
		}
	}

	@Override
	public int queryProjectCountByParams(Project project) {
		// TODO Auto-generated method stub
		return proMapper.queryProjectCountByParams(project);
	}

	@Override
	public List<Project> queryProjectByParams(Project project) {
		// TODO Auto-generated method stub
		return proMapper.queryProjectByParams(project);
	}

	@Override
	public Project queryProjectById(String id) {
		// TODO Auto-generated method stub
		return proMapper.queryProjectById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String auditProject(AuditLog auditLog) {
		// TODO Auto-generated method stub
		Project pro = new Project();
		pro.setStatus(auditLog.getStatus());
		pro.setId(auditLog.getProjectId());
		Integer ret = proMapper.editProject(pro);
		if(ret == 1) {
			auditLog.setAuditTime(TimeUtil.dateToString(new Date()));
			ret = auditMapper.addAuditLog(auditLog);
			if(ret == 1) {
				return "0000";
			}
			return "0001";
		}
		return "0001";
	}

}
