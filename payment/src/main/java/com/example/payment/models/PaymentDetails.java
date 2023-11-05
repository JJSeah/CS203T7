

package com.example.payment.models;

import com.example.payment.utils.DateJsonSerializer;
import com.example.payment.utils.Utils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="PaymentDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="transaction_id")
	private String transactionId;

	@Column(name="transaction_date")
	@JsonSerialize(using = DateJsonSerializer.class)
	private LocalDateTime transactionDate;

	@Column(name="order_value")
	private double orderValue;

	@Column(name="payment_type")
	private PaymentType paymentType;

	@Column(name="payment_status")
	private String paymentStatus;



//	private CardDetails cardDetails;


	/**
	 * Payment Details
	 * 
	 * @param _txId
	 * @param _txDate
	 * @param _orderValue
	 * @param _payType
	 */
	public PaymentDetails(String _txId, LocalDateTime _txDate,
			double _orderValue, PaymentType _payType) {
		this( _txId,  _txDate, _orderValue,  _payType,  null); 
	}
	
	/**
	 * Payment Details with Card Details
	 * 
	 * @param _txId
	 * @param _txDate
	 * @param _orderValue
	 * @param _payType
	 * @param _card
	 */
	public PaymentDetails(String _txId, LocalDateTime _txDate,
			double _orderValue, PaymentType _payType, CardDetails _card) {
		
		transactionId		= _txId;
		transactionDate		= _txDate;
		orderValue			= _orderValue;
		paymentType			= _payType;
//		cardDetails			= _card;
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
	 * @return the orderValue
	 */
	public double getOrderValue() {
		return orderValue;
	}
	
	/**
	 * @return the paymentType
	 */
	public PaymentType getPaymentType() {
		return paymentType;
	}
	
	public String toString() {
		return Utils.toJsonString(this);
	}
	
	/**
	 * @return the cardDetails
	 */
//	public CardDetails getCardDetails() {
//		return cardDetails;
//	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public void setOrderValue(double orderValue) {
		this.orderValue = orderValue;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

}
