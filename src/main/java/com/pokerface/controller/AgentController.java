package com.pokerface.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pokerface.config.WebSecurityConfig;
import com.pokerface.model.Agent;
import com.pokerface.service.AgentService;
import com.pokerface.service.TopupService;
import com.pokerface.util.StringUtil;

@Controller
public class AgentController {
    
	@Autowired
    private AgentService agentService;
	
	@Autowired
    private TopupService topupService;
	
	//Retrieve all agents in the database
    @RequestMapping("/agents")
    public String list(Map<String, Object> model, HttpSession session) {
    	Agent agent = (Agent) session.getAttribute(WebSecurityConfig.LOGIN_USER);
        List<Agent> agents = agentService.findSubAgents(agent);
        model.put("agents", agents);
        return "agentList";
    }

    //Navigating to the new agent form
    @RequestMapping("/toAddAgent")
    public String registerForm(Model model) {
    	model.addAttribute("operType", "add");
        model.addAttribute("agent", new Agent());
        return "agentDetail";
    }

    //Pushing the new agent to the database
    @PostMapping("/addAgent")
    public String addAgent(Model model, @Valid @ModelAttribute Agent agent, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "add");
            return "agentDetail";
        } else {
        	Agent parentAgent = (Agent) session.getAttribute(WebSecurityConfig.LOGIN_USER);
        	agent.setParentAgentId(parentAgent.getLoginId());
        	agent.setLevel(parentAgent.getLevel() + 1);
        	agent.setCreateTime(new Date());
        	agent.setRoomCardCount(0L);
        	agent.setCoinCount(0L);
        	if(StringUtil.isEmpty(agent.getDiscount())){
        		agent.setDiscount("0");
        	}
            agentService.saveAgent(agent);
            return "redirect:/agents";
        }

    }
    
    @PostMapping("/checkAgent")
    @ResponseBody
    public Map<String, Object> checkAgent(String loginId, String name, String phone, HttpSession session) {
    	Map<String, Object> map = new HashMap<>();
    	map.put("loginIdExists", agentService.loginIdExists(loginId));
    	map.put("nameExists", agentService.nameExists(name));
    	map.put("phoneExists", agentService.phoneExists(phone));
    	return map;
    }

    //Navigating to the update agent form
    @RequestMapping("/agentDetail")
    public String toAgentDetail(Model model, Long id) {
    	model.addAttribute("operType", "update");
        Agent agent = agentService.findSingleAgent(id);
        model.addAttribute("agent", agent);
        return "agentDetail";
    }

    @PostMapping("/updateAgent")
    public String updateAgent(Model model, @Valid @ModelAttribute Agent agent, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "update");
            return "agentDetail";
        } else {
        	if(StringUtil.isEmpty(agent.getDiscount())){
        		agent.setDiscount("0");
        	}
        	Agent loginUser = (Agent) session.getAttribute(WebSecurityConfig.LOGIN_USER);
        	if(!loginUser.getLoginId().equals(agent.getLoginId())){
        		agent.setPwd(null);
        	}
            agentService.updateAgent(agent);
            return "redirect:/agents";
        }

    }
    
    @RequestMapping("/topupAgent")
    @ResponseBody
    public Map<String, Object> topupAgent(Model model, Long toAgentId, Long amount, HttpSession session) {
    	Map<String, Object> map = new HashMap<>();
    	if(amount <= 0){
    		map.put("success", false);
    		map.put("message", "充值金额不能小于0或者等于0");
    		return map;
    	}
    	Agent fromAgent = (Agent) session.getAttribute(WebSecurityConfig.LOGIN_USER);
    	if(!fromAgent.isAdmin() && fromAgent.getRoomCardCount() < amount){
    		map.put("success", false);
    		map.put("message", "您的房卡已不足，房卡数：" + fromAgent.getRoomCardCount());
    		return map;
    	}
    	Agent toAgent = agentService.findSingleAgent(toAgentId);
    	if(toAgent == null){
    		map.put("success", false);
    		map.put("message", "转入代理不存在或已被删除");
    		return map;
    	}
    	//int diffLevel = toAgent.getLevel() - fromAgent.getLevel();
    	//if(diffLevel != 1){
    	if(!fromAgent.getLoginId().equals(toAgent.getParentAgentId())){
    		map.put("success", false);
    		map.put("message", "只能给下级代理充值");
    		return map;
    	}
    	topupService.topupAgent(fromAgent, toAgent, amount);
    	map.put("success", true);
        return map;
    }

    //Removing the agent
    @GetMapping("/removeAgent")
    public String removeAgent(Long id) {
        Agent agent = agentService.findSingleAgent(id);
        if (agent != null) {
            agentService.removeAgent(id);
        }
        return "redirect:/agents";
    }
}
