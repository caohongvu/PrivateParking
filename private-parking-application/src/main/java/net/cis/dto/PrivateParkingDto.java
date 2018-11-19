package net.cis.dto;

import net.cis.jpa.entity.enums.ParkingTypeEnum;

/**
 * Created by Vincent on 02/10/2018
 */
public class PrivateParkingDto {
	
	private long id;
	private String numberPlate;
	private ParkingTypeEnum type;
	private String inTime;
	private String outTime;
	
	private String paymentResult;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumberPlate() {
		return numberPlate;
	}
	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}
	public ParkingTypeEnum getType() {
		return type;
	}
	public void setType(ParkingTypeEnum type) {
		this.type = type;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getPaymentResult() {
		return paymentResult;
	}
	public void setPaymentResult(String paymentResult) {
		this.paymentResult = paymentResult;
	}
	
}
