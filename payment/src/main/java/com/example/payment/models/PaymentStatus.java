
package com.example.payment.models;

import com.example.payment.utils.DateJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


import java.time.LocalDateTime;

public class PaymentStatus {
	
	private String transactionId;
	
	@JsonSerialize(using = DateJsonSerializer.class)
	private LocalDateTime transactionDate;
	
	private String paymentStatus;
	private String paymentReference;
	
	@JsonSerialize(using = DateJsonSerializer.class)
	private LocalDateTime paymentDate;
	private PaymentType paymentType;
	
	/**
	 * 
	 */
	public PaymentStatus() {
	}
	/**
	 * Payment Status
	 * 
	 * @param _txId
	 * @param _txDate
	 * @param _payStatus
	 * @param _payRef
	 * @param _payDate
	 * @param _payType
	 */
	public PaymentStatus(String _txId, LocalDateTime _txDate, String _payStatus,
			String _payRef, LocalDateTime _payDate, PaymentType _payType) {
		
		transactionId		= _txId;
		transactionDate		= _txDate;
		paymentStatus		= _payStatus;
		
		paymentReference	= _payRef;
		paymentDate			= _payDate;
		paymentType			= _payType;
	}
	
	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}
	/**
	 * @return the transactionDate
	 */
	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}
	/**
	 * @return the paymentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}
	/**
	 * @return the paymentReference
	 */
	public String getPaymentReference() {
		return paymentReference;
	}
	/**
	 * @return the paymentDate
	 */
	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}
	/**
	 * @return the paymentType
	 */
	public PaymentType getPaymentType() {
		return paymentType;
	}
	
	/**
	 * Returns Transaction ID | Payment Status | Payment Reference
	 */
	public String toString() {
		return transactionId + "|" + paymentStatus + "|" + paymentReference;
	}
	
	/**
	 * Returns the HashCode of the Tx ID
	 */
	public int hashCode() {
		return transactionId.hashCode();
	}
}
