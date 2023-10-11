package com.merchant.solutions.handlerTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.merchant.solutions.algo.Algo;
import com.merchant.solutions.dto.Action;
import com.merchant.solutions.dto.Param;
import com.merchant.solutions.dto.Signal;
import com.merchant.solutions.handler.impl.SignalHandlerImpl;
import com.merchant.solutions.repo.SignalRepository;

public class SignalHandlerTest {
	
	@InjectMocks
    private SignalHandlerImpl signalHandler;

    @Mock
    private SignalRepository signalRepository;

    @Mock
    private Algo algo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleSignal() throws IOException {
        Signal signalConfig = createMockSignalConfig();
        Mockito.when(signalRepository.findAll()).thenReturn(Collections.singletonList(signalConfig));

        signalHandler.handleSignal(1);

        verifyAlgoMethodsCalled();
        verifyAlgoCancelTradesNotCalled();
    }

    private Signal createMockSignalConfig() {
        Signal signalConfig = new Signal();
        signalConfig.setSignalNumber(1);

        List<Action> actionList = new ArrayList<>();
        actionList.add(createAction("setUp"));
        actionList.add(createActionWithParams("setAlgoParam", 1, 60));
        actionList.add(createAction("performCalc"));
        actionList.add(createAction("submitToMarket"));

        signalConfig.setActions(actionList);
        return signalConfig;
    }

    private Action createAction(String actionType) {
        Action action = new Action();
        action.setActionType(actionType);
        return action;
    }

    private Action createActionWithParams(String actionType, int paramNumber, int paramValue) {
        Action action = createAction(actionType);
        List<Param> paramList = Collections.singletonList(createParam(paramNumber, paramValue));
        action.setParams(paramList);
        return action;
    }

    private Param createParam(int paramNumber, int paramValue) {
        Param param = new Param();
        param.setParamNumber(paramNumber);
        param.setParamValue(paramValue);
        return param;
    }

    private void verifyAlgoMethodsCalled() {
        Mockito.verify(algo).setUp();
        Mockito.verify(algo).setAlgoParam(Mockito.anyInt(), Mockito.anyInt());
        Mockito.verify(algo).performCalc();
        Mockito.verify(algo).submitToMarket();
    }

    private void verifyAlgoCancelTradesNotCalled() {
        Mockito.verify(algo, Mockito.never()).cancelTrades();
    }
}






