package com.pokerface.dto;

import com.pokerface.model.Topup;

public class TopupDto extends Topup {
	
	private String transferType;
	
	private String fromName;
	
	private String toName;

	public TopupDto(String transferType, Topup record){
		this.transferType = transferType;
		this.setAdmin(record.isAdmin());
		this.setAmount(record.getAmount());
		this.setFromAgent(record.isFromAgent());
		this.setFromBalance(record.getFromBalance());
		this.setFromId(record.getFromId());
		this.setFromName(record.getFromName());
		this.setId(record.getId());
		this.setToAgent(record.isToAgent());
		this.setToBalance(record.getToBalance());
		this.setToId(record.getToId());
		this.setToName(record.getToName());
		this.setTopupTime(record.getTopupTime());
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	@Override
	public String getFromName() {
		return fromName;
	}

	@Override
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	@Override
	public String getToName() {
		return toName;
	}

	@Override
	public void setToName(String toName) {
		this.toName = toName;
	}
}
