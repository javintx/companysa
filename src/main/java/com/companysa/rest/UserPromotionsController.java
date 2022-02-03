package com.companysa.rest;

import com.companysa.rest.domain.PromotionUserDto;
import com.companysa.usecase.UserPromotionsUseCase;
import com.companysa.usecase.domain.PromotionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPromotionsController {

	private final UserPromotionsUseCase userPromotionsUseCase;

	@Autowired
	public UserPromotionsController(UserPromotionsUseCase userPromotionsUseCase) {
		this.userPromotionsUseCase = userPromotionsUseCase;
	}

	@PostMapping(value = "/promotions/signInWith", consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public String signInWith(@RequestBody PromotionUserDto user) {
		PromotionUser promotionUser = user.toDomain();
		userPromotionsUseCase.signInWith(promotionUser);
		return signedWith(promotionUser);
	}

	private String signedWith(final PromotionUser user) {
		return String.format("SignInWth: %s", user.uid());
	}

	@GetMapping(value = "/promotions/userDetails/{userUid}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PromotionUserDto> userDetailsByUid(@PathVariable String userUid) {
		return userPromotionsUseCase.userDetailsByUid(userUid)
			.map(user -> ResponseEntity.ok(PromotionUserDto.fromDomain(user)))
			.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
