package com.pokerface.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pokerface.model.CommonConfig;
import com.pokerface.service.CommonConfigService;

@Controller
public class CommonConfigController {
    
	@Autowired
	private CommonConfigService service;
	
    @RequestMapping("/commonConfigs")
    public String list(Map<String, Object> model, HttpSession session) {
        List<CommonConfig> commonConfigs = service.commonConfigs();
        model.put("commonConfigs", commonConfigs);
        return "commonConfigList";
    }
    
    @RequestMapping("/commonConfigDetail")
    public String commonConfigDetail(Model model, Long id) {
    	model.addAttribute("operType", "update");
        CommonConfig commonConfig = service.commonConfigDetail(id);
        model.addAttribute("commonConfig", commonConfig);
        return "commonConfigDetail";
    }
    
    @RequestMapping("/toAddCommonConfig")
    public String toAddCommonConfig(Model model) {
    	model.addAttribute("operType", "add");
        model.addAttribute("commonConfig", new CommonConfig());
        return "commonConfigDetail";
    }
    
    @PostMapping("/addCommonConfig")
    public String addCommonConfig(Model model, @Valid @ModelAttribute CommonConfig commonConfig, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "add");
            return "commonConfigDetail";
        } else {
        	service.saveCommonConfig(commonConfig);
            return "redirect:/commonConfigs";
        }
    }
    
    @PostMapping("/updateCommonConfig")
    public String updateCommonConfig(Model model, @Valid @ModelAttribute CommonConfig commonConfig, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "update");
            return "commonConfigDetail";
        } else {
        	service.saveCommonConfig(commonConfig);
            return "redirect:/commonConfigs";
        }
    }
}
