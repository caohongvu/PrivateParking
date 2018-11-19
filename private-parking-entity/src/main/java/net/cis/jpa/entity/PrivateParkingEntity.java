package net.cis.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.cis.jpa.entity.enums.ParkingTypeEnum;

/**
 * Created by Vincent on 02/10/2018
 */
@Entity
@Table(name="tickets")
public class PrivateParkingEntity {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="number_plate")
	private String numberPlate;
	
	@Column(name="type")
	private ParkingTypeEnum type;
	
	@Column(name="in_time")
	private String inTime;
	
	@Column(name="out_time")
	private String outTime;
	
	@Column(name="payment_result")
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
