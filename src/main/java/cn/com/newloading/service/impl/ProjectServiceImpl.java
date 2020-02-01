package cn.com.newloading.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.AuditLog;
import cn.com.newloading.bean.DonationLog;
import cn.com.newloading.bean.News;
import cn.com.newloading.bean.Project;
import cn.com.newloading.bean.User;
import cn.com.newloading.dao.mapper.AuditLogMapper;
import cn.com.newloading.dao.mapper.DonationLogMapper;
import cn.com.newloading.dao.mapper.ProjectMapper;
import cn.com.newloading.dao.mapper.UserMapper;
import cn.com.newloading.service.ProjectService;
import cn.com.newloading.utils.StringUtil;
import cn.com.newloading.utils.TimeUtil;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectMapper proMapper;
	@Autowired
	private AuditLogMapper auditMapper;
	@Autowired
	private DonationLogMapper dlMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String addProject(Project project) {
		//判断是否具备发表项目条件
		//判断用户是否是拉黑状态
		User user = userMapper.queryUserInfo(project.getUserId());
		if("2".equals(user.getStatus())) {
			return "0004";
		}
		//判断前面的项目是否存在未完结
		Integer count = proMapper.wajProByUserId(project.getUserId());
		if(count != 0) {
			return "0005";
		}
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
		project.setTotalMoney("0.00");
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
		List<Project> proList = proMapper.queryProjectByParams(project);
		for (int i = 0; i < proList.size(); i++) {
			Project p = proList.get(i);
			if(p.getDetails().length() > 100) {
				int index = p.getDetails().indexOf("<img");
				if(index > 100) {
					String details = p.getDetails().substring(0, 100);
					details += "......[点击查看详情]";
					p.setDetails(details);
				}else if(index < 100){
					String details = p.getDetails().substring(0, index);
					details += "[图片]......[点击查看详情]";
					p.setDetails(details);
				}else {
					if(index == -1) {
						String details = p.getDetails() + "[图片]......[点击查看详情]";
						p.setDetails(details);
					}else {
						String details = p.getDetails().substring(0, 100);
						details += "[图片]......[点击查看详情]";
						p.setDetails(details);
					}
					
				}
			}else {
				int index = p.getDetails().indexOf("<img");
				String details = p.getDetails().substring(0, index);
				details += "[图片]......[点击查看详情]";
				p.setDetails(details);
			}
		}
		return proList;
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
		if("3".equals(pro.getStatus())) {
			Project project = proMapper.queryProjectById(auditLog.getProjectId());
			int refuseCount = Integer.valueOf(project.getRefuseCount()) + 1;
			pro.setRefuseCount(String.valueOf(refuseCount));
		}
		
		Integer ret = proMapper.editProject(pro);
		if(ret == 1) {
			auditLog.setAuditTime(TimeUtil.dateToString(new Date()));
//			if(StringUtil.isBlank(auditLog.getExplain())) {
//				auditLog.setExplain(" ");
//			}
			ret = auditMapper.addAuditLog(auditLog);
			if(ret == 1) {
				return "0000";
			}
			return "0001";
		}
		return "0001";
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String editProject(Project project) {
		// TODO Auto-generated method stub
		project.setStatus("1");
		Integer ret = proMapper.editProject(project);
		if(ret == 1) {
			return "0000";
		}
		return "0001";
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String donation(String proId,String money,String userId) {
		// TODO Auto-generated method stub
		Project pro = proMapper.queryProjectById(proId);
		Double total = Double.valueOf(pro.getTotalMoney()) + Double.valueOf(money);
		Double current = Double.valueOf(pro.getCurrentMoney()) + Double.valueOf(money);
		pro.setTotalMoney(String.valueOf(total));
		pro.setCurrentMoney(String.valueOf(current));
		pro.setId(proId);
		Integer ret = proMapper.editProject(pro);
		if(ret == 1) {
			DonationLog donationLog = new DonationLog();
			donationLog.setProjectId(proId);
			donationLog.setUserId(userId);
			donationLog.setMoney(money);
			donationLog.setDonationTime(TimeUtil.dateToString(new Date()));
			ret = dlMapper.addDonationLog(donationLog);
			if(ret == 1) {
				return "0000";
			}else {
				return "0001";
			}
		}
		return "0001";
	}

}
