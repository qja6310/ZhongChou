package cn.com.newloading.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.newloading.bean.AuditLog;
import cn.com.newloading.dao.mapper.AuditLogMapper;
import cn.com.newloading.service.AuditLogService;

@Service
public class AuditLogServiceImpl implements AuditLogService {

	@Autowired
	private AuditLogMapper alMapper;
	
	@Override
	public List<AuditLog> queryAuditLogByProId(String projectId) {
		// TODO Auto-generated method stub
		return alMapper.queryAuditLogByProId(projectId);
	}

}
