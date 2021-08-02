package com.companysa.usecase.domain;

public class PromotionUser {
	private final String name;
	private final String lastName;
	private final String address;
	private final String city;
	private final String emailAddress;
	private final boolean termsAndConditions;

	private PromotionUser(final String name,
						  final String lastName,
						  final String address,
						  final String city,
						  final String emailAddress,
						  final boolean termsAndConditions) {
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.emailAddress = emailAddress;
		this.termsAndConditions = termsAndConditions;
	}

	public static PromotionUserBuilder builder() {
		return new PromotionUserBuilder();
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

	public boolean isTermsAndConditions() {
		return termsAndConditions;
	}

	public static class PromotionUserBuilder {
		private String name;
		private String lastName;
		private String address;
		private String city;
		private String emailAddress;
		private boolean termsAndConditions;

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
			this.termsAndConditions = termsAndConditions;
			return this;
		}

		public PromotionUser newUser() {
			return new PromotionUser(name, lastName, address, city, emailAddress, termsAndConditions);
		}
	}
}
