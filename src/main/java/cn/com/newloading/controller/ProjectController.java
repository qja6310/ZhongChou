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
import cn.com.newloading.bean.AuditLog;
import cn.com.newloading.bean.Project;
import cn.com.newloading.bean.User;
import cn.com.newloading.service.AuditLogService;
import cn.com.newloading.service.ProjectService;
import cn.com.newloading.utils.StringUtil;

@Controller
@RequestMapping("/projects")
public class ProjectController extends BaseController {

	@Autowired
	private ProjectService proService;
	@Autowired
	private AuditLogService alService;
	
	@RequestMapping("/myProject")
	public ModelAndView myProject(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("myPro");
		String userId = request.getParameter("userId");
		if(StringUtil.isBlank(userId)) {
			mav = new ModelAndView("index");
		}
		Project pro = new Project();
		pro.setUserId(userId);
		List<Project> proList = proService.queryProjectByParams(pro);
		model.addAttribute("proList", proList);
		return mav;
	}
	
	@RequestMapping("/toEditPro")
	public ModelAndView toEditPro(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("editPro");
		String id = request.getParameter("id");
		if(StringUtil.isBlank(id)) {
			mav = new ModelAndView("index");
		}
		//获取审核详情
		List<AuditLog> alList = alService.queryAuditLogByProId(id);
		model.addAttribute("al", alList.get(0));
		//获取项目
		Project pro = proService.queryProjectById(id);
		model.addAttribute("pro", pro);
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
		String disease = request.getParameter("disease");
		if(StringUtil.isBlank(disease)) {
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
		project.setDisease(disease);
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
		String startApplyTime = request.getParameter("startApplyTime");
		String endApplyTime = request.getParameter("endApplyTime");
		Project pro = new Project();
		pro.setUserName(userName);
		pro.setPatientName(patientName);
		pro.setPatientSex(patientSex);
		pro.setPhone(phone);
		pro.setStatus(status);
		pro.setStartApplyTime(startApplyTime);
//		if(at.length == 2) {
//			pro.setEndApplyTime(at[1]);
//		}else {
			pro.setEndApplyTime(endApplyTime);
//		}
		
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
		String startApplyTime = request.getParameter("startApplyTime");
		String endApplyTime = request.getParameter("endApplyTime");
		String details = request.getParameter("details");
		String currPage = request.getParameter("currPage");
		String limit = request.getParameter("limit");
		Project pro = new Project();
		pro.setUserName(userName);
		pro.setPatientName(patientName);
		pro.setPatientSex(patientSex);
		pro.setPhone(phone);
		pro.setStatus(status);
		pro.setStartApplyTime(startApplyTime);
		pro.setEndApplyTime(endApplyTime);
		pro.setDetails(details);
		pro.setCurrent(Integer.valueOf(currPage));
		pro.setLimit(Integer.valueOf(limit));
		pro.setStart((Integer.valueOf(currPage) - 1) * Integer.valueOf(limit));
		List<Project> proList = proService.queryProjectByParams(pro);
		model.addAttribute("proList", proList);
		return mav;
	}
	
	@RequestMapping("/toAudit")
	public ModelAndView toAudit(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("auditPro");
		String id = request.getParameter("id");
		Project pro = proService.queryProjectById(id);
		model.addAttribute("pro", pro);
		return mav;
	}
	
	@RequestMapping("audit")
	@ResponseBody
	public JSONObject audit(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isBlank(id)) {
			return responseMsg("0002","PRO");
		}
		String status = request.getParameter("status");
		if(StringUtil.isBlank(status)) {
			return responseMsg("0002","PRO");
		}
		String explain = request.getParameter("explain");
		if("1".equals(status) && StringUtil.isBlank(explain)) {
			return responseMsg("0011","PRO");
		}
		Admin admin = (Admin) request.getSession().getAttribute("user");
		if(admin == null) {
			return responseMsg("0005","USER");
		}
		AuditLog auditLog = new AuditLog();
		auditLog.setAdminId(admin.getId());
		auditLog.setProjectId(id);
		auditLog.setStatus(status);
		auditLog.setExplains(explain);
		String retcode = proService.auditProject(auditLog);
		return responseMsg(retcode,"PRO");
	}
	
	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("projects");
		return mav;
	}
	
	@RequestMapping("/getCount")
	@ResponseBody
	public JSONObject getCount(HttpServletRequest request) {
		String details = request.getParameter("details");
		Project pro = new Project();
		pro.setDetails(details);
		pro.setStatus("2");
		int total = proService.queryProjectCountByParams(pro);
		JSONObject json = new JSONObject();
		json.put("total",total);
		return json;
	}
	
	@RequestMapping("/projectsPage")
	public ModelAndView projectsPage(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("projectsPage");
		String details = request.getParameter("details");
		String currPage = request.getParameter("currPage");
		String limit = request.getParameter("limit");
		Project pro = new Project();
		pro.setStatus("2");
		pro.setDetails(details);
		pro.setCurrent(Integer.valueOf(currPage));
		pro.setLimit(Integer.valueOf(limit));
		pro.setStart((Integer.valueOf(currPage) - 1) * Integer.valueOf(limit));
		List<Project> proList = proService.queryProjectByParams(pro);
		model.addAttribute("proList", proList);
		return mav;
	}
	
	@RequestMapping("details")
	public ModelAndView details(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("proDetails");
		String id = request.getParameter("id");
		if(StringUtil.isBlank(id)) {
			mav = new ModelAndView("index");
		}
		String flag = request.getParameter("flag");
		model.addAttribute("flag", flag);
		Project pro = proService.queryProjectById(id);
		model.addAttribute("pro", pro);
		return mav;
	}
	@RequestMapping("details2")
	public ModelAndView details2(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("proDetails2");
		String id = request.getParameter("id");
		if(StringUtil.isBlank(id)) {
			mav = new ModelAndView("index");
		}
		String money = (String) request.getSession().getAttribute("money");
		Project pro = proService.queryProjectById(id);
		if(StringUtil.isBlank(money)) {
			model.addAttribute("pro", pro);
			model.addAttribute("flag", "1");
			mav = new ModelAndView("proDetails");
		}
		model.addAttribute("pro", pro);
		model.addAttribute("flag", "1");
		model.addAttribute("retCode", request.getParameter("retCode"));
		model.addAttribute("retMsg", request.getParameter("retMsg"));
		model.addAttribute("money", request.getParameter("money"));
		request.getSession().removeAttribute("money");
		return mav;
	}
	
	@RequestMapping("/doEditProject")
	@ResponseBody
	public JSONObject doEditProject(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isBlank(id)) {
			return responseMsg("0002","PRO");
		}
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
		project.setId(id);
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
		String retcode = proService.editProject(project);
		return responseMsg(retcode,"PRO");
	}
}
