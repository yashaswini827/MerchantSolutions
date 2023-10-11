package com.merchant.solutions.handler.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.merchant.solutions.algo.Algo;
import com.merchant.solutions.dto.Action;
import com.merchant.solutions.dto.Param;
import com.merchant.solutions.dto.Signal;
import com.merchant.solutions.handler.SignalHandler;
import com.merchant.solutions.repo.SignalRepository;

@Service
public class SignalHandlerImpl implements SignalHandler {

	@Autowired
	SignalRepository signalConfigRepository;
	
	@Autowired
	Algo algo;

	public void handleSignal(int signal) {
		try {
			List<Signal> signalList = loadSignalConfiguration();
			Signal signalConfig = findSignalConfig(signalList, signal);

			if (signalConfig != null) {
				executeActions(algo, signalConfig.getActions());
			} else {
				algo.cancelTrades();
			}
			algo.doAlgo();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Cacheable("singnalConfigCache")
	private List<Signal> loadSignalConfiguration() throws IOException {
		return signalConfigRepository.findAll();
	}

	private Signal findSignalConfig(List<Signal> signalList, int signalNumber) {
		return signalList.stream().filter(signal -> signal.getSignalNumber() == signalNumber).findFirst().orElse(null);
	}

	private void executeActions(Algo algo, List<Action> actions) {
		for (Action action : actions) {
			String actionType = action.getActionType();
			List<Param> params = action.getParams();

			switch (actionType) {
			case "setUp":
				algo.setUp();
				break;
			case "reverse":
				algo.reverse();
				break;
			case "performCalc":
				algo.performCalc();
				break;
			case "submitToMarket":
				algo.submitToMarket();
				break;
			case "setAlgoParam":
				for (Param param : params) {
					algo.setAlgoParam(param.getParamNumber(), param.getParamValue());
				}
				break;
			// Add more action cases as needed
			}
		}

	}
}
