package com.pokerface.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pokerface.config.WebSecurityConfig;
import com.pokerface.model.Agent;
import com.pokerface.model.User;
import com.pokerface.service.AgentService;
import com.pokerface.service.UserService;
import com.pokerface.util.MD5;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class InterfaceController {

	@Autowired
    private AgentService agentService;
	
	@Autowired
    private UserService userService;
	
	private static Map<String, Map<String, Object>> sessions = new HashMap<String, Map<String, Object>>();
	
	@RequestMapping("/intef/login")
	@ResponseBody
    public String login(String loginid, String pwd) {
		JSONObject json = new JSONObject();
		Map<String, Object> sessionData = new HashMap<String, Object>();
		sessionData.put(WebSecurityConfig.LOGIN_ID, loginid);
		Agent agent = agentService.findAgent(loginid);
		User user = null;
		if(agent == null){
			user = userService.findUser(loginid);
		}
		
		if(agent == null && user == null){
			json.put("code", 1);//User not exist!
			json.put("errMsg", "User not exist!");
            return json.toString();
		}
		
		if((agent != null && !MD5.encode(agent.getPwd()).equals(pwd))
				|| (user != null && !MD5.encode(user.getPwd()).equals(pwd))){
			json.put("code", 2);//Password error!
			json.put("errMsg", "Password error!");
            return json.toString();
		}
		
		if(agent != null){
			json.put("code", 0);
			json.put("userType", "agent");
			sessionData.put(WebSecurityConfig.LOGIN_TYPE, WebSecurityConfig.LOGIN_TYPE_AGENT);
			sessionData.put(WebSecurityConfig.LOGIN_USER, agent);
		}
		
		if(user != null){
			json.put("code", 0);
			json.put("userType", "user");
			sessionData.put(WebSecurityConfig.LOGIN_TYPE, WebSecurityConfig.LOGIN_TYPE_USER);
			sessionData.put(WebSecurityConfig.LOGIN_USER, user);
		}
		
		String sessionKey = UUID.randomUUID().toString();
		sessions.put(sessionKey, sessionData);
		json.put("sessionKey", sessionKey);
		return json.toString();
	}
	
	@RequestMapping("/intef/subagent")
	@ResponseBody
    public String getSubAgents(String sessionKey) {
		JSONObject result = new JSONObject();
		Map<String, Object> sessionData = sessions.get(sessionKey);
		if(sessionData == null){
			result.put("code", 1);
			result.put("errMsg", "Please login first!");
			return result.toString();
		}
		Agent agent = (Agent) sessionData.get(WebSecurityConfig.LOGIN_USER);
		List<Agent> subAgents = agentService.findSubAgents(agent);
		JSONArray jArray = new JSONArray();
		for(Agent subAgent : subAgents){
			if(subAgent == null){
				continue;
			}
			jArray.add(subAgent.toJson());
		}
		result.put("subagent", jArray);
		return result.toString();
	}
	
	@RequestMapping("/intef/logout")
	@ResponseBody
    public String logout(String sessionKey) {
		JSONObject result = new JSONObject();
		sessions.remove(sessionKey);
		result.put("code", 0);
		return result.toString();
	}
}
