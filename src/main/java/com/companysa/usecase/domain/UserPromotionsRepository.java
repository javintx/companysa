package com.companysa.usecase.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPromotionsRepository {
	void signInWith(final PromotionUser user);

	Optional<PromotionUser> userDetails(final String userEmail);
}
