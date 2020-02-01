package cn.com.newloading.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Admin;
import cn.com.newloading.bean.WithdrawLog;
import cn.com.newloading.service.FundManageService;
import cn.com.newloading.utils.StringUtil;

@Controller
@RequestMapping("/fund")
public class FundController extends BaseController {
	
	@Autowired
	private FundManageService fmService;

	@RequestMapping("/fundManage")
	public ModelAndView fundManage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("fundManage");
		return mav;
	}
	
	@RequestMapping("/getTotal")
	@ResponseBody
	public JSONObject getTotal(HttpServletRequest request) {
		String status = request.getParameter("status");
		String startApplyTime = request.getParameter("startApplyTime");
		String endApplyTime = request.getParameter("endApplyTime");
		String keyword = request.getParameter("keyword");
		String userId = request.getParameter("userId");
		WithdrawLog wl = new WithdrawLog();
		wl.setStatus(status);
		wl.setStartApplyTime(startApplyTime);
		wl.setEndApplyTime(endApplyTime);
		wl.setKeyword(keyword);
		wl.setUserId(userId);
		Integer total = fmService.getTotal(wl);
		JSONObject json = new JSONObject();
		json.put("total",total);
		return json;
	}
	
	@RequestMapping("fundManagePage")
	public ModelAndView fundManagePage(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("fundManagePage");
		String status = request.getParameter("status");
		String startApplyTime = request.getParameter("startApplyTime");
		String endApplyTime = request.getParameter("endApplyTime");
		String keyword = request.getParameter("keyword");
		String currPage = request.getParameter("currPage");
		String limit = request.getParameter("limit");
		WithdrawLog wl = new WithdrawLog();
		wl.setStatus(status);
		wl.setStartApplyTime(startApplyTime);
		wl.setEndApplyTime(endApplyTime);
		wl.setKeyword(keyword);
		wl.setCurrent(Integer.valueOf(currPage));
		wl.setLimit(Integer.valueOf(limit));
		wl.setStart((Integer.valueOf(currPage) - 1) * Integer.valueOf(limit));
		List<WithdrawLog> wlList = fmService.queryWithdrawLogByParams(wl);
		model.addAttribute("wlList", wlList);
		return mav;
	}
	
	@RequestMapping("/auditWPass")
	@ResponseBody
	public JSONObject auditWPass(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isBlank(id)) {
			return responseMsg("0002","WDL");
		}
		Admin admin = (Admin) request.getSession().getAttribute("user");
		if(admin == null) {
			return responseMsg("0002","WDL");
		}
		String retcode = fmService.auditWPass(id,admin.getId());
		return responseMsg(retcode,"WDL");
	}
	
	@RequestMapping("/auditWNotPass")
	@ResponseBody
	public JSONObject auditWNotPass(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isBlank(id)) {
			return responseMsg("0002","WDL");
		}
		Admin admin = (Admin) request.getSession().getAttribute("user");
		if(admin == null) {
			return responseMsg("0002","WDL");
		}
		String retcode = fmService.auditWNotPass(id,admin.getId());
		return responseMsg(retcode,"WDL");
	}
	
	@RequestMapping("/myFundManage")
	public ModelAndView myFundManage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("myFundManage");
		return mav;
	}
	
	@RequestMapping("myFundManagePage")
	public ModelAndView myFundManagePage(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("myFundManagePage");
		String status = request.getParameter("status");
		String startApplyTime = request.getParameter("startApplyTime");
		String endApplyTime = request.getParameter("endApplyTime");
		String keyword = request.getParameter("keyword");
		String currPage = request.getParameter("currPage");
		String limit = request.getParameter("limit");
		String userId = request.getParameter("userId");
		WithdrawLog wl = new WithdrawLog();
		wl.setStatus(status);
		wl.setStartApplyTime(startApplyTime);
		wl.setEndApplyTime(endApplyTime);
		wl.setKeyword(keyword);
		wl.setCurrent(Integer.valueOf(currPage));
		wl.setLimit(Integer.valueOf(limit));
		wl.setStart((Integer.valueOf(currPage) - 1) * Integer.valueOf(limit));
		wl.setUserId(userId);
		List<WithdrawLog> wlList = fmService.queryWithdrawLogByParams(wl);
		model.addAttribute("wlList", wlList);
		return mav;
	}
	
	@RequestMapping("/pztj")
	public ModelAndView pztj(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("pztj");
		String wlId = request.getParameter("wlId");
		WithdrawLog wl = new WithdrawLog();
		wl.setId(wlId);
		List<WithdrawLog> wlList = fmService.queryWithdrawLogByParams(wl);
		wl = wlList.get(0);
		model.addAttribute("wl", wl);
		return mav;
	}
	
	@RequestMapping("/tjpz")
	@ResponseBody
	public JSONObject tjpz(HttpServletRequest request) {
		String wlId = request.getParameter("wlId");
		if(StringUtil.isBlank(wlId)) {
			return responseMsg("0002","WDL");
		}
		String evidence = request.getParameter("evidence");
		if(StringUtil.isBlank(evidence)) {
			return responseMsg("0002","WDL");
		}
		WithdrawLog wl = new WithdrawLog();
		wl.setId(wlId);
		wl.setEvidence(evidence);
		String retcode = fmService.pztj(wl);
		return responseMsg(retcode,"WDL");
	}
	
	@RequestMapping("/pzDetails")
	public ModelAndView pzDetails(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("pzDetails");
		String wlId = request.getParameter("wlId");
		WithdrawLog wl = new WithdrawLog();
		wl.setId(wlId);
		List<WithdrawLog> wlList = fmService.queryWithdrawLogByParams(wl);
		wl = wlList.get(0);
		model.addAttribute("wl", wl);
		return mav;
	}
	
	@RequestMapping("/pzAuditNotPass")
	@ResponseBody
	public JSONObject pzAuditNotPass(HttpServletRequest request) {
		String wlId = request.getParameter("wlId");
		if(StringUtil.isBlank(wlId)) {
			return responseMsg("0002","WDL");
		}
		Admin admin = (Admin) request.getSession().getAttribute("user");
		if(admin == null) {
			return responseMsg("0002","WDL");
		}
		String retcode = fmService.pzAuditNotPass(wlId, admin.getId());
		return responseMsg(retcode,"WDL");
	}
	
	@RequestMapping("/pzAuditPass")
	@ResponseBody
	public JSONObject pzAuditPass(HttpServletRequest request) {
		String wlId = request.getParameter("wlId");
		if(StringUtil.isBlank(wlId)) {
			return responseMsg("0002","WDL");
		}
		Admin admin = (Admin) request.getSession().getAttribute("user");
		if(admin == null) {
			return responseMsg("0002","WDL");
		}
		String retcode = fmService.pzAuditPass(wlId, admin.getId());
		return responseMsg(retcode,"WDL");
	}
}
