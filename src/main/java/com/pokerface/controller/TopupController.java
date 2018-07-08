package com.pokerface.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pokerface.config.WebSecurityConfig;
import com.pokerface.dto.IEntity;
import com.pokerface.dto.TopupDto;
import com.pokerface.model.User;
import com.pokerface.service.TopupService;
import com.pokerface.service.UserService;
import com.pokerface.util.HttpUtil;

@Controller
public class TopupController {
    
	@Autowired
    private TopupService topupService;
	
	@Autowired
	private UserService userService;
	
    @RequestMapping("/topupList")
    public String list(Map<String, Object> model, HttpSession session) {
    	String loginType = (String) session.getAttribute(WebSecurityConfig.LOGIN_TYPE);
    	boolean isAgent = WebSecurityConfig.LOGIN_TYPE_AGENT.equals(loginType);
    	IEntity user = (IEntity) session.getAttribute(WebSecurityConfig.LOGIN_USER);
        List<TopupDto> topups = topupService.getTopup(user.getLoginId(), isAgent);
        model.put("topups", topups);
        return "topupList";
    }
    
    @RequestMapping("/topupUser")
    public String topupUser(Map<String, Object> model, HttpSession session) {
        return "topupUser";
    }
    
    @RequestMapping("/topupUserSubmit")
    @ResponseBody
    public Map<String, Object> topupUserSubmit(Model model, String toUserId, Long amount, HttpSession session) {
    	Map<String, Object> map = new HashMap<>();
    	if(amount <= 0){
    		map.put("success", false);
    		map.put("message", "充值金额不能小于0或者等于0");
    		return map;
    	}
    	IEntity loginUser = (IEntity) session.getAttribute(WebSecurityConfig.LOGIN_USER);
    	if(!loginUser.isAdmin() && loginUser.getRoomCardCount() < amount){
    		map.put("success", false);
    		map.put("message", "您的房卡已不足，房卡数：" + loginUser.getRoomCardCount());
    		return map;
    	}
    	if(loginUser.getLoginId().equals(toUserId)){
    		map.put("success", false);
    		map.put("message", "不能给自己充值");
    		return map;
    	}
    	User toUser = userService.findTopupUser(toUserId);
    	if(toUser == null){
    		map.put("success", false);
    		map.put("message", "转入用户不存在");
    		return map;
    	}
    	topupService.topupUser(loginUser, toUser, amount);
    	map.put("success", true);
    	HttpUtil.sendGet(HttpUtil.getServerPrefix()+"/agent?userId=" + toUser.getLoginId());
        return map;
    }

}
