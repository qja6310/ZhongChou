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
	
	
	//项目管理
	@RequestMapping("/projectManage")
	public ModelAndView projectManage(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("projectManage");
		Project pro = new Project();
		int total = proService.queryProjectCountByParams(pro);
		model.addAttribute("total", total);
		return mav;
	}
	
	@RequestMapping("/getTotal")
	@ResponseBody
	public JSONObject getTotal(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String patientName = request.getParameter("patientName");
		String patientSex = request.getParameter("patientSex");
		String phone = request.getParameter("phone");
		String status = request.getParameter("status");
		String details = request.getParameter("details");
		String applyTime = request.getParameter("applyTime");
		String[] at = applyTime.split("~");
//		String currPage = request.getParameter("currPage");
//		String limit = request.getParameter("limit");
		Project pro = new Project();
		pro.setUserName(userName);
		pro.setPatientName(patientName);
		pro.setPatientSex(patientSex);
		pro.setPhone(phone);
		pro.setStatus(status);
		pro.setStartApplyTime(at[0]);
		if(at.length == 2) {
			pro.setEndApplyTime(at[1]);
		}else {
			pro.setEndApplyTime(null);
		}
		
		pro.setDetails(details);
		int total = proService.queryProjectCountByParams(pro);
		JSONObject json = new JSONObject();
		json.put("total",total);
		return json;
	}
	
	@RequestMapping("/projectManagePage")
	public ModelAndView projectManagePage(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("projectManagePage");
		String userName = request.getParameter("userName");
		String patientName = request.getParameter("patientName");
		String patientSex = request.getParameter("patientSex");
		String phone = request.getParameter("phone");
		String status = request.getParameter("status");
		String applyTime = request.getParameter("applyTime");
		String[] at = applyTime.split("~");
		String details = request.getParameter("details");
		String currPage = request.getParameter("currPage");
		String limit = request.getParameter("limit");
		Project pro = new Project();
		pro.setUserName(userName);
		pro.setPatientName(patientName);
		pro.setPatientSex(patientSex);
		pro.setPhone(phone);
		pro.setStatus(status);
		pro.setStartApplyTime(at[0]);
		if(at.length == 2) {
			pro.setEndApplyTime(at[1]);
		}else {
			pro.setEndApplyTime(null);
		}
		pro.setDetails(details);
		pro.setCurrent(Integer.valueOf(currPage));
		pro.setLimit(Integer.valueOf(limit));
		pro.setStart((Integer.valueOf(currPage) - 1) * Integer.valueOf(limit));
		List<Project> proList = proService.queryProjectByParams(pro);
		model.addAttribute("proList", proList);
		return mav;
	}
	
}
