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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pokerface.model.CoinRoom;
import com.pokerface.service.CoinRoomService;

@Controller
public class CoinRoomController {
    
	@Autowired
    private CoinRoomService service;
	
    @RequestMapping("/coinrooms")
    public String list(Map<String, Object> model, HttpSession session) {
        List<CoinRoom> coinrooms = service.coinrooms();
        model.put("coinrooms", coinrooms);
        return "coinroomList";
    }
    
    @RequestMapping("/coinroomDetail")
    public String coinroomDetail(Model model, Long id) {
    	model.addAttribute("operType", "update");
        CoinRoom coinroom = service.coinroomDetail(id);
        model.addAttribute("coinRoom", coinroom);
        return "coinroomDetail";
    }
    
    @RequestMapping("/toAddCoinRoom")
    public String toAddCoinRoom(Model model) {
    	model.addAttribute("operType", "add");
        model.addAttribute("coinRoom", new CoinRoom());
        return "coinroomDetail";
    }
    
    @PostMapping("/checkCoinRoom")
    @ResponseBody
    public Map<String, Object> checkCoinRoom(Long roomId, Long id, HttpSession session) {
    	Map<String, Object> map = new HashMap<>();
    	map.put("roomIdExists", service.roomIdExists(roomId, id));
    	return map;
    }
    
    @RequestMapping("/copyCoinRoom")
    public String copyRoom(Long id) {
        service.copyRoom(id);
        return "redirect:/coinrooms";
    }
    
    @PostMapping("/addCoinRoom")
    public String addCoinRoom(Model model, @Valid @ModelAttribute CoinRoom coinRoom, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "add");
        	//model.addAttribute("coinroom", coinroom);
            return "coinroomDetail";
        } else {
        	valid(coinRoom);
        	coinRoom.setCreateTime(new Date());
        	service.saveCoinRoom(coinRoom);
            return "redirect:/coinrooms";
        }
    }
    
    @PostMapping("/updateCoinRoom")
    public String updateCoinRoom(Model model, @Valid @ModelAttribute CoinRoom coinRoom, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "update");
            return "coinroomDetail";
        } else {
        	CoinRoom db = service.coinroomDetail(coinRoom.getId());
        	valid(coinRoom);
        	coinRoom.setCreateTime(db.getCreateTime());
        	service.saveCoinRoom(coinRoom);
            return "redirect:/coinrooms";
        }
    }
    
    private void valid(CoinRoom coinroom){
    	int baseScore = coinroom.getBaseScore();
    	if(baseScore <= 0){
    		baseScore = 1;
    	}
    	if(baseScore <= 10){
    		coinroom.setGrade("primary");
    	} else if(baseScore <= 100){
    		coinroom.setGrade("junior");
    	} else {
    		coinroom.setGrade("senior");
    	}
    	
    	if(coinroom.getMaxSeat()<=1){
    		coinroom.setMaxSeat(6);
    	}
    	if(coinroom.getMinCoin()<=0){
    		coinroom.setMinCoin(baseScore*100);
    	}
    	if(coinroom.getDrawPercent()<=0){
    		coinroom.setDrawPercent(2);
    	}
    }
}
