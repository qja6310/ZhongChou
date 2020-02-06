package cn.com.newloading.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import cn.com.newloading.alipay.AlipayConfig;
import cn.com.newloading.bean.User;
import cn.com.newloading.service.ProjectService;
import cn.com.newloading.utils.StringUtil;
import cn.com.newloading.utils.TimeUtil;


@Controller
//@RequestMapping("/donation")
public class DonationController extends BaseController {
	
	@Autowired
	private ProjectService proService;

	/*
	 * 充值业务跳转转账jsp
	 */
	@RequestMapping("/toDonation")
	public void toDonation(HttpServletRequest req, HttpServletResponse response) throws Exception {
		String money = req.getParameter("money");
		req.getSession().setAttribute("money", money);
		
		String proId = req.getParameter("proId");
		req.getSession().setAttribute("proId", proId);
		
		/*流水号*/
		String seqNum = TimeUtil.seqNum();
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
			
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = seqNum;
		//付款金额，必填
		String total_amount = money;
		//订单名称，必填
		String subject = "捐款";
		//商品描述，可空
		String body = "";
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		
		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		
		response.setCharacterEncoding("UTF-8");//设置将字符以"UTF-8"编码输出到客户端浏览器
		//通过设置响应头控制浏览器以UTF-8的编码显示数据
		response.setHeader("content-type", "text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(result);
	}
	
	@RequestMapping("/callBack")
	public ModelAndView callBack(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("main");
		String proId = (String) request.getSession().getAttribute("proId");
		if(StringUtil.isBlank(proId)) {
			mav = new ModelAndView("index");
			return mav;
		}
		String money = (String) request.getSession().getAttribute("money");
//		if(StringUtil.isBlank(money)) {
//			mav = new ModelAndView("index");
//			return mav;
//		}
		User user = (User) request.getSession().getAttribute("user");
		if(StringUtil.isBlank(money)) {
			model.addAttribute("jump", "donation");
			model.addAttribute("proId",proId);
			return mav;
		}
		String retcode = proService.donation(proId, money,user.getId());
		JSONObject retJson = responseMsg(retcode,"PRO");
		model.addAttribute("jump", "donation");
		model.addAttribute("retCode", retJson.get("retCode"));
		model.addAttribute("retMsg", retJson.get("retMsg"));
		model.addAttribute("money",money);
		model.addAttribute("proId",proId);
		return mav;
	}
}
