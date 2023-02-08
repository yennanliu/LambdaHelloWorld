package com.yen;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;


/**
 *  Unit test for SimpleHandler
 *      - https://youtu.be/kyWllXOGMWQ?t=474
 */
@ExtendWith(MockitoExtension.class)
public class SimpleHandlerTest {

    /**
     *  here we HAVE to mock Context, LambdaLogger
     *      - https://youtu.be/kyWllXOGMWQ?t=632
     */
    private SimpleHandler simpleHandler;

    // 1) mock Context, LambdaLogger
    @Mock
    Context context;

    @Mock
    LambdaLogger logger;

    @BeforeEach
    public void setup(){
        // 2) if context.getLogger() is called, then return logger, which is the mocked class
        when(context.getLogger()).thenReturn(logger);

        // 3) replace logger.log() with System.out.Println
        doAnswer(call -> {
            System.out.println((String)call.getArgument(0));
            return null;
        }).when(logger).log(anyString());

        // 4) return mock simpleHandler (?)
        simpleHandler = new SimpleHandler();
    }

    @Test
    public void shouldReturnUpperCaseOfInput(){

        // 5) write the test logic
        when(context.getFunctionName()).thenReturn("handleRequest");
        System.out.println(">>> mock context = " + context);
        System.out.println(">>> mock logger = " + logger);
        Assertions.assertEquals("HELLO WORLD!", simpleHandler.handleRequest("hello world!", context));
    }

}