package com.pokerface.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pokerface.model.RoomConfig;
import com.pokerface.model.RoomInning;
import com.pokerface.service.RoomService;

@Controller
public class RoomController {
    
	@Autowired
    private RoomService roomService;
	
	//Retrieve all rooms in the database
    @RequestMapping("/rooms")
    public String list(Map<String, Object> model, HttpSession session) {
    	List<RoomConfig> rooms = roomService.findAllRoom();
        model.put("rooms", rooms);
        return "roomList";
    }
    
    @RequestMapping("/roomDetail")
    public String listInning(Map<String, Object> model, Long roomId, HttpSession session) {
    	List<RoomInning> innings = roomService.findRoomInnings(roomId);
    	model.put("roomId", roomId);
        model.put("innings", innings);
        return "inningList";
    }
    
    @RequestMapping("/roomBill")
    public String roomBill(Map<String, Object> model, Long roomId, HttpSession session) {
    	List<RoomInning> bills = roomService.findRoomBill(roomId);
    	model.put("roomId", roomId);
        model.put("bills", bills);
        return "roomBill";
    }

}
