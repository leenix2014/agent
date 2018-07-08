package com.pokerface.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pokerface.config.WebSecurityConfig;
import com.pokerface.model.User;
import com.pokerface.service.UserService;

@Controller
public class UserController {
    
	@Autowired
    private UserService userService;
	
    @RequestMapping("/roomCardHis")
    public String roomCardHistory(Map<String, Object> model, HttpSession session) {
    	User user = (User) session.getAttribute(WebSecurityConfig.LOGIN_USER);
    	model.put("roomCardBills", userService.findRoomCardBill(user.getLoginId()));
        return "roomCardHis";
    }

}
