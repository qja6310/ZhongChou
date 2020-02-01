package cn.com.newloading.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import cn.com.newloading.bean.WithdrawLog;
import cn.com.newloading.utils.StringUtil;

public class FundManageProvider {

	private static final String T_WITHDRAW_LOG = "t_withdraw_log";
	private static final String T_PROJECT = "t_project";
	private static final String T_USER = "t_user";
	private static final String T_ADMIN = "t_admin";
	
	public String getTotal(WithdrawLog wl) {
		SQL sql = new SQL();
		sql.SELECT("count(*)");
		sql.FROM(T_WITHDRAW_LOG +" wl INNER JOIN " + T_PROJECT + " p on wl.projectId = p.id");
		if(StringUtil.isNotBlank(wl.getStatus())) {
			sql.WHERE("wl.status = #{status}");
		}
		if(StringUtil.isNotBlank(wl.getKeyword())) {
			wl.setKeyword("%" + wl.getKeyword() + "%");
			sql.WHERE("p.details like #{keyword}");
		}
		if(StringUtil.isNotBlank(wl.getStartApplyTime())) {
			sql.WHERE("wl.applyTime >= DATE_FORMAT(#{startApplyTime},'%Y-%m-%d %H:%i:%s')");
		}
		if(StringUtil.isNotBlank(wl.getEndApplyTime())) {
			sql.WHERE("wl.applyTime <= DATE_FORMAT(#{endApplyTime},'%Y-%m-%d %H:%i:%s')");
		}
		if(StringUtil.isNotBlank(wl.getUserId())) {
			sql.WHERE("wl.userId = #{userId}");
		}
		return sql.toString();
	}
	
	public String queryWithdrawLogByParams(WithdrawLog wl) {
		SQL sql = new SQL();
		sql.SELECT("wl.id,wl.money,wl.`status`,wl.applyTime,wl.auditTime,wl.projectId,wl.evidence,wl.pztjTime,wl.pzshTime,wl.pzshAdminId"
				+ ",p.patientName,p.phone,u.`name` userName,p.currentMoney,a1.name txAdminName,a2.name pzAdminName");
		sql.FROM(T_WITHDRAW_LOG + " wl INNER JOIN " + T_PROJECT + " p on wl.projectId = p.id " + 
				"INNER JOIN " + T_USER + " u on u.id = wl.userId LEFT JOIN " + T_ADMIN + " a1 on a1.id = wl.adminId " +
				"LEFT JOIN " + T_ADMIN + " a2 on a2.id = wl.pzshAdminId");
		if(StringUtil.isNotBlank(wl.getId())) {
			sql.WHERE("wl.id = #{id}");
		}
		if(StringUtil.isNotBlank(wl.getStatus())) {
			sql.WHERE("wl.status = #{status}");
		}
		if(StringUtil.isNotBlank(wl.getKeyword())) {
			wl.setKeyword("%" + wl.getKeyword() + "%");
			sql.WHERE("p.details like #{keyword}");
		}
		if(StringUtil.isNotBlank(wl.getStartApplyTime())) {
			sql.WHERE("wl.applyTime >= DATE_FORMAT(#{startApplyTime},'%Y-%m-%d %H:%i:%s')");
		}
		if(StringUtil.isNotBlank(wl.getEndApplyTime())) {
			sql.WHERE("wl.applyTime <= DATE_FORMAT(#{endApplyTime},'%Y-%m-%d %H:%i:%s')");
		}
		if(StringUtil.isNotBlank(wl.getUserId())) {
			sql.WHERE("wl.userId = #{userId}");
		}
		if(StringUtil.isBlank(wl.getId())) {
			sql.ORDER_BY("wl.applyTime desc limit #{start},#{limit}");
		}
		return sql.toString();
	}
	
	public String editWithdrawLog(WithdrawLog wl) {
		SQL sql = new SQL();
		sql.UPDATE(T_WITHDRAW_LOG);
		if(StringUtil.isNotBlank(wl.getStatus())) {
			sql.SET("status = #{status}");
		}
		if(StringUtil.isNotBlank(wl.getAdminId())) {
			sql.SET("adminId = #{adminId}");
		}
		if(StringUtil.isNotBlank(wl.getAuditTime())) {
			sql.SET("auditTime = #{auditTime}");
		}
		if(StringUtil.isNotBlank(wl.getEvidence())) {
			sql.SET("evidence = #{evidence}");
		}
		if(StringUtil.isNotBlank(wl.getPztjTime())) {
			sql.SET("pztjTime = #{pztjTime}");
		}
		if(StringUtil.isNotBlank(wl.getPzshAdminId())) {
			sql.SET("pzshAdminId = #{pzshAdminId}");
		}
		if(StringUtil.isNotBlank(wl.getPzshTime())) {
			sql.SET("pzshTime = #{pzshTime}");
		}
		sql.WHERE("id = #{id}");
		return sql.toString();
	}
}
