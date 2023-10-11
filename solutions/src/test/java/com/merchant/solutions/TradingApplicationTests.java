package com.merchant.solutions;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.merchant.solutions.controller.TradingApplicationController;
import com.merchant.solutions.handler.SignalHandler;

@SpringBootTest
@AutoConfigureMockMvc
class TradingApplicationTests {

	@Mock
	private SignalHandler signalHandler;

	@InjectMocks
	private TradingApplicationController signalController;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testHandleSignal() throws Exception {
		int signal = 1;

		// Mock the behavior of signalHandler.handleSignal
		Mockito.doNothing().when(signalHandler).handleSignal(signal);

		// Perform the GET request to the controller endpoint
		mockMvc.perform(MockMvcRequestBuilders.get("/signals/" + signal))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Signal " + signal + " handled successfully."));
	}
}
