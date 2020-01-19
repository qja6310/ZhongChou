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
	public ModelAndView toLogin(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
	
	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}
	
	@RequestMapping("/main")
	public ModelAndView main(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("main");
		Role user = (Role) request.getSession().getAttribute("user");
		return mav;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("main");
		request.getSession().removeAttribute("user");;
		return mav;
	}
	
}
