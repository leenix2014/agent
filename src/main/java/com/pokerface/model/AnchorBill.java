package com.pokerface.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ANCHOR_BILL")
public class AnchorBill implements Serializable {

	//private static final long serialVersionUID = 5638109843581843912L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
	@NotNull(message="主播id不能为空")
    @Column(name = "ANCHOR_ID")
    private Long anchorId;

    @NotNull
    @Column(name = "ANCHOR_NAME")
    private String anchorName;
    
    @NotNull
    @Column(name = "ANCHOR_TYPE")
    private String anchorType;
    
    @NotNull
    @Column(name = "BILL_TYPE")
    private String billType;
    
    @NotNull
    @Column(name = "ROOM_ID")
    private Long roomId;

    @NotNull
    @Column(name = "CHANNEL")
    private String channel;
    
    @NotNull
    @Column(name = "USER_ID")
    private Long userId;
    
    @NotNull
    @Column(name = "USER_NAME")
    private String userName;
    
    @NotNull
    @Column(name = "AMOUNT")
    private Long amount;
    
    @Column(name = "RECORD_TIME")
    private Date recordTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(Long anchorId) {
		this.anchorId = anchorId;
	}

	public String getAnchorName() {
		return anchorName;
	}

	public void setAnchorName(String anchorName) {
		this.anchorName = anchorName;
	}

	public String getAnchorType() {
		return anchorType;
	}

	public void setAnchorType(String anchorType) {
		this.anchorType = anchorType;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}	
}
