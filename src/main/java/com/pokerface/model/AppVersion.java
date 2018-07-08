package com.pokerface.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "APP_VERSION")
public class AppVersion implements Cloneable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
	@NotNull(message="客户端版本号不能为空")
    @Size(min = 1, max = 10, message = "客户端版本号不能为空")
    @Column(name = "APP_VER")
    private String appVer;

    @NotNull(message="平台不能为空")
    @Size(min = 1, max = 20, message = "平台不能为空")
    @Column(name = "PLATFORM")
    private String platform;
    
    @Column(name = "LATEST")
    private boolean latest;
    
    @Column(name = "MUST_UPDATE")
    private boolean mustUpdate;

    @Column(name = "DOWNLOAD_URL")
    private String downloadUrl;
    
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppVer() {
		return appVer;
	}

	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public boolean isLatest() {
		return latest;
	}

	public void setLatest(boolean latest) {
		this.latest = latest;
	}

	public boolean isMustUpdate() {
		return mustUpdate;
	}

	public void setMustUpdate(boolean mustUpdate) {
		this.mustUpdate = mustUpdate;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
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
	
	@Override
	public AppVersion clone() {
		AppVersion copy = new AppVersion();
		try {
			copy =  (AppVersion) super.clone();
			copy.setCreateTime(new Date());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return copy;
	}
	
}
