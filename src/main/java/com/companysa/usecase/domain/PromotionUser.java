package com.companysa.usecase.domain;

import com.companysa.usecase.domain.exception.NotAgreeWithTermsAndConditions;

import java.util.UUID;

public class PromotionUser {
	private final String UID;
	private final String name;
	private final String lastName;
	private final String address;
	private final String city;
	private final String emailAddress;
	private final boolean agreeWithTermsAndConditions;

	private PromotionUser(final String UID,
						  final String name,
						  final String lastName,
						  final String address,
						  final String city,
						  final String emailAddress,
						  final boolean agreeWithTermsAndConditions) {
		this.UID = UID;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.emailAddress = emailAddress;
		this.agreeWithTermsAndConditions = agreeWithTermsAndConditions;
	}

	public static PromotionUserBuilder builder() {
		return new PromotionUserBuilder();
	}

	public static PromotionUserBuilder updater(final PromotionUser promotionUser) {
		return new PromotionUserBuilder(promotionUser);
	}

	public String uid() {
		return UID;
	}

	public String name() {
		return name;
	}

	public String lastName() {
		return lastName;
	}

	public String address() {
		return address;
	}

	public String city() {
		return city;
	}

	public String emailAddress() {
		return emailAddress;
	}

	public boolean isAgreeWithTermsAndConditions() {
		return agreeWithTermsAndConditions;
	}

	public void validate() {
		if (!isAgreeWithTermsAndConditions()) {
			throw new NotAgreeWithTermsAndConditions();
		}
	}

	public static class PromotionUserBuilder {
		private final String uid;
		private String name;
		private String lastName;
		private String address;
		private String city;
		private String emailAddress;
		private boolean agreeWithTermsAndConditions;

		private PromotionUserBuilder() {
			this.uid = UUID.randomUUID().toString();
		}

		private PromotionUserBuilder(final PromotionUser promotionUser) {
			this.uid = promotionUser.uid();
			this.name = promotionUser.name();
			this.lastName = promotionUser.lastName();
			this.address = promotionUser.address();
			this.city = promotionUser.city();
			this.emailAddress = promotionUser.emailAddress();
			this.agreeWithTermsAndConditions = promotionUser.isAgreeWithTermsAndConditions();
		}

		public PromotionUserBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public PromotionUserBuilder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public PromotionUserBuilder withAddress(String address) {
			this.address = address;
			return this;
		}

		public PromotionUserBuilder withCity(String city) {
			this.city = city;
			return this;
		}

		public PromotionUserBuilder withEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
			return this;
		}

		public PromotionUserBuilder agreeWithTermsAndConditions(boolean termsAndConditions) {
			this.agreeWithTermsAndConditions = termsAndConditions;
			return this;
		}

		public PromotionUser newUser() {
			return new PromotionUser(uid, name, lastName, address, city, emailAddress, agreeWithTermsAndConditions);
		}

		public PromotionUser updateUser() {
			return new PromotionUser(uid, name, lastName, address, city, emailAddress, agreeWithTermsAndConditions);
		}
	}
}
