package com.companysa.usecase.infrastructure;

import com.companysa.usecase.domain.PromotionUser;
import com.companysa.usecase.domain.UserPromotionsRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserPromotionsInMemoryRepository implements UserPromotionsRepository {

	private final static Map<String, PromotionUser> promotionUsers = new HashMap<>();

	@Override
	public void signInWith(final PromotionUser user) {
		promotionUsers.put(user.uid(), user);
	}

	@Override
	public Optional<PromotionUser> userDetailsByUid(final String userUid) {
		return Optional.ofNullable(promotionUsers.get(userUid));
	}

	@Override
	public void updateUser(PromotionUser user) {
		promotionUsers.put(user.uid(), user);
	}
}
