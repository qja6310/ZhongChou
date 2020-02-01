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

import cn.com.newloading.bean.Hint;
import cn.com.newloading.bean.User;
import cn.com.newloading.bean.UserInfo;
import cn.com.newloading.service.HintService;
import cn.com.newloading.service.UserService;
import cn.com.newloading.utils.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

	@Autowired
	private UserService userService;

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
		User user = userService.doLogin(phone, password);
		if (user == null) {
			json.put("retCode", "0");
			json.put("retMsg", "登陆失败");
		} else {
			json.put("retCode", "1");
			json.put("retMsg", "登陆成功");
			request.getSession().setAttribute("user", user);
		}
		return json;
	}
	
	@RequestMapping("updateUser")
	public ModelAndView updateUser(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("userInfo");
		String id = request.getParameter("roleId");
		if(StringUtil.isBlank(id)) {
			mav = new ModelAndView("index");
		}
		User user = userService.queryUserInfo(id);
		model.addAttribute("user", user);
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
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setAge(age);
		user.setSex(sex);
		user.setPhone(phone);
		user.setPassword(password);
		String retcode = userService.saveUpdate(user);
		return responseMsg(retcode, "USER");
	}

	@RequestMapping("/addUser")
	@ResponseBody
	public JSONObject addUser(HttpServletRequest request) {
		JSONObject json = new JSONObject();
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
		User user = new User();
		user.setName(name);
		user.setAge(age);
		user.setSex(sex);
		user.setPhone(phone);
		user.setPassword(password);
		String retcode = userService.addUser(user);
		return responseMsg(retcode, "USER");
	}
	
	@RequestMapping("/userManage")
	public ModelAndView userManage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("userManage");
		return mav;
	}
	
	@RequestMapping("/getTotal")
	@ResponseBody
	public JSONObject getTotal(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");
		String status = request.getParameter("status");
		
		User user = new User();
		user.setName(userName);
		user.setSex(sex);
		user.setPhone(phone);
		user.setStatus(status);
		
		int total = userService.getTotal(user);
		JSONObject json = new JSONObject();
		json.put("total",total);
		return json;
	}
	
	@RequestMapping("/userManagePage")
	public ModelAndView userManagePage(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("userManagePage");
		String userName = request.getParameter("userName");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");
		String status = request.getParameter("status");
		String currPage = request.getParameter("currPage");
		String limit = request.getParameter("limit");
		
		UserInfo user = new UserInfo();
		user.setName(userName);
		user.setSex(sex);
		user.setPhone(phone);
		user.setStatus(status);
		user.setCurrent(Integer.valueOf(currPage));
		user.setLimit(Integer.valueOf(limit));
		user.setStart((Integer.valueOf(currPage) - 1) * Integer.valueOf(limit));
		
		List<User> userList = userService.queryUserByParams(user);
		model.addAttribute("userList", userList);
		return mav;
	}
	
	@RequestMapping("/editUser")
	@ResponseBody
	public JSONObject editUser(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isBlank(id)) {
			return responseMsg("0004", "USER");
		}
		String status = request.getParameter("status");
		if(StringUtil.isBlank(status)) {
			return responseMsg("0004", "USER");
		}
		User user = new User();
		user.setId(id);
		user.setStatus(status);
		String retcode = userService.editUser(user);
		return responseMsg(retcode,"USER");
	}
}
