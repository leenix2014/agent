package com.pokerface.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM_CARD_BILL")
public class RoomCardBill {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
	@Column(name = "USER_ID")
    private String userId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "SOURCE")
    private String source;
	
	@Column(name = "SOURCE_ID")
	private String sourceId;
	
	@Column(name = "SOURCE_NAME")
	private String sourceName;
	
	@Column(name = "AMOUNT")
    private Long amount;
	
	@Column(name = "BEFORE_BAL")
    private Long beforeBal;
	
	@Column(name = "AFTER_BAL")
    private Long afterBal;
	
	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getBeforeBal() {
		return beforeBal;
	}

	public void setBeforeBal(Long beforeBal) {
		this.beforeBal = beforeBal;
	}

	public Long getAfterBal() {
		return afterBal;
	}

	public void setAfterBal(Long afterBal) {
		this.afterBal = afterBal;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
