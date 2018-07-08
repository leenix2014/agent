package com.pokerface.model;

import java.io.Serializable;
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

import com.pokerface.dto.IEntity;
import com.pokerface.dto.IJSON;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Entity
@Table(name = "USERS")
public class User implements Serializable, IJSON, IEntity {

	private static final long serialVersionUID = 5638109843581843912L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
    @Column(name = "LOGIN_ID")
    private String loginId;

    @NotNull
    @Size(min = 1, max = 16, message = "Please enter a valid name")
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "PWD")
    private String pwd;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "FB_ID")
    private String facebookId;
    
    @NotNull
    @Min(value = 0, message = "Roomcard count must be positive")
    @Column(name = "ROOM_CARD_COUNT")
    private Long roomCardCount;
    
    @NotNull
    @Min(value = 0, message = "Coin count must be positive")
    @Column(name = "COIN_COUNT")
    private Long coinCount;
    
    @NotNull
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
	public User() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
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

	@Override
	public boolean isAdmin() {
		return false;
	}

	@Override
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		try {
			json.put("USER_LOGIN_ID", this.getLoginId());
			json.put("USER_NAME", this.getName());
			json.put("ROOM_CARD_COUNT", this.getRoomCardCount());
			json.put("USER_PHONE", this.getPhone());
			json.put("FB_ID", this.getFacebookId());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

}
