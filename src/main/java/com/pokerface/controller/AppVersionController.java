package com.pokerface.controller;

import java.util.Date;
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

import com.pokerface.model.AppVersion;
import com.pokerface.service.AppVersionService;
import com.pokerface.util.StringUtil;

@Controller
public class AppVersionController {
    
	@Autowired
	private AppVersionService service;
	
    @RequestMapping("/appVersions")
    public String list(Map<String, Object> model, HttpSession session) {
        List<AppVersion> appVersions = service.appVersions();
        model.put("appVersions", appVersions);
        return "appVersionList";
    }
    
    @RequestMapping("/appVersionDetail")
    public String appVersionDetail(Model model, Long id, boolean newVersion) {
    	AppVersion appVersion = service.appVersionDetail(id);
        if(newVersion){
        	model.addAttribute("operType", "publish");
            appVersion.setAppVer(StringUtil.versionIncrease(appVersion.getAppVer()));
        }else{
        	model.addAttribute("operType", "update");
            appVersion.setAppVer(appVersion.getAppVer());
        }        
        model.addAttribute("appVersion", appVersion);
        return "appVersionDetail";
    }
    
    @RequestMapping("/toAddAppVersion")
    public String toAddAppVersion(Model model) {
    	model.addAttribute("operType", "add");
        model.addAttribute("appVersion", new AppVersion());
        return "appVersionDetail";
    }
    
    @RequestMapping("/copyAppVersion")
    public String increaseAppVer(Long id) {
        service.increaseAppVer(id);
        return "redirect:/appVersions";
    }
    
    @PostMapping("/addAppVersion")
    public String addAppVersion(Model model, @Valid @ModelAttribute AppVersion appVersion, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "add");
            return "appVersionDetail";
        } else {
        	appVersion.setCreateTime(new Date());
        	service.saveAppVersion(appVersion);
            return "redirect:/appVersions";
        }
    }
    
    @PostMapping("/updateAppVersion")
    public String updateAppVersion(Model model, @Valid @ModelAttribute AppVersion appVersion, boolean newVersion, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	if(newVersion){
        		model.addAttribute("operType", "publish");
        	}else{
        		model.addAttribute("operType", "update");
        	}
        	
            return "appVersionDetail";
        } else {
        	AppVersion db = service.appVersionDetail(appVersion.getId());
        	if(newVersion){
        		appVersion.setId(null);
        	}
        	appVersion.setLatest(true);
        	appVersion.setCreateTime(db.getCreateTime());
        	service.saveAppVersion(appVersion);
        	if(newVersion){
        		db.setLatest(false);
        		service.saveAppVersion(appVersion);
        	}
            return "redirect:/appVersions";
        }
    }
}
