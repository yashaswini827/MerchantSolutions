package com.merchant.solutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merchant.solutions.dto.Signal;
import com.merchant.solutions.handler.SignalHandler;
import com.merchant.solutions.repo.SignalRepository;

@RestController
@RequestMapping("/signals")
public class TradingApplicationController {
	@Autowired
	SignalHandler signalHandler;

	@Autowired
	SignalRepository signalConfigRepository;

	@GetMapping("/{signal}")
	public ResponseEntity<String> handleSignal(@PathVariable int signal) {
		// Delegate signal handling to the SignalHandler
		signalHandler.handleSignal(signal);
		return ResponseEntity.ok("Signal " + signal + " handled successfully.");
	}

	@PostMapping("/create")
	public Signal createSignalConfig(@RequestBody Signal signal) {
		return signalConfigRepository.save(signal);
	}

}
