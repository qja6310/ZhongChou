package cn.com.newloading.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.com.newloading.bean.WithdrawLog;

@Mapper
public interface WithdrawLogMapper {

	@Insert("insert into t_withdraw_log (projectId,adminId,userId,money,applyTime,auditTime,status) values (#{projectId},#{adminId},#{userId},#{money},#{applyTime},#{auditTime},#{status})")
	Integer addWithdrawLog(WithdrawLog withdrawLog);
	
	@Select("select count(*) from t_withdraw_log where status != 0 and status != 3 and userId = #{userId}")
	Integer checkOverStatusCount(@Param("userId")String userId);
	
}
