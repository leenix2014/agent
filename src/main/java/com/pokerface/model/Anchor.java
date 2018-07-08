package com.pokerface.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ANCHOR")
public class Anchor {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
	@NotNull(message="主播id不能为空")
	@Digits(integer=3, fraction=0)
    @Column(name = "ANCHOR_ID")
    private Long anchorId;

    @NotNull
    @Column(name = "ANCHOR_NAME")
    private String anchorName;
    
    @NotNull
    @Column(name = "ANCHOR_TYPE")
    private String anchorType;
    
    @NotNull
    @Column(name = "PWD")
    private String pwd;

    @Column(name = "CHANNEL")
    private String channel;

    @NotNull
    @Column(name = "TITLE")
    private String title;
    
    @NotNull(message="默认费用不能为空")
    @Digits(integer=9, fraction=0)
    @Min(value = 0)
    @Column(name = "COST")
    private Long cost;
    
    @Column(name = "ENCRYPTED")
    private boolean encrypted;
    
    @Column(name = "ROOM_PWD")
    private String roomPwd;
    
    @Column(name = "ADMIN_IDS")
    private String adminIds;
    
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
	public Anchor() {
		super();
	}

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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
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

	public boolean isEncrypted() {
		return encrypted;
	}

	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

	public String getRoomPwd() {
		return roomPwd;
	}

	public void setRoomPwd(String roomPwd) {
		this.roomPwd = roomPwd;
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
}
