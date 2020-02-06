package cn.com.newloading.service;

import java.util.List;

import cn.com.newloading.bean.AuditLog;

public interface AuditLogService {

	List<AuditLog> queryAuditLogByProId(String projectId);
	
}
