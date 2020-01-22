package cn.com.newloading.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import cn.com.newloading.bean.AuditLog;

@Mapper
public interface AuditLogMapper {

	@Insert("insert into t_audit_log (adminId,projectId,explain,status,auditTime) values (#{adminId},#{projectId},#{explain},#{status},#{auditTime})")
	Integer addAuditLog(AuditLog auidtLog);
	
}
