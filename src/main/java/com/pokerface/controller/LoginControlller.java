package com.pokerface.controller;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.pokerface.config.WebSecurityConfig;
import com.pokerface.dto.IEntity;
import com.pokerface.model.Agent;
import com.pokerface.model.User;
import com.pokerface.service.AgentService;
import com.pokerface.service.UserService;
import com.pokerface.util.MD5;

@Controller
public class LoginControlller {
	
	@Autowired
	DefaultKaptcha captchaProducer;
	
	@Autowired
    private MessageSource messageSource;
	
	@Autowired
    private AgentService agentService;
	
	@Autowired
    private UserService userService;
	
	@RequestMapping("/")
    public String index(Map<String, Object> model) {
        return "login";
    }
	
	@RequestMapping("/toLogin")
    public String toLogin(Map<String, Object> model) {
        return "login";
    }
	
	@RequestMapping("/getVerifyCode")
    public String getVerifyCode(HttpServletRequest request,  
        HttpServletResponse response, HttpSession session) throws Exception {  
        response.setDateHeader("Expires", 0);  
        response.setHeader("Cache-Control",  
                "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg");  
  
        String capText = captchaProducer.createText();  
        System.out.println("capText: " + capText);  
  
        session.setAttribute(WebSecurityConfig.VERIFY_CODE, capText);
  
        BufferedImage bi = captchaProducer.createImage(capText);  
        ServletOutputStream out = response.getOutputStream();  
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();  
        } finally {  
            out.close();  
        }  
        return null;  
    }
	
	@RequestMapping("/login")
	@ResponseBody
    public Map<String, Object> login(String username, String password, String language, String checkcode, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		String verifyCode = (String)session.getAttribute(WebSecurityConfig.VERIFY_CODE);
		if(!checkcode.equals(verifyCode)){
			map.put("success", false);
            map.put("message", getMessage("login.validatecode.error", session));
            return map;
		}
		Agent agent = agentService.findAgent(username);
		User user = null;
		if(agent == null){
			user = userService.findUser(username);
		}
		
		if(agent == null && user == null){
			map.put("success", false);
            map.put("message", getMessage("login.user.not.exist", session));
            return map;
		}
		
		Locale locale = "en_US".equals(language) ? Locale.US : Locale.SIMPLIFIED_CHINESE;
		System.out.println("language:" + locale.toString());
		if(agent != null && MD5.encode(agent.getPwd()).equals(password)){
			map.put("success", true);
			session.setAttribute(WebSecurityConfig.LOGIN_TYPE, WebSecurityConfig.LOGIN_TYPE_AGENT);
			session.setAttribute(WebSecurityConfig.LOGIN_USER, agent);
			session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
	        return map;
		}
		
		if(user != null && MD5.encode(user.getPwd()).equals(password)){
			map.put("success", true);
			session.setAttribute(WebSecurityConfig.LOGIN_TYPE, WebSecurityConfig.LOGIN_TYPE_USER);
			session.setAttribute(WebSecurityConfig.LOGIN_USER, user);
			session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
	        return map;
		}
		
		map.put("success", false);
        map.put("message", getMessage("login.password.error", session));
        return map;
    }
	
	private String getMessage(String code, HttpSession session){
		return messageSource.getMessage(code, null, (Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
	}
	
	@RequestMapping("/menu")
    public String menu(Map<String, Object> model, HttpSession session) {
        return "menu";
    }
	
	@RequestMapping("/logout")
    public String logout(Map<String, Object> model, HttpSession session) {
		session.removeAttribute(WebSecurityConfig.LOGIN_TYPE);
		session.removeAttribute(WebSecurityConfig.LOGIN_USER);
        return "redirect:/";
    }

	@RequestMapping("/chgPwd")
    public String toChangePwd(Map<String, Object> model, HttpSession session) {
        return "chgPwd";
    }
	
	@RequestMapping("/chgPwdSubmit")
    @ResponseBody
    public Map<String, Object> changePwd(String oldPwd, String newPwd, HttpSession session) {
    	Map<String, Object> map = new HashMap<>();
    	IEntity loginUser = (IEntity) session.getAttribute(WebSecurityConfig.LOGIN_USER);
    	if(!MD5.encode(loginUser.getPwd()).equals(oldPwd)){
    		map.put("success", false);
    		map.put("message", "原密码错误");
    		return map;
    	}
    	if(loginUser instanceof Agent){
    		agentService.changePwd(loginUser.getId(), newPwd);
    	} else {
    		userService.changePwd(loginUser.getId(), newPwd);
    	}
    	loginUser.setPwd(newPwd);//更新Session数据
    	map.put("success", true);
        return map;
    }
}
