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
import cn.com.newloading.bean.News;
import cn.com.newloading.service.HintService;
import cn.com.newloading.service.NewsService;
import cn.com.newloading.utils.StringUtil;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {
	
	@Autowired
	private NewsService newsService;

	@RequestMapping("/newsManage")
	public ModelAndView newsManage(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("newsManage");
		String keyword = request.getParameter("keyword");
		int total = newsService.queryNewsTotalByKeyword(keyword);
		model.addAttribute("total", total);
		return mav;
	}
	
	@RequestMapping("/newsManagePage")
	public ModelAndView newsManagePage(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("newsManagePage");
		String keyword = request.getParameter("keyword");
		String currPage = request.getParameter("currPage");
		String limit = request.getParameter("limit");
		List<News> newsList = newsService.queryNewsByKeyword(keyword,currPage,limit);
		model.addAttribute("newsList", newsList);
//		int total = newsService.queryNewsTotalByKeyword(keyword);
//		model.addAttribute("total", total);
		return mav;
	}
	
	@RequestMapping("/issueNews")
	public ModelAndView issueNews(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("issueNews");
		return mav;
	}
	
	@RequestMapping("/addNews")
	@ResponseBody
	public JSONObject addNews(HttpServletRequest request) {
		String title = request.getParameter("title");
		if(StringUtil.isBlank(title)) {
			return responseMsg("0002", "NEWS");
		}
		String content = request.getParameter("content");
		if(StringUtil.isBlank(content)) {
			return responseMsg("0002", "NEWS");
		}
		Admin admin = (Admin) request.getSession().getAttribute("user");
		if(admin == null) {
			return responseMsg("0005", "USER");
		}
		News news = new News();
		news.setTitle(title);
		news.setContent(content);
		news.setAdminId(admin.getId());
		String retcode = newsService.addNews(news);
		return responseMsg(retcode,"NEWS");
	}
	
	@RequestMapping("/getTotal")
	@ResponseBody
	public JSONObject getTotal(HttpServletRequest request) {
		String keyword = request.getParameter("keyword");
		int total = newsService.queryNewsTotalByKeyword(keyword);
		JSONObject json = new JSONObject();
		json.put("total",total);
		return json;
	}
	
	@RequestMapping("/delNews")
	@ResponseBody
	public JSONObject delNews(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isBlank(id)) {
			return responseMsg("0002", "NEWS");
		}
		String retcode = newsService.delNews(id);
		return responseMsg(retcode,"NEWS");
	}
	
	@RequestMapping("/editNews")
	public ModelAndView editNews(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("editNews");
		String id = request.getParameter("id");
		if(StringUtil.isBlank(id)) {
			mav = new ModelAndView("newsManage");
		}
		News news = newsService.queryNewsById(id);
		model.addAttribute("news", news);
		return mav;
	}
	
	@RequestMapping("/doEditNews")
	@ResponseBody
	public JSONObject editNews(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(StringUtil.isBlank(id)) {
			return responseMsg("0002", "NEWS");
		}
		String title = request.getParameter("title");
		if(StringUtil.isBlank(title)) {
			return responseMsg("0002", "NEWS");
		}
		String content = request.getParameter("content");
		if(StringUtil.isBlank(content)) {
			return responseMsg("0002", "NEWS");
		}
		Admin admin = (Admin) request.getSession().getAttribute("user");
		if(admin == null) {
			return responseMsg("0005", "USER");
		}
		News news = new News();
		news.setId(id);
		news.setTitle(title);
		news.setContent(content);
		news.setAdminId(admin.getId());
		String retcode = newsService.editNews(news);
		return responseMsg(retcode,"NEWS");
	}
	
	@RequestMapping("/")
	public ModelAndView news(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("news");
		return mav;
	}
	
	@RequestMapping("/newsPage")
	public ModelAndView newsPage(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("newsPage");
		String keyword = request.getParameter("keyword");
		List<News> newsList = newsService.queryNewsByKeyword2(keyword);
		model.addAttribute("newsList", newsList);
		return mav;
	}
	
	@RequestMapping("/newsDetails")
	public ModelAndView newsDetails(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("newsDetails");
		String id = request.getParameter("id");
		if(StringUtil.isBlank(id)) {
			mav = new ModelAndView("newsManage");
		}
		News news = newsService.queryNewsById(id);
		model.addAttribute("news", news);
		return mav;
	}
	
}
