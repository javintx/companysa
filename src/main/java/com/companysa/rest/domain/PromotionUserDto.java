package com.companysa.rest.domain;

import com.companysa.rest.domain.exception.NotAgreeWithTermsAndConditionsException;
import com.companysa.usecase.domain.PromotionUser;

public class PromotionUserDto {
	private String name;
	private String lastName;
	private String address;
	private String city;
	private String emailAddress;
	private boolean termsAndConditions;

	public static PromotionUserDto fromDomain(final PromotionUser promotionUser) {
		PromotionUserDto promotionUserDto = new PromotionUserDto();
		promotionUserDto.setName(promotionUser.name());
		promotionUserDto.setLastName(promotionUser.lastName());
		promotionUserDto.setAddress(promotionUser.address());
		promotionUserDto.setCity(promotionUser.city());
		promotionUserDto.setEmailAddress(promotionUser.emailAddress());
		promotionUserDto.setTermsAndConditions(promotionUser.isTermsAndConditions());
		return promotionUserDto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isTermsAndConditions() {
		return termsAndConditions;
	}

	public void setTermsAndConditions(boolean termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	public void validate() {
		if (!isTermsAndConditions()) {
			throw new NotAgreeWithTermsAndConditionsException();
		}
	}

	public PromotionUser toDomain() {
		return PromotionUser.builder()
			.withName(name)
			.withLastName(lastName)
			.withAddress(address)
			.withCity(city)
			.withEmailAddress(emailAddress)
			.agreeWithTermsAndConditions(termsAndConditions)
			.newUser();
	}
}
