package cn.com.newloading.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.com.newloading.bean.WithdrawLog;
import cn.com.newloading.dao.provider.FundManageProvider;

@Mapper
public interface FundManageMapper {

	@SelectProvider(type = FundManageProvider.class,method = "getTotal")
	Integer getTotal(WithdrawLog wl);
	
	@SelectProvider(type = FundManageProvider.class,method = "queryWithdrawLogByParams")
	List<WithdrawLog> queryWithdrawLogByParams(WithdrawLog wl);
	
	@UpdateProvider(type = FundManageProvider.class,method = "editWithdrawLog")
	Integer editWithdrawLog(WithdrawLog wl);
	
}
