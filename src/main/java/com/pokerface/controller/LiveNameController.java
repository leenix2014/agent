package com.pokerface.controller;

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

import com.pokerface.model.LiveName;
import com.pokerface.service.LiveNameService;

@Controller
public class LiveNameController {
    
	@Autowired
    private LiveNameService service;

    @RequestMapping("/liveNameDetail")
    public String liveNameDetail(Model model, Long id) {
    	model.addAttribute("operType", "update");
        LiveName liveName = service.liveNameDetail(id);
        model.addAttribute("liveName", liveName);
        return "liveNameDetail";
    }
    
    @RequestMapping("/toAddLiveName")
    public String toAddLiveName(Model model) {
    	model.addAttribute("operType", "add");
        model.addAttribute("liveName", new LiveName());
        return "liveNameDetail";
    }
    
    @RequestMapping("/liveName")
    public String liveNameList(Map<String, Object> model, HttpSession session) {
        List<LiveName> liveName = service.livename();
        model.put("liveName", liveName);
        return "liveNameList";
    }
    
    @GetMapping("/liveNameDelete")
    public String liveNameDelete(Long id) {
    	LiveName liveName = service.liveNameDetail(id);
    	if(liveName != null){
    		service.removeLiveName(id);
    	}
    	return "redirect:/liveName";
    }
    
    @PostMapping("/addLiveName")
    public String addLiveName(Model model, @Valid @ModelAttribute LiveName livename, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "add");
            return "anchorDetail";
        } else {
        	service.saveLiveName(livename);
            return "redirect:/liveName";
        }
    }
    
    @PostMapping("/updateLiveName")
    public String updateLiveName(Model model, @Valid @ModelAttribute LiveName update, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "update");
            return "liveNameDetail";
        } else {
        	service.saveLiveName(update);
            return "redirect:/liveName";
        }
    }
}
