package com.companysa.rest;

import com.companysa.Application;
import com.companysa.rest.domain.exception.NotAgreeWithTermsAndConditionsException;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class UserPromotionsControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testSignInWithAUser() throws Exception {
		mvc.perform(post("/promotions/signInWith")
				.contentType(MediaType.APPLICATION_JSON).content(validUser()))
			.andExpect(status().isOk())
			.andExpect(content().string("SignInWth: userName"));
	}

	@Test
	public void testSignInWithAUserWithoutTermsAndConditionsChecked() throws Exception {
		mvc.perform(post("/promotions/signInWith")
				.contentType(MediaType.APPLICATION_JSON).content(userWithoutTermsAndConditionsChecked()))
			.andExpect(status().isBadRequest())
			.andExpect(result -> assertTrue(result.getResolvedException() instanceof NotAgreeWithTermsAndConditionsException));
	}

//	@Test
//	public void testUserDetailsNotFound() throws Exception {
//		mvc.perform(get("/promotions/userDetails/user_email@address.com"))
//			.andExpect(status().isNotFound());
//	}

	private String validUser() throws JSONException {
		return userJson()
			.put("termsAndConditions", "true")
			.toString();
	}

	private JSONObject userJson() throws JSONException {
		return new JSONObject()
			.put("name", "userName")
			.put("lastName", "user last name")
			.put("address", "user address")
			.put("city", "userCity")
			.put("emailAddress", "user_email@address.com");
	}

	private String userWithoutTermsAndConditionsChecked() throws JSONException {
		return userJson()
			.put("termsAndConditions", "false")
			.toString();
	}
}
