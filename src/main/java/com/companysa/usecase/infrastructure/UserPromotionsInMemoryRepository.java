package com.companysa.usecase.infrastructure;

import com.companysa.usecase.domain.PromotionUser;
import com.companysa.usecase.domain.UserPromotionsRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserPromotionsInMemoryRepository implements UserPromotionsRepository {

	private final static List<PromotionUser> promotionUsers = new ArrayList<>();

	@Override
	public void signInWith(final PromotionUser user) {
		promotionUsers.add(user);
	}

	@Override
	public Optional<PromotionUser> userDetails(final String userEmail) {
		return promotionUsers.stream()
			.filter(user -> userEmail.equals(user.emailAddress()))
			.findFirst();
	}
}
