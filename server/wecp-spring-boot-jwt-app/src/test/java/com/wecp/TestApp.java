package com.wecp;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wecp.controller.JwtAuthenticationController;
import com.wecp.controller.UserTransactionController;
import com.wecp.entities.UserTransaction;
import com.wecp.model.JwtRequest;
import com.wecp.model.JwtResponse;

@AutoConfigureMockMvc
@SpringBootTest
public class TestApp {
	@Autowired
	JwtAuthenticationController authenticationController;
	
	@Autowired
	UserTransactionController userTransactionController;
	
	@Autowired
	MockMvc mockMvc;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	   void contextLoads(ApplicationContext context) {
		 assertThat(context).isNotNull();
	   }
	
	@Test
	  void testSuccessfulAuthentication(ApplicationContext context) {
		JwtRequest user = new JwtRequest("admin.user", "12345");
		try {
			 ResponseEntity<JwtResponse> res = (ResponseEntity<JwtResponse>) authenticationController.createAuthenticationToken(user);
			 JwtResponse r =  res.getBody();
			 String token = r.getToken();
			 assertNotNull(token);
		} catch (Exception e) {
			
			assertEquals(true, false);
		}
	  }
	
	@Test
	  void testWrongCredentials(ApplicationContext context) {
		JwtRequest user = new JwtRequest("admin.user", "123456");
		try {
			 ResponseEntity<JwtResponse> res = (ResponseEntity<JwtResponse>) authenticationController.createAuthenticationToken(user);
			 JwtResponse r =  res.getBody();
			 String token = r.getToken();
			 assertNull(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals(true, true);
		}
	  }
	
	@Test
	@WithMockUser(username = "John", password = "123456", roles = "CLIENT")
	public void testFetchingDataForInvalidUser() {
		 try {
			ResultActions actions =   mockMvc.perform(MockMvcRequestBuilders.get("/all-transactions?userId=abcdef")
			            .accept(MediaType.ALL))
			            .andExpect(status().isNotFound());
			//actions.
			System.out.println(actions.toString());
		} catch (Exception e) {
			assertEquals(true, false);
		}
		           // .andExpect(content().string(null));
	}
	
	@Test
	@WithMockUser(username = "John", password = "123456", roles = "CLIENT")
	public void testFetchingDataForValidUser() {
		 try {
			ResultActions actions =   mockMvc.perform(MockMvcRequestBuilders.get("/all-transactions?userId=John")
			            .accept(MediaType.ALL))
			            .andExpect(status().isOk());
			
		} catch (Exception e) {
			assertEquals(true, false);
		}
		         
	}
	
	@Test
	@WithMockUser(username = "John", password = "123456", roles = "CLIENT")
	public void testTransactCredit() {
		 try {
			 UserTransaction transaction = new UserTransaction();
				transaction.setUserId("John");
				transaction.setTransactionAmount(100d);
				transaction.setTransactionType("CREDIT");
				
			String json = mapper.writeValueAsString(transaction);
			ResultActions actions =   mockMvc.perform(MockMvcRequestBuilders.post("/transaction")
						.content(json)
						.contentType(MediaType.APPLICATION_JSON)
			            .accept(MediaType.ALL))
			            .andExpect(status().isOk());
		
		} catch (Exception e) {
			assertEquals(true, false);
		}
		           // .andExpect(content().string(null));
	}
	
	@Test
	@WithMockUser(username = "John", password = "123456", roles = "CLIENT")
	public void testTransactDebit() {
		 try {
			 UserTransaction transaction = new UserTransaction();
				transaction.setUserId("John");
				transaction.setTransactionAmount(50d);
				transaction.setTransactionType("DEBIT");
				
			String json = mapper.writeValueAsString(transaction);
			ResultActions actions =   mockMvc.perform(MockMvcRequestBuilders.post("/transaction")
						.content(json)
						.contentType(MediaType.APPLICATION_JSON)
			            .accept(MediaType.ALL))
			            .andExpect(status().isOk());
			
		} catch (Exception e) {
			assertEquals(true, false);
		}
		           // .andExpect(content().string(null));
	}
	
	@Test
	@WithMockUser(username = "admin.user", password = "123456", roles = "ADMIN")
	public void testFetchingDataByWrongRole() {
		 try {
			ResultActions actions =   mockMvc.perform(MockMvcRequestBuilders.get("/all-transactions?userId=John")
			            .accept(MediaType.ALL))
			            .andExpect(status().isForbidden());
			
		} catch (Exception e) {
			assertEquals(true, false);
		}
		           // .andExpect(content().string(null));
	}
	
	

}
