package com.pokerface.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "BACCARAT_ROOM")
public class Baccarat implements Cloneable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
	@NotNull(message="房间号不能为空")
	@Size(min=1, message="房间号不能为空")
    @Column(name = "ROOM_ID")
    private String roomId;
    
	@NotNull(message="最小金额不能为空")
    @Min(value=0, message="最小金额不能小于0")
    @Column(name = "MIN_COIN")
    private Integer minCoin;
    
    @NotNull(message="坐下金币门槛不能为空")
    @Min(value=10000, message="坐下金币门槛最小为10000")
    @Column(name = "TAKE_SEAT_COIN")
    private Integer takeSeatCoin;

    @NotNull(message="最小下注额不能为空")
    @Min(value=1, message="最小下注额最小为1")
    @Column(name = "MIN_BET")
    private Integer minBet;
    
    @NotNull(message="最大下注额不能为空")
    @Min(value=100, message="最大下注额最小为100")
    @Column(name = "MAX_BET")
    private Integer maxBet;
    
    @NotNull(message="限红不能为空")
    @Min(value=10000, message="限红最小为10000")
    @Column(name = "MAX_RED")
    private Integer maxRed;
    
    @NotNull(message="和、对子下限不能为空")
    @Min(value=10, message="和、对子下限最小为10")
    @Column(name = "TIE_PAIR_MIN_BET")
    private Integer tiePairMinBet;
    
    @NotNull(message="和、对子上限不能为空")
    @Min(value=30, message="和、对子上限最小为30")
    @Column(name = "TIE_PAIR_MAX_BET")
    private Integer tiePairMaxBet;

    @NotNull(message="抽水比例不能为空")
    @Min(value=1, message="最小抽水比例为1")
    @Column(name = "DRAW_PERCENT")
    private Integer drawPercent;
    
    @NotNull(message="下注时间不能为空")
    @Min(value=10, message="下注时间最小为10")
    @Column(name = "BET_TIME")
    private Integer betTime;
    
    @Column(name = "OVER_TIME")
    private Integer overTime;
    
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    @Column(name = "ADMIN_IDS")
    private String adminIds;
    
    @Column(name = "DELETED")
    private boolean deleted;
    
    @Column(name = "DELETE_TIME")
    private Date deletedTime;
    
    @NotNull(message="声网频道不能为空")
	@Size(min=1, message="声网频道不能为空")
    @Column(name = "CHANNEL")
    private String channel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Integer getMinCoin() {
		return minCoin;
	}

	public void setMinCoin(Integer minCoin) {
		this.minCoin = minCoin;
	}

	public Integer getMinBet() {
		return minBet;
	}

	public void setMinBet(Integer minBet) {
		this.minBet = minBet;
	}

	public Integer getMaxBet() {
		return maxBet;
	}

	public void setMaxBet(Integer maxBet) {
		this.maxBet = maxBet;
	}

	public Integer getMaxRed() {
		return maxRed;
	}

	public void setMaxRed(Integer maxRed) {
		this.maxRed = maxRed;
	}

	public Integer getTiePairMinBet() {
		return tiePairMinBet;
	}

	public void setTiePairMinBet(Integer tiePairMinBet) {
		this.tiePairMinBet = tiePairMinBet;
	}

	public Integer getTiePairMaxBet() {
		return tiePairMaxBet;
	}

	public void setTiePairMaxBet(Integer tiePairMaxBet) {
		this.tiePairMaxBet = tiePairMaxBet;
	}

	public Integer getDrawPercent() {
		return drawPercent;
	}

	public void setDrawPercent(Integer drawPercent) {
		this.drawPercent = drawPercent;
	}

	public Integer getBetTime() {
		return betTime;
	}

	public void setBetTime(Integer betTime) {
		this.betTime = betTime;
	}

	public Integer getOverTime() {
		return overTime;
	}

	public void setOverTime(Integer overTime) {
		this.overTime = overTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAdminIds() {
		return adminIds;
	}

	public void setAdminIds(String adminIds) {
		this.adminIds = adminIds;
	}

	public Integer getTakeSeatCoin() {
		return takeSeatCoin;
	}

	public void setTakeSeatCoin(Integer takeSeatCoin) {
		this.takeSeatCoin = takeSeatCoin;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(Date deletedTime) {
		this.deletedTime = deletedTime;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
}
