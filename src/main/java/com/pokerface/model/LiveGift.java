package com.pokerface.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.pokerface.dto.LiveGiftDto;

@Entity
@Table(name = "LIVE_GIFT")
public class LiveGift {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "GIFT_ID")
    private Long giftId;

    @Column(name = "GIFT_NAME")
    private String giftName;
    
    @NotNull(message="花费不能为空")
    @Min(value=0, message="花费最小为0")
    @Column(name = "COST")
    private Long cost;
    
    @Column(name = "CROSS_ROOM")
    private Boolean crossRoom;

    @Column(name = "SPURT")
    private Boolean spurt;
    
    @Column(name = "IS_VALID")
    private Boolean isValid;
    
    @Column(name = "PLAY_EFFECT")
    private Boolean playEffect;
    
    @Column(name = "VERSION")
    private String version;
    
    @Column(name = "GIFT_POSS")
    private String giftPoss;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGiftId() {
		return giftId;
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

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public Boolean getCrossRoom() {
		return crossRoom;
	}

	public void setCrossRoom(Boolean crossRoom) {
		this.crossRoom = crossRoom;
	}

	public Boolean getSpurt() {
		return spurt;
	}

	public void setSpurt(Boolean spurt) {
		this.spurt = spurt;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Boolean getPlayEffect() {
		return playEffect;
	}

	public void setPlayEffect(Boolean playEffect) {
		this.playEffect = playEffect;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getGiftPoss() {
		return giftPoss;
	}

	public void setGiftPoss(String giftPoss) {
		this.giftPoss = giftPoss;
	}
	
	public void setPoss(LiveGiftDto poss){
		this.giftPoss = poss.getPoss();
//		this.cost = poss.getCost();
//		this.playEffect = poss.getPlayEffect();
	}
}
