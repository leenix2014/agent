package com.pokerface.controller;

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

import com.pokerface.dto.AnchorSumDto;
import com.pokerface.model.AnchorBill;
import com.pokerface.service.AnchorBillService;
import com.pokerface.util.StringUtil;

import net.sf.json.JSONObject;

@Controller
public class AnchorBillController {
    
	@Autowired
	private AnchorBillService service;
	
    @RequestMapping("/existsAnchorBill")
    @ResponseBody
    public String existsAnchorBill(String userId, HttpSession session) {
    	long anchorBill = StringUtil.parseInt(userId);
    	boolean exists = service.existsAnchorBill(anchorBill);
    	JSONObject result = new JSONObject();
    	result.put("exists", exists);
    	JSONObject json = new JSONObject();
    	json.put("retcode", 0);
    	json.put("result", result);
        return json.toString();
    }
    
//    @RequestMapping("/updateMachine")
//    @ResponseBody
//    public String updateMachine(String machine_MAC, String is_online, String supplies, String is_binding, String platform_userid, String status) {
//    	long anchorBillId = StringUtil.parseInt(platform_userid);
//    	service.updateMachine(anchorBillId, machine_MAC, is_online, supplies, is_binding, status);
//    	JSONObject json = new JSONObject();
//    	json.put("state", 0);
//    	json.put("content", new JSONObject());
//        return json.toString();
//    }

    @RequestMapping("/anchorsBill")
    public String list(Map<String, Object> model, Long anchorId, HttpSession session) {
        List<AnchorBill> anchorBills = service.anchorBills(anchorId);
        model.put("anchorBills", anchorBills);
        return "anchorBillList";
    }
    
    @RequestMapping("/anchorsSumBill")
    public String sumlist(Map<String, Object> model, HttpSession session) {
        List<AnchorSumDto> anchorSumBills = service.anchorSumBills();
        model.put("anchorSumBills", anchorSumBills);
        return "anchorSumBillList";
    }
    
    @RequestMapping("/anchorBillDetail")
    public String anchorBillDetail(Model model, Long id) {
    	model.addAttribute("operType", "update");
        AnchorBill anchorBill = service.anchorBillDetail(id);
        model.addAttribute("anchorBill", anchorBill);
        return "anchorBillDetail";
    }
    
//    @RequestMapping("/toAddAnchorBill")
//    public String toAddAnchorBill(Model model) {
//    	model.addAttribute("operType", "add");
//        model.addAttribute("anchorBill", new AnchorBill());
//        return "anchorBillDetail";
//    }
    
    @PostMapping("/checkAnchorBill")
    @ResponseBody
    public Map<String, Object> checkAnchorBill(Long anchorBillId, HttpSession session) {
    	Map<String, Object> map = new HashMap<>();
    	map.put("anchorBillIdExists", service.existsAnchorBill(anchorBillId));
    	return map;
    }
    
//    @PostMapping("/addAnchorBill")
//    public String addAnchorBill(Model model, @Valid @ModelAttribute AnchorBill anchorBill, BindingResult bindingResult, HttpSession session) {
//        if (bindingResult.hasErrors()) {
//        	model.addAttribute("operType", "add");
//            return "anchorBillDetail";
//        } else {
////        	anchorBill.setCreateTime(new Date());
//        	service.saveAnchorBill(anchorBill);
//            return "redirect:/anchorBills";
//        }
//    }
    
    @PostMapping("/updateAnchorBill")
    public String updateAnchorBill(Model model, @Valid @ModelAttribute AnchorBill update, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("operType", "update");
            return "anchorBillDetail";
        } else {
//        	AnchorBill anchorBill = service.anchorBillDetail(update.getId());
//        	update.setCreateTime(anchorBill.getCreateTime());
        	service.saveAnchorBill(update);
            return "redirect:/anchorBills";
        }
    }
    
//    @RequestMapping("/anchorBillStart")
//    @ResponseBody
//    public Map<String, Object> anchorBillStart(Model model, Long anchorBillId) {
//    	Map<String, Object> map = new HashMap<>();
//        if(service.isLiving(anchorBillId)){
//        	map.put("success", false);
//        	map.put("message", "开播成功。");
//        } else {
//        	service.anchorBillStart(anchorBillId);
//        	map.put("success", true);
//        }
//        return map;
//    }
    
//    @RequestMapping("/anchorBillStop")
//    @ResponseBody
//    public Map<String, Object> anchorBillStop(Model model, Long anchorBillId) {
//    	Map<String, Object> map = new HashMap<>();
//        service.anchorBillStop(anchorBillId);
//        map.put("success", true);
//        return map;
//    }
}
