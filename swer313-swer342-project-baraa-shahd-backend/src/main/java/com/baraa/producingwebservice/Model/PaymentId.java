package com.baraa.producingwebservice.Model;

import java.io.Serializable;
import java.util.Objects;




public class PaymentId implements Serializable {
	private Integer accountNumber;

    private String accountType;

	public PaymentId(Integer accountNumber, String accountType) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
	}

	public PaymentId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, accountType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentId other = (PaymentId) obj;
		return Objects.equals(accountNumber, other.accountNumber) && Objects.equals(accountType, other.accountType);
	}


    

}
