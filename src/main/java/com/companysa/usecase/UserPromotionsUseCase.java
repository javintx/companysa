package com.companysa.usecase;

import com.companysa.usecase.domain.PromotionUser;
import com.companysa.usecase.domain.UserPromotionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPromotionsUseCase {

	private final UserPromotionsRepository userPromotionsRepository;

	@Autowired
	public UserPromotionsUseCase(final UserPromotionsRepository userPromotionsRepository) {
		this.userPromotionsRepository = userPromotionsRepository;
	}

	public void signInWith(final PromotionUser user) {
		userPromotionsRepository.signInWith(user);
	}

	public Optional<PromotionUser> userDetails(String userEmail) {
		return userPromotionsRepository.userDetails(userEmail);
	}
}
