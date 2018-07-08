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

@Entity
@Table(name = "COIN_ROOM")
public class CoinRoom implements Cloneable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
	@NotNull(message="房间号不能为空")
    @Column(name = "ROOM_ID")
    private Long roomId;

    @NotNull
    @Column(name = "MODE")
    private String mode;
    
    @NotNull(message="底分不能为空")
    @Min(value=1, message="最小底分为1")
    @Column(name = "BASE_SCORE")
    private Integer baseScore;

    @NotNull(message="最小金币不能为空")
    @Min(value=1, message="最小金币数为1")
    @Column(name = "MIN_COIN")
    private Integer minCoin;
    
    @NotNull(message="提示充值金币数不能为空")
    @Min(value=1, message="提示充值金币数最小为1")
    @Column(name = "NEED_CHARGE_COIN")
    private Integer needChargeCoin;
    
    @NotNull(message="自动踢出金币数不能为空")
    @Min(value=0, message="自动踢出金币数最小为0")
    @Column(name = "KICK_OUT_COIN")
    private Integer kickOutCoin;

    @NotNull(message="最大座位不能为空")
    @Min(value=2, message="最小座位数为2")
    @Column(name = "MAX_SEAT")
    private Integer maxSeat;
    
    @NotNull(message="等级不能为空")
    @Column(name = "GRADE")
    private String grade;
    
    @NotNull(message="抽水比例不能为空")
    @Min(value=1, message="最小抽水比例为1")
    @Column(name = "DRAW_PERCENT")
    private Integer drawPercent;
    
    @Column(name = "ENCRYPTED")
    private boolean encrypted;
    
    @Column(name = "PWD")
    private String pwd;
    
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
	public CoinRoom() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public Integer getBaseScore() {
		return baseScore;
	}

	public void setBaseScore(Integer baseScore) {
		this.baseScore = baseScore;
	}

	public Integer getMinCoin() {
		return minCoin;
	}

	public void setMinCoin(Integer minCoin) {
		this.minCoin = minCoin;
	}

	public Integer getNeedChargeCoin() {
		return needChargeCoin;
	}

	public void setNeedChargeCoin(Integer needChargeCoin) {
		this.needChargeCoin = needChargeCoin;
	}

	public Integer getKickOutCoin() {
		return kickOutCoin;
	}

	public void setKickOutCoin(Integer kickOutCoin) {
		this.kickOutCoin = kickOutCoin;
	}

	public Integer getMaxSeat() {
		return maxSeat;
	}

	public void setMaxSeat(Integer maxSeat) {
		this.maxSeat = maxSeat;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getDrawPercent() {
		return drawPercent;
	}

	public void setDrawPercent(Integer drawPercent) {
		this.drawPercent = drawPercent;
	}

	public boolean isEncrypted() {
		return encrypted;
	}

	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Override
	public CoinRoom clone() {
		CoinRoom copy = new CoinRoom();
		try {
			copy =  (CoinRoom) super.clone();
			copy.setCreateTime(new Date());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return copy;
	}
}
