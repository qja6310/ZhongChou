package cn.com.newloading.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Project;
import cn.com.newloading.bean.User;
import cn.com.newloading.service.ProjectService;
import cn.com.newloading.utils.StringUtil;

@Controller
@RequestMapping("/projects")
public class ProjectController extends BaseController {

	@Autowired
	private ProjectService proService;
	
	@RequestMapping("/myProject")
	public ModelAndView myProject(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("myProject");
		return mav;
	}
	
	@RequestMapping("/applyProject")
	public ModelAndView applyProject(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("applyProject");
		return mav;
	}
	
	@RequestMapping("/doAddProject")
	@ResponseBody
	public JSONObject doAddProject(HttpServletRequest request) {
		String name = request.getParameter("name");
		if(StringUtil.isBlank(name)) {
			return responseMsg("0002","PRO");
		}
		String age = request.getParameter("age");
		if(StringUtil.isBlank(age)) {
			return responseMsg("0002","PRO");
		}
		String sex = request.getParameter("sex");
		if(StringUtil.isBlank(sex)) {
			return responseMsg("0002","PRO");
		}
		String phone = request.getParameter("phone");
		if(StringUtil.isBlank(phone)) {
			return responseMsg("0002","PRO");
		}
		String identityNum = request.getParameter("identityNum");
		if(StringUtil.isBlank(identityNum)) {
			return responseMsg("0002","PRO");
		}
		String targetMoney = request.getParameter("targetMoney");
		if(StringUtil.isBlank(targetMoney)) {
			return responseMsg("0002","PRO");
		}
		String hospital = request.getParameter("hospital");
		if(StringUtil.isBlank(hospital)) {
			return responseMsg("0002","PRO");
		}
		String endTime = request.getParameter("endTime");
		if(StringUtil.isBlank(endTime)) {
			return responseMsg("0002","PRO");
		}
		String nowAddress = request.getParameter("nowAddress");
		if(StringUtil.isBlank(nowAddress)) {
			return responseMsg("0002","PRO");
		}
		String details = request.getParameter("details");
		if(StringUtil.isBlank(details)) {
			return responseMsg("0002","PRO");
		}
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			return responseMsg("0002","PRO");
		}
		Project project = new Project();
		project.setUserId(user.getId());
		project.setPatientName(name);
		project.setPatientAge(age);
		project.setPatientSex(sex);
		project.setPhone(phone);
		project.setEndTime(endTime);
		project.setIdentityNum(identityNum);
		project.setHospital(hospital);
		project.setTargetMoney(targetMoney);
		project.setNowAddress(nowAddress);
		project.setDetails(details);
		String retcode = proService.addProject(project);
		return responseMsg(retcode,"PRO");
	}
	
}
