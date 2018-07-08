package com.pokerface.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ROULETTE_ROOM")
public class Roulette implements Cloneable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
	@NotNull(message="房间号不能为空")
    @Column(name = "ROOM_ID")
    private String roomId;

    @NotNull
    @Column(name = "GAME_TYPE")
    private Long gameType;
    
    @Min(value=0, message="最小金额不能小于0")
    @Column(name = "MIN_COIN")
    private Integer minCoin;

    @NotNull(message="最小下注额不能为空")
    @Min(value=1, message="最小下注额最小为1")
    @Column(name = "MIN_BET")
    private Integer minBet;
    
    @NotNull(message="最大下注额不能为空")
    @Min(value=100, message="最大下注额最小为100")
    @Column(name = "MAX_BET")
    private Integer maxBet;
    
    @NotNull(message="最大总下注额不能为空")
    @Column(name = "MAX_TOTAL_BET")
    private Integer maxTotalBet;

    @NotNull(message="抽水比例不能为空")
    @Min(value=1, message="最小抽水比例为1")
    @Column(name = "DRAW_PERCENT")
    private Integer drawPercent;
    
    @Column(name = "READY_TIME")
    private Integer readyTime;
    
    @NotNull(message="下注时间不能为空")
    @Min(value=10, message="下注时间最小为10")
    @Column(name = "BET_TIME")
    private Integer betTime;
    
//    @NotNull(message="开奖时间不能为空")
//    @Min(value=6, message="开奖时间最小为6")
    @Column(name = "PRIZE_TIME")
    private Integer prizeTime;
    
    @NotNull(message="开奖后停留时间不能为空")
    @Min(value=3, message="开奖后停留时间最小为3")
    @Column(name = "PRIZED_TIME")
    private Integer prizedTime;
    
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    @Column(name = "ADMIN_IDS")
    private String adminIds;

	@NotNull(message="声网频道不能为空")
	@Size(min=1, message="声网频道不能为空")
    @Column(name = "CHANNEL")
    private String channel;
    
    @Column(name = "DELETED")
    private boolean deleted;
    
    @Column(name = "DELETE_TIME")
    private Date deletedTime;
    
	public Roulette() {
		super();
	}

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

	public Long getGameType() {
		return gameType;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public void setGameType(Long gameType) {
		this.gameType = gameType;
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

	public Integer getMaxTotalBet() {
		return maxTotalBet;
	}

	public void setMaxTotalBet(Integer maxTotalBet) {
		this.maxTotalBet = maxTotalBet;
	}

	public Integer getDrawPercent() {
		return drawPercent;
	}

	public void setDrawPercent(Integer drawPercent) {
		this.drawPercent = drawPercent;
	}

	public Integer getReadyTime() {
		return readyTime;
	}

	public void setReadyTime(Integer readyTime) {
		this.readyTime = readyTime;
	}

	public Integer getBetTime() {
		return betTime;
	}

	public void setBetTime(Integer betTime) {
		this.betTime = betTime;
	}

	public Integer getPrizeTime() {
		return prizeTime;
	}

	public void setPrizeTime(Integer prizeTime) {
		this.prizeTime = prizeTime;
	}

	public Integer getPrizedTime() {
		return prizedTime;
	}

	public void setPrizedTime(Integer prizedTime) {
		this.prizedTime = prizedTime;
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
}
