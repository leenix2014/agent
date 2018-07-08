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
import com.pokerface.model.Agent;
import com.pokerface.model.User;
import com.pokerface.model.Withdraw;
import com.pokerface.service.AgentService;
import com.pokerface.service.CoinService;
import com.pokerface.service.UserService;
import com.pokerface.util.HttpUtil;


@Controller
public class CoinController {
    
	@Autowired
    private CoinService coinService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AgentService agentService;
	
    @RequestMapping("/withdrawList")
    public String list(Map<String, Object> model, String type, HttpSession session) {
    	Agent agent = (Agent) session.getAttribute(WebSecurityConfig.LOGIN_USER);
        List<Withdraw> withdraws = coinService.getWithdraw(agent.getLoginId(), type);
        model.put("isAgent", "agent".equals(type));
        model.put("withdraws", withdraws);
        return "withdrawList";
    }
	
    @RequestMapping("/withdraw")
    public String withdraw(Map<String, Object> model, boolean isAgent, HttpSession session) {
    	model.put("isAgent", isAgent);
        return "withdraw";
    }
    
    @RequestMapping("/withdrawSubmit")
    @ResponseBody
    public Map<String, Object> withdrawSubmit(Model model, String toUserId, boolean isAgent, Long amount, HttpSession session) {
    	Map<String, Object> map = new HashMap<>();
    	if(amount <= 0){
    		map.put("success", false);
    		map.put("message", "提现金额不能小于0或者等于0");
    		return map;
    	}
    	Agent loginUser = (Agent) session.getAttribute(WebSecurityConfig.LOGIN_USER);
    	if(isAgent){
    		Agent toAgent = agentService.findAgent(toUserId);
    		if(toAgent == null){
    			map.put("success", false);
    			map.put("message", "提现代理不存在");
    			return map;
    		}
    		if(!loginUser.getLoginId().equals(toAgent.getParentAgentId())){
    			map.put("success", false);
    			map.put("message", "只能给本人的下级代理提现");
    			return map;
    		}
    		if(toAgent.getLoginId().equals(loginUser.getLoginId())){
    			map.put("success", false);
    			map.put("message", "不能给自己提现！");
    			return map;
    		}
    		if(toAgent.getCoinCount() < amount){
    			map.put("success", false);
    			map.put("message", "提现代理的金币不足，金币数：" + toAgent.getCoinCount());
    			return map;
    		}
    		coinService.withdrawAgent(loginUser, toAgent, amount);
    		map.put("success", true);
	        return map;
    	} else{
	    	User toUser = userService.findUser(toUserId);
	    	if(toUser == null){
	    		map.put("success", false);
	    		map.put("message", "提现用户不存在");
	    		return map;
	    	}
	    	if(toUser.getCoinCount() < amount){
	    		map.put("success", false);
	    		map.put("message", "提现用户的金币不足，金币数：" + toUser.getCoinCount());
	    		return map;
	    	}
	    	coinService.withdraw(loginUser, toUser, amount);
	    	map.put("success", true);
	    	HttpUtil.sendGet(HttpUtil.getServerPrefix()+"/agent?type=coin&amount="+amount+"&userId=" + toUser.getLoginId());
	        return map;
    	}
    }

}
