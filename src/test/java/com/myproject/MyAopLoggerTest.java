package com.myproject;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.AopTestUtils;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(classes = { MyAopLoggerTestConfig.class })
public class MyAopLoggerTest {

    // class that we are testing
    @SpyBean
    private MyAopLogger myAopLogger;

    @MockBean
    private MyLogWriter myLogWriter;

    @Autowired // autowired and configured in a separate config as we want AOP enhanced bean
    private MyService myService;

    @Test
    public void testBeforeAndAfter() throws MyException {

        reset(myLogWriter);
        reset(myAopLogger);
        reset(myService);

        when(AopTestUtils.<MyService>getUltimateTargetObject(myService).getAnObject()).thenReturn("a string");

        try {
            myService.getAnObject();
        } catch (MyException e) {
            fail();
        }

        verify(myAopLogger).before(any());
        verify(AopTestUtils.<MyService>getUltimateTargetObject(myService)).getAnObject();
        verify(myAopLogger).after(any(), any());
        verify(myLogWriter, times(2)).writeLog(any());

    }

    @Test
    public void testBeforeAndAfterThrowing() throws MyException {

        reset(myLogWriter);
        reset(myAopLogger);
        reset(myService);

        when(AopTestUtils.<MyService>getUltimateTargetObject(myService).getAnObject()).thenThrow(new MyException());

        try {
            myService.getAnObject();
            fail();
        } catch (MyException e) {
            // nop
        }

        verify(myAopLogger).before(any());
        verify(AopTestUtils.<MyService>getUltimateTargetObject(myService)).getAnObject();
        verify(myAopLogger).afterThrowing(any(), any());
        verify(myLogWriter, times(2)).writeLog(any());

    }

}
