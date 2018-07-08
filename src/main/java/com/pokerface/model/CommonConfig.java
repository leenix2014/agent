package com.pokerface.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "COMMON_CONFIG")
public class CommonConfig {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
	@NotNull(message="key不能为空")
    @Size(min = 1, max = 255, message = "key不能为空")
    @Column(name = "CONFIG_KEY")
    private String configKey;

    @NotNull(message="参数不能为空")
    @Size(min = 1, max = 255, message = "参数不能为空")
    @Column(name = "CONFIG_VALUE")
    private String configValue;
    
    @Column(name = "REMARK")
    private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}    
}
