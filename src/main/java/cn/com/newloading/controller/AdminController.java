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
import cn.com.newloading.bean.Hint;
import cn.com.newloading.bean.User;
import cn.com.newloading.service.AdminService;
import cn.com.newloading.service.HintService;
import cn.com.newloading.utils.StringUtil;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{

	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/doLogin")
	@ResponseBody
	public JSONObject doLogin(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String phone = request.getParameter("phone");
		if (StringUtil.isBlank(phone)) {
			return responseMsg("0002", "USER");
		}
		String password = request.getParameter("password");
		if (StringUtil.isBlank(password)) {
			return responseMsg("0002", "USER");
		}
		Admin admin = adminService.doLogin(phone, password);
		if (admin == null) {
			json.put("retCode", "0");
			json.put("retMsg", "登陆失败");
		} else {
			json.put("retCode", "1");
			json.put("retMsg", "登陆成功");
			request.getSession().setAttribute("user", admin);
		}
		return json;
	}
	
	@RequestMapping("updateAdmin")
	public ModelAndView updateUser(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("adminInfo");
		String id = request.getParameter("roleId");
		if(StringUtil.isBlank(id)) {
			mav = new ModelAndView("index");
		}
		Admin admin = adminService.queryAdminInfo(id);
		model.addAttribute("admin", admin);
		return mav;
	}
	
	@RequestMapping("/saveUpdate")
	@ResponseBody
	public JSONObject saveUpdate(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		if (StringUtil.isBlank(id)) {
			return responseMsg("0004", "USER");
		}
		String name = request.getParameter("name");
		if (StringUtil.isBlank(name)) {
			return responseMsg("0004", "USER");
		}
		String age = request.getParameter("age");
		if (StringUtil.isBlank(age)) {
			return responseMsg("0004", "USER");
		}
		String sex = request.getParameter("sex");
		if (StringUtil.isBlank(sex)) {
			return responseMsg("0004", "USER");
		}
		String phone = request.getParameter("phone");
		if (StringUtil.isBlank(phone)) {
			return responseMsg("0004", "USER");
		}
		String password = request.getParameter("password");
		if (StringUtil.isBlank(password)) {
			return responseMsg("0004", "USER");
		}
		Admin admin = new Admin();
		admin.setId(id);
		admin.setName(name);
		admin.setAge(age);
		admin.setSex(sex);
		admin.setPhone(phone);
		admin.setPassword(password);
		String retcode = adminService.saveUpdate(admin);
		return responseMsg(retcode, "USER");
	}
	
}
