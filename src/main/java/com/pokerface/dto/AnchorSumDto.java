package com.pokerface.dto;

public class AnchorSumDto {
	
    private Long anchorId;
    
    private Long sum;
    
    private String anchorName;
    
    private String anchorType;
    
    private Long roomId;
    
    private String channel;
	
	public AnchorSumDto(Long anchorId, Long sum, String anchorName, String anchorType,
			Long roomId, String channel){
		this.anchorId = anchorId;
		this.sum = sum;
		this.anchorName = anchorName;
		this.anchorType = anchorType;
		this.roomId = roomId;
		this.channel = channel;
	}

	public Long getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(Long anchorId) {
		this.anchorId = anchorId;
	}

	public Long getSum() {
		return sum;
	}

	public void setSum(Long sum) {
		this.sum = sum;
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

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
}
