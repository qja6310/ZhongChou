package cn.com.newloading.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.newloading.bean.Role;
import cn.com.newloading.bean.User;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public String toLogin(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView("index");
//		return mav;
		return "index";
	}
	
	@RequestMapping("/register")
	public String register(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView("register");
//		return mav;
		return "register";
	}
	
	@RequestMapping("/main")
	public String main(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView("main");
		Role user = (Role) request.getSession().getAttribute("user");
//		return mav;
		return "main";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView("main");
		request.getSession().removeAttribute("user");;
//		return mav;
		return "main";
	}
	
}
