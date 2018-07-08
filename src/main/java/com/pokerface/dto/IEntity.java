package com.pokerface.dto;

public interface IEntity {

	public String getLoginId();
	
	public String getName();
	
	public Long getRoomCardCount();
	
	public void setRoomCardCount(Long count);
	
	public Long getId();
	
	public boolean isAdmin();
	
	public String getPwd();
	
	public void setPwd(String pwd);
}
