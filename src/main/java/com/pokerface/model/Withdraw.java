package com.pokerface.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WITHDRAW_RECORD")
public class Withdraw {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
	@Column(name = "AGENT_ID")
    private String agentId;
	
	@Column(name = "AGENT_NAME")
    private String agentName;
	
	@Column(name = "USER_ID")
    private String userId;
	
	@Column(name = "USER_NAME")
    private String userName;
	
	@Column(name = "IS_AGENT")
    private boolean isAgent;
	
	@Column(name = "AMOUNT")
    private Long amount;
	
	@Column(name = "AGENT_BAL")
    private Long agentBalance;
	
	@Column(name = "USER_BAL")
    private Long userBalance;
	
	@Column(name = "WITHDRAW_TIME")
	private Date withdrawTime;
	
	@Column(name = "IS_ADMIN")
	private boolean isAdmin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
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

	public boolean isAgent() {
		return isAgent;
	}

	public void setAgent(boolean isAgent) {
		this.isAgent = isAgent;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getAgentBalance() {
		return agentBalance;
	}

	public void setAgentBalance(Long agentBalance) {
		this.agentBalance = agentBalance;
	}

	public Long getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(Long userBalance) {
		this.userBalance = userBalance;
	}

	public Date getWithdrawTime() {
		return withdrawTime;
	}

	public void setWithdrawTime(Date withdrawTime) {
		this.withdrawTime = withdrawTime;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
