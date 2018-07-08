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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.pokerface.model.Anchor;
import com.pokerface.model.User;
import com.pokerface.service.AnchorService;
import com.pokerface.service.UserService;
import com.pokerface.util.StringUtil;

import net.sf.json.JSONObject;

@Controller
public class AnchorController {
    
	@Autowired
    private AnchorService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private MessageSource messageSource;

    @RequestMapping("/anchors")
    public String list(Map<String, Object> model, HttpSession session) {
        List<Anchor> anchors = service.anchors();
        model.put("anchors", anchors);
        return "anchorList";
    }
    
    @RequestMapping("/anchorDetail")
    public String anchorDetail(Model model, Long id) {
    	model.addAttribute("operType", "update");
        Anchor anchor = service.anchorDetail(id);
        model.addAttribute("anchor", anchor);
        return "anchorDetail";
    }
    
    @RequestMapping("/toAddAnchor")
    public String toAddAnchor(Model model) {
    	model.addAttribute("operType", "add");
        model.addAttribute("anchor", new Anchor());
        return "anchorDetail";
    }
    
    @PostMapping("/checkAnchorAdminIds")
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
    
    @PostMapping("/checkAnchor")
    @ResponseBody
    public Map<String, Object> checkAnchor(Long anchorId, Long id, HttpSession session) {
    	Map<String, Object> map = new HashMap<>();
    	map.put("anchorIdExists", service.existsAnchor(anchorId, id));
    	return map;
    }
    
    @PostMapping("/addAnchor")
    public String addAnchor(Model model, @Valid @ModelAttribute Anchor anchor, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "add");
            return "anchorDetail";
        } else {
        	anchor.setCreateTime(new Date());
        	service.saveAnchor(anchor);
            return "redirect:/anchors";
        }
    }
    
    @PostMapping("/updateAnchor")
    public String updateAnchor(Model model, @Valid @ModelAttribute Anchor update, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "update");
            return "anchorDetail";
        } else {
        	Anchor anchor = service.anchorDetail(update.getId());
        	update.setCreateTime(anchor.getCreateTime());
        	service.saveAnchor(update);
            return "redirect:/anchors";
        }
    }
    
    @RequestMapping("/anchorStart")
    @ResponseBody
    public Map<String, Object> anchorStart(Model model, Long anchorId) {
    	Map<String, Object> map = new HashMap<>();
        if(service.isLiving(anchorId)){
        	map.put("success", false);
        	map.put("message", "开播成功。");
        } else {
        	service.anchorStart(anchorId);
        	map.put("success", true);
        }
        return map;
    }
    
    @RequestMapping("/anchorStop")
    @ResponseBody
    public Map<String, Object> anchorStop(Model model, Long anchorId) {
    	Map<String, Object> map = new HashMap<>();
        service.anchorStop(anchorId);
        map.put("success", true);
        return map;
    }
}
