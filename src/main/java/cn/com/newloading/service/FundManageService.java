package cn.com.newloading.service;

import java.util.List;

import cn.com.newloading.bean.WithdrawLog;

public interface FundManageService {

	Integer getTotal(WithdrawLog wl);
	
	List<WithdrawLog> queryWithdrawLogByParams(WithdrawLog wl);
	
	String auditWPass(String id,String adminId);
	
	String auditWNotPass(String id,String adminId);
	
	String pztj(WithdrawLog wl);
	
	String pzAuditNotPass(String wlId,String adminId);
	
	String pzAuditPass(String wlId,String adminId);
}
