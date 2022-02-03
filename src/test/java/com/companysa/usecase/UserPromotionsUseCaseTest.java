package com.companysa.usecase;

import com.companysa.Application;
import com.companysa.usecase.domain.PromotionUser;
import com.companysa.usecase.domain.exception.NotAgreeWithTermsAndConditions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = Application.class)
public class UserPromotionsUseCaseTest {

	@Autowired
	private UserPromotionsUseCase userPromotionsUseCase;

	@Test
	public void testSignInWithValidUser() {
		assertDoesNotThrow(() -> userPromotionsUseCase.signInWith(validPromotionUser()));
	}

	@Test
	public void testSignInWithInvalidUser() {
		assertThrows(NotAgreeWithTermsAndConditions.class, () -> userPromotionsUseCase.signInWith(invalidPromotionUser()));
	}

	@Test
	public void testUserDetailsByUid() {
		PromotionUser promotionUser = validPromotionUser();
		userPromotionsUseCase.signInWith(promotionUser);
		PromotionUser storedPromotionUser = userPromotionsUseCase.userDetailsByUid(promotionUser.uid()).get();
		assertEquals(promotionUser, storedPromotionUser);
	}

	@Test
	public void testUpdateUserDetails() {
		PromotionUser promotionUser = validPromotionUser();
		userPromotionsUseCase.signInWith(promotionUser);

		PromotionUser updatedPromotionUser = PromotionUser.updater(promotionUser).withName("New Name").updateUser();
		PromotionUser storedPromotionUser = userPromotionsUseCase.updateUserDetails(updatedPromotionUser);
		assertEquals(updatedPromotionUser, storedPromotionUser);
	}

	private PromotionUser validPromotionUser() {
		return PromotionUser.builder()
			.withName("Name")
			.withLastName("Last Name")
			.withAddress("Address")
			.withCity("City")
			.withEmailAddress("email@address")
			.agreeWithTermsAndConditions(true)
			.newUser();
	}

	private PromotionUser invalidPromotionUser() {
		return PromotionUser.builder()
			.withName("Name")
			.withLastName("Last Name")
			.withAddress("Address")
			.withCity("City")
			.withEmailAddress("email@address")
			.agreeWithTermsAndConditions(false)
			.newUser();
	}
}
