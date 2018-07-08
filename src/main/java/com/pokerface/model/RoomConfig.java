package com.pokerface.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM_CONFIG")
public class RoomConfig {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
    @Column(name = "ROOM_ID")
    private Long roomId;

    @Column(name = "GAME")
    private Long game;

    @Column(name = "OWNER")
    private Long owner;

    @Column(name = "OWNER_NAME")
    private String ownerName;
    
    @Column(name = "DEALER_TYPE")
    private String dealerType;
    
    @Column(name = "BSCORES")
    private Long bScores;

    @Column(name = "SEATS")
    private Long seats;
    
    @Column(name = "INNINGS")
    private Long innings;
    
    @Column(name = "PMSCORES")
    private Long pmScores;
    
    @Column(name = "DMSCORES")
    private Long dmScores;
    
    @Column(name = "BLIND")
    private boolean blind;
    
    @Column(name = "MAX_BET")
    private Long maxBet;
    
    @Column(name = "HAS_GHOST")
    private boolean hasGhost;
    
    @Column(name = "STATUS")
    private Long status;
    
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    @Column(name = "END_TIME")
    private Date endTime;
    
	public RoomConfig() {
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

	public Long getGame() {
		return game;
	}

	public void setGame(Long game) {
		this.game = game;
	}

	public Long getOwner() {
		return owner;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getDealerType() {
		return dealerType;
	}

	public void setDealerType(String dealerType) {
		this.dealerType = dealerType;
	}

	public Long getbScores() {
		return bScores;
	}

	public void setbScores(Long bScores) {
		this.bScores = bScores;
	}

	public Long getSeats() {
		return seats;
	}

	public void setSeats(Long seats) {
		this.seats = seats;
	}

	public Long getInnings() {
		return innings;
	}

	public void setInnings(Long innings) {
		this.innings = innings;
	}

	public Long getPmScores() {
		return pmScores;
	}

	public void setPmScores(Long pmScores) {
		this.pmScores = pmScores;
	}

	public Long getDmScores() {
		return dmScores;
	}

	public void setDmScores(Long dmScores) {
		this.dmScores = dmScores;
	}

	public boolean isBlind() {
		return blind;
	}

	public void setBlind(boolean blind) {
		this.blind = blind;
	}

	public Long getMaxBet() {
		return maxBet;
	}

	public void setMaxBet(Long maxBet) {
		this.maxBet = maxBet;
	}

	public boolean isHasGhost() {
		return hasGhost;
	}

	public void setHasGhost(boolean hasGhost) {
		this.hasGhost = hasGhost;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
