package cn.com.newloading.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.newloading.bean.WithdrawLog;
import cn.com.newloading.dao.mapper.WithdrawLogMapper;
import cn.com.newloading.service.WithdrawLogService;
import cn.com.newloading.utils.TimeUtil;

@Service
public class WithdrawLogServiceImpl implements WithdrawLogService{

	@Autowired
	private WithdrawLogMapper wlMapper;
	
	@Override
	public String addWithdrawLog(WithdrawLog withdrawLog) {
		/*
		 * 判断是否具有未完结的提现记录，记录状态如下：
		 * 1--待审核
		 * 2--审核通过
		 * 3--审核不通过
		 * 4--凭证提交后待审核
		 * 5--凭证审核不通过
		 * 0--完结（在审核通过，领完钱后需要将使用的发票凭证等提交，再由管理员审核，审核过后状态为0，算完结）
		 */
		Integer count = wlMapper.checkOverStatusCount(withdrawLog.getUserId());
		if(count > 0) {
			return "0003";
		}
		withdrawLog.setApplyTime(TimeUtil.dateToString(new Date()));
		withdrawLog.setStatus("1");
		Integer ret = wlMapper.addWithdrawLog(withdrawLog);
		if(ret == 1) {
			return "0000";
		}else {
			return "0001";
		}
	}
}
