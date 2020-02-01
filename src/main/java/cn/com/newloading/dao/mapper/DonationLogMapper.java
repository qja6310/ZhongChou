package cn.com.newloading.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import cn.com.newloading.bean.DonationLog;

@Mapper
public interface DonationLogMapper {

	@Insert("insert into t_donation_log (projectId,userId,money,donationTime) values (#{projectId},#{userId},#{money},#{donationTime})")
	Integer addDonationLog(DonationLog donationLog);
}
