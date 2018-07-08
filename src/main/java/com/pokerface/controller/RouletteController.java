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

import com.pokerface.model.Roulette;
import com.pokerface.model.User;
import com.pokerface.service.RouletteService;
import com.pokerface.service.UserService;
import com.pokerface.util.StringUtil;

@Controller
public class RouletteController {
    
	@Autowired
    private RouletteService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private MessageSource messageSource;
	
    @RequestMapping("/roulettes")
    public String list(Map<String, Object> model, HttpSession session) {
        List<Roulette> roulettes = service.roulettes();
        model.put("roulettes", roulettes);
        return "rouletteList";
    }
    
    @GetMapping("/rouletteDelete")
    public String baccaratDelete(Long id) {
    	Roulette roulette = service.rouletteDetail(id);
    	if(roulette != null){
    		service.removeRouletteRoom(id);
    	}
    	return "redirect:/roulettes";
    }
    
    @RequestMapping("/rouletteDetail")
    public String rouletteDetail(Model model, Long id) {
    	model.addAttribute("operType", "update");
        Roulette roulette = service.rouletteDetail(id);
        model.addAttribute("roulette", roulette);
        return "rouletteDetail";
    }
    
    @RequestMapping("/toAddRoulette")
    public String toAddRoulette(Model model) {
    	model.addAttribute("operType", "add");
        model.addAttribute("roulette", new Roulette());
        return "rouletteDetail";
    }
    
    @PostMapping("/checkRoulette")
    @ResponseBody
    public Map<String, Object> checkRoulette(String roomId, String channel, Long id, HttpSession session) {
    	Map<String, Object> map = new HashMap<>();
    	map.put("roomIdExists", service.roomIdExists(roomId, id));
    	map.put("channelExists", service.channelExists(channel, id));
    	return map;
    }
    
    @PostMapping("/checkRouletteAdminIds")
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
    
    @RequestMapping("/copyRoulette")
    public String copyRoom(Long id) {
        service.copyRoom(id);;
        return "redirect:/roulettes";
    }
    
    @PostMapping("/addRoulette")
    public String addRoulette(Model model, @Valid @ModelAttribute Roulette roulette, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "add");
            return "rouletteDetail";
        } else {
        	roulette.setCreateTime(new Date());
        	service.saveRoulette(roulette);
            return "redirect:/roulettes";
        }
    }
    
    @PostMapping("/updateRoulette")
    public String updateRoulette(Model model, @Valid @ModelAttribute Roulette roulette, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "update");
            return "rouletteDetail";
        } else {
        	Roulette db = service.rouletteDetail(roulette.getId());
        	roulette.setCreateTime(db.getCreateTime());
        	service.saveRoulette(roulette);
            return "redirect:/roulettes";
        }
    }
}
