package com.merchant.solutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merchant.solutions.dto.Signal;
import com.merchant.solutions.repo.SignalRepository;
import com.merchant.solutions.services.SignalHandler;

@RestController
@RequestMapping("/signals")
public class SignalController {
	@Autowired
	SignalHandler signalHandler;

	@Autowired
	SignalRepository signalConfigRepository;

	@GetMapping("/{signal}")
	public String handleSignal(@PathVariable int signal) {
		// Delegate signal handling to the SignalHandler
		signalHandler.handleSignal(signal);
		return "Signal " + signal + " handled successfully.";
	}

	@PostMapping("/create")
	public Signal createSignalConfig(@RequestBody Signal signal) {
		return signalConfigRepository.save(signal);
	}

}
