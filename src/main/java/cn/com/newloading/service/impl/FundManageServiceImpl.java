package cn.com.newloading.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.Project;
import cn.com.newloading.bean.WithdrawLog;
import cn.com.newloading.dao.mapper.FundManageMapper;
import cn.com.newloading.dao.mapper.ProjectMapper;
import cn.com.newloading.service.FundManageService;
import cn.com.newloading.utils.TimeUtil;

@Service
public class FundManageServiceImpl implements FundManageService {

	@Autowired
	private FundManageMapper fmMapper;
	@Autowired
	private ProjectMapper pMapper;
	
	@Override
	public Integer getTotal(WithdrawLog wl) {
		// TODO Auto-generated method stub
		return fmMapper.getTotal(wl);
	}

	@Override
	public List<WithdrawLog> queryWithdrawLogByParams(WithdrawLog wl) {
		// TODO Auto-generated method stub
		return fmMapper.queryWithdrawLogByParams(wl);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String auditWPass(String id,String adminId) {
		/*
		 * 1,再次根据id获取金额
		 * 2,扣减项目中的当前金额
		 * 3,修改提现审核状态
		 */
		WithdrawLog wl = new WithdrawLog();
		wl.setId(id);
		List<WithdrawLog> wlList = fmMapper.queryWithdrawLogByParams(wl);
		if(wlList == null || wlList.size() == 0) {
			return "0001";
		}
		wl = wlList.get(0);
		Project pro = pMapper.queryProjectById(wl.getProjectId());
		double newCurrentMoney = 0.00;
		newCurrentMoney = Double.valueOf(pro.getCurrentMoney()) - Double.valueOf(wl.getMoney());
		Project project = new Project();
		project.setCurrentMoney(String.valueOf(newCurrentMoney));
		project.setId(pro.getId());
		Integer ret1 = pMapper.editProject(project);
		
		wl.setStatus("2");
		wl.setAdminId(adminId);
		wl.setAuditTime(TimeUtil.dateToString(new Date()));
		Integer ret2 = fmMapper.editWithdrawLog(wl);
		if(ret1 == 1 && ret2 == 1) {
			return "0000";
		}
		return "0001";
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String auditWNotPass(String id,String adminId) {
		// TODO Auto-generated method stub
		WithdrawLog wl = new WithdrawLog();
		wl.setId(id);
		wl.setStatus("3");
		wl.setAdminId(adminId);
		wl.setAuditTime(TimeUtil.dateToString(new Date()));
		Integer ret = fmMapper.editWithdrawLog(wl);
		if(ret == 1) {
			return "0000";
		}
		return "0001";
	}

	@Override
	public String pztj(WithdrawLog wl) {
		// TODO Auto-generated method stub
		wl.setPztjTime(TimeUtil.dateToString(new Date()));
		wl.setStatus("4");
		Integer ret = fmMapper.editWithdrawLog(wl);
		if(ret == 1) {
			return "0000";
		}
		return "0001";
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String pzAuditNotPass(String wlId,String adminId) {
		// TODO Auto-generated method stub
		WithdrawLog wl = new WithdrawLog();
		wl.setId(wlId);
		wl.setStatus("5");
		wl.setPzshTime(TimeUtil.dateToString(new Date()));
		wl.setPzshAdminId(adminId);
		Integer ret = fmMapper.editWithdrawLog(wl);
		if(ret == 1) {
			return "0000";
		}
		return "0001";
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String pzAuditPass(String wlId,String adminId) {
		// TODO Auto-generated method stub
		WithdrawLog wl = new WithdrawLog();
		wl.setId(wlId);
		List<WithdrawLog> wlList = fmMapper.queryWithdrawLogByParams(wl);
		if(wlList == null || wlList.size() == 0) {
			return "0001";
		}
		wl = wlList.get(0);
		Project pro = pMapper.queryProjectById(wl.getProjectId());
		Project project = new Project();
		project.setId(pro.getId());
		Integer ret1 = 1;
		if(TimeUtil.judgeIsPast(pro.getEndTime())) {
			project.setStatus("0");
			ret1 = pMapper.editProject(project);
		}
		wl.setStatus("0");
		wl.setPzshAdminId(adminId);
		wl.setPzshTime(TimeUtil.dateToString(new Date()));
		Integer ret2 = fmMapper.editWithdrawLog(wl);
		if(ret1 == 1 && ret2 == 1) {
			return "0000";
		}
		return "0001";
	}

}
