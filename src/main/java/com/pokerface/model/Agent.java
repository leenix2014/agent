package com.pokerface.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pokerface.dto.IEntity;
import com.pokerface.dto.IJSON;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Entity
@Table(name = "AGENT")
public class Agent implements Serializable, IJSON, IEntity {

	private static final long serialVersionUID = -7673491772242816703L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
	@NotNull(message="登录ID不能为空")
    @Size(min = 1, max = 16, message = "登录ID不能为空")
    @Column(name = "LOGIN_ID")
    private String loginId;

    @NotNull(message="代理名字不能为空")
    @Size(min = 1, max = 16, message = "代理名字不能为空")
    @Column(name = "NAME")
    private String name;

    @Column(name = "PARENT_AGENT_ID", updatable = false)
    private String parentAgentId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_AGENT_ID", referencedColumnName="LOGIN_ID", insertable=false, updatable=false)
    private Agent parent;
    
    @NotNull(message="密码不能为空")
    @Size(min = 1, max = 16, message = "密码不能为空")
    @Column(name = "PWD")
    private String pwd;
    
    @NotNull(message="电话不能为空")
    @Size(min = 1, max = 16, message = "电话不能为空")
    @Column(name = "PHONE")
    private String phone;
    
    @Column(name = "ROOM_CARD_COUNT")
    private Long roomCardCount;
    
    @Column(name = "COIN_COUNT")
    private Long coinCount;

    @Column(name = "LEVEL")
    private Integer level;
    
    @Column(name = "DISCOUNT")
    private String discount;
    
    @Column(name = "IS_ADMIN")
    private boolean isAdmin;
    
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    @Column(name = "DELETED")
    private boolean deleted;
    
    @Column(name = "DELETE_TIME")
    private Date deleteTime;
    
	public Agent() {
		super();
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getPwd() {
		return pwd;
	}

	@Override
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getParentAgentId() {
		return parentAgentId;
	}

	public void setParentAgentId(String parentAgentId) {
		this.parentAgentId = parentAgentId;
	}

	public Agent getParent() {
		return parent;
	}

	public void setParent(Agent parent) {
		this.parent = parent;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	@Override
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public Long getRoomCardCount() {
		return roomCardCount;
	}

	@Override
	public void setRoomCardCount(Long roomCardCount) {
		this.roomCardCount = roomCardCount;
	}

	public Long getCoinCount() {
		return coinCount;
	}

	public void setCoinCount(Long coinCount) {
		this.coinCount = coinCount;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	@Override
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		try {
			json.put("AGENT_LOGIN_ID", this.getLoginId());
			json.put("AGENT_NAME", this.getName());
			json.put("ROOM_CARD_COUNT", this.getRoomCardCount());
			json.put("PARENT_AGENT_ID", this.getParent().getLoginId());
			json.put("PARENT_AGENT_NAME", this.getParent().getName());
			json.put("AGENT_PHONE", this.getPhone());
			json.put("AGENT_LEVEL", this.getLevel());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

}
