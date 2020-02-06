package cn.com.newloading.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.com.newloading.bean.AuditLog;

@Mapper
public interface AuditLogMapper {

	@Insert("insert into t_audit_log (adminId,projectId,explains,status,auditTime) values (#{adminId},#{projectId},#{explains},#{status},#{auditTime})")
	Integer addAuditLog(AuditLog auidtLog);
	
	@Select("SELECT al.*,a.name adminName FROM t_audit_log al INNER JOIN t_admin a ON a.id = al.adminId WHERE al.projectId = #{projectId} ORDER BY auditTime DESC")
	List<AuditLog> queryAuditLogByProId(@Param("projectId") String projectId);
	
}
