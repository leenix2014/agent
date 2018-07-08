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
@Table(name = "ANCHOR_MACHINE")
public class AnchorMachine implements Serializable {

	private static final long serialVersionUID = 5638109843581843912L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
    @Column(name = "ANCHOR_ID")
    private Long anchorId;

    @Column(name = "MACHINE_MAC")
    private String machineMac;
    
    @Column(name = "IS_ONLINE")
    private String isOnline;

    @Column(name = "SUPPLIES")
    private String supplies;

    @Column(name = "IS_BINDING")
    private String isBinding;
    
    @Column(name = "STATUS")
    private String status;
    
    @NotNull
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    
	public AnchorMachine() {
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

	public String getMachineMac() {
		return machineMac;
	}

	public void setMachineMac(String machineMac) {
		this.machineMac = machineMac;
	}

	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}

	public String getSupplies() {
		return supplies;
	}

	public void setSupplies(String supplies) {
		this.supplies = supplies;
	}

	public String getIsBinding() {
		return isBinding;
	}

	public void setIsBinding(String isBinding) {
		this.isBinding = isBinding;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
