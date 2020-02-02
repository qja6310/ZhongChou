package cn.com.newloading.controller;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.User;
import cn.com.newloading.bean.WithdrawLog;
import cn.com.newloading.service.WithdrawLogService;
import cn.com.newloading.utils.StringUtil;

@Controller
@RequestMapping("/withdraw")
public class WithdrawController extends BaseController{
	
	@Autowired
	private WithdrawLogService wlService;

	@RequestMapping("/apply")
	@ResponseBody
	public JSONObject apply(HttpServletRequest request) {
		String proId = request.getParameter("proId");
		if(StringUtil.isBlank(proId)) {
			return responseMsg("0002","WDL");
		}
		String yhCardNum = request.getParameter("yhCardNum");
		if(StringUtil.isBlank(yhCardNum)) {
			return responseMsg("0002","WDL");
		}
		String txMoney = request.getParameter("txMoney");
		if(StringUtil.isBlank(proId)) {
			return responseMsg("0002","WDL");
		}
		
		//不可以提现0元
		Double m = Double.parseDouble(txMoney);
		DecimalFormat df = new DecimalFormat("0.00");
		String mdf = df.format(m);
		if("0.00".equals(mdf)) {
			return responseMsg("0004","WDL");
		}
		
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			return responseMsg("0002","WDL");
		}
		WithdrawLog wl = new WithdrawLog();
		wl.setProjectId(proId);
		wl.setMoney(txMoney);
		wl.setUserId(user.getId());
		wl.setYhCardNum(yhCardNum);
		String retcode = wlService.addWithdrawLog(wl);
		return responseMsg(retcode,"WDL");
	}
}
