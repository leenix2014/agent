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

import com.pokerface.dto.LiveGiftDto;
import com.pokerface.model.LiveGift;
import com.pokerface.service.LiveGiftService;

@Controller
public class LiveGiftController {
    
	@Autowired
	private LiveGiftService service;

    @RequestMapping("/liveGiftDetail")
    public String liveGiftDetail(Model model, Long id) {
    	model.addAttribute("operType", "update");
        LiveGift liveGift = service.liveGiftDetail(id);
        model.addAttribute("liveGift", liveGift);        
        return "liveGiftDetail";
    }
    
    @RequestMapping("/liveGiftPossDetail")
    public String liveGiftPossDetail(Model model, Long id) {
    	model.addAttribute("operType", "update");
        LiveGift liveGift = service.liveGiftDetail(id);
        List<LiveGift> list = service.validGifts(liveGift.getGiftId());
        model.addAttribute("validGifts", list);
        model.addAttribute("liveGift", new LiveGiftDto(liveGift));
        return "liveGiftPossDetail";
    }
    
//    @RequestMapping("/toAddLiveGift")
//    public String toAddLiveGift(Model model) {
//    	model.addAttribute("operType", "add");
//    	LiveGiftDto word = new LiveGiftDto();
//    	List<LiveGift> list = service.validGifts();
//        model.addAttribute("validGifts", list);
//        model.addAttribute("liveGift", word);
//        return "liveGiftDetail";
//    }
    
    @RequestMapping("/liveGift")
    public String liveGiftList(Map<String, Object> model, HttpSession session) {
        List<LiveGift> livegift = service.validGifts();
        model.put("liveGift", livegift);
        return "liveGiftList";
    }
    
    @GetMapping("/liveDefaultPoss")
    public String liveDefaultPoss(Long id) {
    	LiveGift liveGift = service.liveGiftDetail(id);
    	if(liveGift != null){
    		service.removeLiveGiftPoss(id);
    	}
    	return "redirect:/liveGift";
    }
    
    @PostMapping("/addLiveGift")
    public String addLiveGift(Model model, @Valid @ModelAttribute LiveGift livegift, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "add");
            return "liveGiftDetail";
        } else {
        	service.saveLiveGift(livegift);
            return "redirect:/liveGift";
        }
    }
    
    @PostMapping("/updateLiveGift")
    public String updateLiveGift(Model model, @Valid @ModelAttribute LiveGift livegift, BindingResult bindingResult, HttpSession session) {
    	if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "update");
            return "liveGiftDetail";
    	} else {
        	service.saveLiveGift(livegift);
            return "redirect:/liveGift";
        }
    }
    
    @PostMapping("/updateLivePossGift")
    public String updateLivePossGift(Model model, @Valid @ModelAttribute LiveGiftDto livegift, BindingResult bindingResult, HttpSession session) {
    	if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "update");
            return "liveGiftPossDetail";
    	} else {
        	service.saveLivePossGift(livegift);
            return "redirect:/liveGift";
        }
    }
}
