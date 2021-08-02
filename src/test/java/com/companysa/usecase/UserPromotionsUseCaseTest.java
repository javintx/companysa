package com.companysa.usecase;

import com.companysa.Application;
import com.companysa.usecase.domain.PromotionUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class UserPromotionsUseCaseTest {

	@Autowired
	private UserPromotionsUseCase userPromotionsUseCase;

	@Test
	public void testSignInWithAUser() {
		userPromotionsUseCase.signInWith(promotionUser());
	}

	private PromotionUser promotionUser() {
		return PromotionUser.builder().newUser();
	}
}
