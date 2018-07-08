package com.pokerface.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LIVE_ROOM")
public class LiveRoom {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
	@Column(name = "CHANNEL")
    private String channel;
	
    @Column(name = "ANCHOR")
    private Long anchorId;

    @Column(name = "ANCHOR_NAME")
    private String anchorName;
    
    @Column(name = "ANCHOR_TYPE")
    private String anchorType;
    
    @Column(name = "TITLE")
    private String title;
    
    @Column(name = "COST")
    private Long cost;
    
    @Column(name = "STATUS")
    private Integer status;
    
    @Column(name = "ONLINE_COUNT")
    private Integer onlineCount;
    
    @Column(name = "ENCRYPTED")
    private boolean encrypted;
    
    @Column(name = "PWD")
    private String pwd;
    
    @Column(name = "ADMIN_IDS")
    private String adminIds;
    
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    @Column(name = "END_TIME")
    private Date endTime;
    
	public LiveRoom() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOnlineCount() {
		return onlineCount;
	}

	public void setOnlineCount(Integer onlineCount) {
		this.onlineCount = onlineCount;
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

	public String getAdminIds() {
		return adminIds;
	}

	public void setAdminIds(String adminIds) {
		this.adminIds = adminIds;
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
