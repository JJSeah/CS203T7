
package com.example.payment.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class CardDetails {


	private long id;
	private String cardNumber;
	private String holderName;
	private int expiryMonth;
	private int expiryYear;
	private int cardCode;

	private CardType cardType;


	/**
	 * @param cardNumber
	 * @param holderName
	 * @param expiryMonth
	 * @param expiryYear
	 * @param cardCode
	 * @param cardType
	 */
	public CardDetails(String cardNumber, String holderName,
			int expiryMonth, int expiryYear, int cardCode,
			CardType cardType) {
		super();
		this.cardNumber = cardNumber;
		this.holderName = holderName;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		this.cardCode = cardCode;
		this.cardType = cardType;
	}

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * @return the holderName
	 */
	public String getHolderName() {
		return holderName;
	}
	/**
	 * @return the expiryMonth
	 */
	public int getExpiryMonth() {
		return expiryMonth;
	}
	/**
	 * @return the expiryYear
	 */
	public int getExpiryYear() {
		return expiryYear;
	}
	/**
	 * @return the cardCode
	 */
	public int getCardCode() {
		return cardCode;
	}

	/**
	 * @return the cardType
	 */
	public CardType getCardType() {
		return cardType;
	}
}
