package com.pokerface.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.pokerface.model.Baccarat;
import com.pokerface.model.User;
import com.pokerface.service.BaccaratService;
import com.pokerface.service.UserService;
import com.pokerface.util.StringUtil;

@Controller
public class BaccaratController {
    
	@Autowired
	private BaccaratService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private MessageSource messageSource;
	
    @RequestMapping("/baccarats")
    public String list(Map<String, Object> model, HttpSession session) {
        List<Baccarat> baccarats = service.baccarats();
        model.put("baccarats", baccarats);
        return "baccaratList";
    }
    
    @RequestMapping("/baccaratDetail")
    public String baccaratDetail(Model model, Long id) {
    	model.addAttribute("operType", "update");
    	Baccarat baccarat = service.baccaratDetail(id);
        model.addAttribute("baccarat", baccarat);
        return "baccaratDetail";
    }
    
    @RequestMapping("/toAddBaccarat")
    public String toAddBaccarat(Model model) {
    	model.addAttribute("operType", "add");
        model.addAttribute("baccarat", new Baccarat());
        return "baccaratDetail";
    }
    
    @PostMapping("/checkBaccarat")
    @ResponseBody
    public Map<String, Object> checkBaccarat(String roomId, String channel, Long id, HttpSession session) {
    	Map<String, Object> map = new HashMap<>();
    	map.put("roomIdExists", service.roomIdExists(roomId, id));
    	map.put("channelExists", service.channelExists(channel, id));
    	return map;
    }
    
    @PostMapping("/checkAdminIds")
    @ResponseBody
    public Map<String, Object> checkAdminIds(String adminIds, HttpSession session) {
    	Map<String, Object> map = new HashMap<>();
    	if(adminIds == null || StringUtil.isEmpty(adminIds.trim())){
    		map.put("success", true);
    		return map;
    	}
    	adminIds = adminIds.trim();
    	String[] parts = adminIds.split(",");
    	StringBuilder sb = new StringBuilder();
    	for(String part : parts){
    		part = part.trim();
    		User user = userService.findUser(part);
    		if(user == null){
    			sb.append(part).append(",");
    		}
    	}
    	if(sb.length() == 0){
    		map.put("success", true);
    		return map;
    	}
    	sb.deleteCharAt(sb.length() - 1);
    	sb.append(messageSource.getMessage("common.adminIds.null", null, (Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME)));
    	map.put("success", false);
    	map.put("message", sb.toString());
    	return map;
    }
    
    @RequestMapping("/copyBaccarat")
    public String copyRoom(Long id) {
        service.copyRoom(id);;
        return "redirect:/baccarats";
    }
    
    @PostMapping("/addBaccarat")
    public String addBaccarat(Model model, @Valid @ModelAttribute Baccarat baccarat, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "add");
            return "baccaratDetail";
        } else {
        	baccarat.setCreateTime(new Date());
        	service.saveBaccarat(baccarat);
            return "redirect:/baccarats";
        }
    }
    
    @GetMapping("/baccaratDelete")
    public String baccaratDelete(Long id) {
    	Baccarat baccarat = service.baccaratDetail(id);
    	if(baccarat != null){
    		service.removeBaccaratRoom(id);
    	}
    	return "redirect:/baccarats";
    }
    
    @PostMapping("/updateBaccarat")
    public String updateBaccarat(Model model, @Valid @ModelAttribute Baccarat baccarat, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "update");
            return "baccaratDetail";
        } else {
        	Baccarat db = service.baccaratDetail(baccarat.getId());
        	baccarat.setCreateTime(db.getCreateTime());
        	service.saveBaccarat(baccarat);
            return "redirect:/baccarats";
        }
    }
}
