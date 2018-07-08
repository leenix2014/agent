package com.pokerface.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TOPUP_RECORD")
public class Topup {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
	@Column(name = "FROM_ID")
    private String fromId;
	
	@Column(name = "FROM_NAME")
    private String fromName;
	
	@Column(name = "FROM_AGENT")
	private boolean isFromAgent;
	
	@Column(name = "TO_ID")
    private String toId;
	
	@Column(name = "TO_NAME")
    private String toName;
	
	@Column(name = "TO_AGENT")
	private boolean toAgent;
	
	@Column(name = "AMOUNT")
    private Long amount;
	
	@Column(name = "FROM_BAL")
    private Long fromBalance;
	
	@Column(name = "TO_BAL")
    private Long toBalance;
	
	@Column(name = "TOPUP_TIME")
	private Date topupTime;
	
	@Column(name = "IS_ADMIN")
	private boolean isAdmin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public boolean isFromAgent() {
		return isFromAgent;
	}

	public void setFromAgent(boolean isFromAgent) {
		this.isFromAgent = isFromAgent;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public boolean isToAgent() {
		return toAgent;
	}

	public void setToAgent(boolean toAgent) {
		this.toAgent = toAgent;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getFromBalance() {
		return fromBalance;
	}

	public void setFromBalance(Long fromBalance) {
		this.fromBalance = fromBalance;
	}

	public Long getToBalance() {
		return toBalance;
	}

	public void setToBalance(Long toBalance) {
		this.toBalance = toBalance;
	}

	public Date getTopupTime() {
		return topupTime;
	}

	public void setTopupTime(Date topupTime) {
		this.topupTime = topupTime;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
