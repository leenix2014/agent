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

import com.pokerface.model.LiveWord;
import com.pokerface.service.LiveWordService;

@Controller
public class LiveWordController {
    
	@Autowired
    private LiveWordService service;

    @RequestMapping("/liveWordsDetail")
    public String liveWordsDetail(Model model, Long id) {
    	model.addAttribute("operType", "update");
        LiveWord liveWord = service.liveWordsDetail(id);
        model.addAttribute("liveWord", liveWord);
        return "liveWordsDetail";
    }
    
    @RequestMapping("/toAddLiveWord")
    public String toAddLiveWord(Model model) {
    	model.addAttribute("operType", "add");
    	LiveWord word = new LiveWord();
    	word.setWords("");
    	word.setWeight(1l);
        model.addAttribute("liveWord", word);
        return "liveWordsDetail";
    }
    
    @RequestMapping("/liveWords")
    public String liveWordsList(Map<String, Object> model, HttpSession session) {
        List<LiveWord> liveWord = service.livewords();
        model.put("liveWords", liveWord);
        return "liveWordsList";
    }
    
    @GetMapping("/liveWordDelete")
    public String liveWordDelete(Long id) {
    	LiveWord liveWord = service.liveWordsDetail(id);
    	if(liveWord != null){
    		service.removeLiveWord(id);
    	}
    	return "redirect:/liveWords";
    }
    
    @PostMapping("/addLiveWord")
    public String addLiveWord(Model model, @Valid @ModelAttribute LiveWord liveword, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "add");
            return "anchorDetail";
        } else {
        	service.saveLiveWord(liveword);
            return "redirect:/liveWords";
        }
    }
    
    @PostMapping("/updateLiveWord")
    public String updateLiveWord(Model model, @Valid @ModelAttribute LiveWord update, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "update");
            return "liveWordsDetail";
        } else {
        	service.saveLiveWord(update);
            return "redirect:/liveWords";
        }
    }
}
