package com.rkc.zds.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.rkc.zds.web.controller.ContactController;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactController.class)
public class ContactControllerTest {

	public ContactControllerTest() {

	}

	@Autowired
	private MockMvc mvc;

	@Test
	public void getAllContactsAPI() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/contact").accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("content").exists());

	}
	
	@Test
	public void getOneContactAPI() throws Exception {
		String id = "8";
		mvc.perform(MockMvcRequestBuilders.get("/api/contact/{id}",id).accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("id").exists());

	}

}
