package com.pokerface.dto;

import com.pokerface.model.LiveGift;
import com.pokerface.util.StringUtil;

import net.sf.json.JSONObject;

public class LiveGiftDto {
	
    private Integer zero;
    private Integer one;
    private Integer two;
    private Integer five;
    private Integer ten;
    private Integer twenty;
    private Integer fifty;
    private Integer hundred;
    private Long giftId;
    private String giftName;
    private Long id;
//    private Long cost;
//    private Boolean playEffect;
    
    public LiveGiftDto(){
    	
    }
    
    public LiveGiftDto(LiveGift gift){
		this.id = gift.getId();
		this.giftId = gift.getGiftId();
		this.giftName = gift.getGiftName();
//		this.cost = gift.getCost();
//		this.playEffect = gift.getPlayEffect();
		String poss = gift.getGiftPoss();
		if(StringUtil.isNotEmpty(poss)){
			JSONObject json = JSONObject.fromObject(poss);
			this.zero = json.getInt("0");
			this.one = json.getInt("1");
			this.two = json.getInt("2");
			this.five = json.getInt("5");
			this.ten = json.getInt("10");
			this.twenty = json.getInt("20");
			this.fifty = json.getInt("50");
			this.hundred = json.getInt("100");
		}
	}
	
	public Long getGiftId() {
		return giftId;
	}

	public Integer getZero() {
		return zero;
	}

	public void setZero(Integer zero) {
		this.zero = zero;
	}

	public Integer getOne() {
		return one;
	}

	public void setOne(Integer one) {
		this.one = one;
	}

	public Integer getTwo() {
		return two;
	}

	public void setTwo(Integer two) {
		this.two = two;
	}

	public Integer getFive() {
		return five;
	}

	public void setFive(Integer five) {
		this.five = five;
	}

	public Integer getTen() {
		return ten;
	}

	public void setTen(Integer ten) {
		this.ten = ten;
	}

	public Integer getTwenty() {
		return twenty;
	}

	public void setTwenty(Integer twenty) {
		this.twenty = twenty;
	}

	public Integer getFifty() {
		return fifty;
	}

	public void setFifty(Integer fifty) {
		this.fifty = fifty;
	}

	public Integer getHundred() {
		return hundred;
	}

	public void setHundred(Integer hundred) {
		this.hundred = hundred;
	}

	public void setGiftId(Long giftId) {
		this.giftId = giftId;
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
//	public Long getCost() {
//		return cost;
//	}
//
//	public void setCost(Long cost) {
//		this.cost = cost;
//	}
//
//	public Boolean getPlayEffect() {
//		return playEffect;
//	}
//
//	public void setPlayEffect(Boolean playEffect) {
//		this.playEffect = playEffect;
//	}

	public String getPoss(){
		JSONObject json = new JSONObject();
		json.put("0", zero);
		json.put("1", one);
		json.put("2", two);
		json.put("5", five);
		json.put("10", ten);
		json.put("20", twenty);
		json.put("50", fifty);
		json.put("100", hundred);
		return json.toString();
	}
}
