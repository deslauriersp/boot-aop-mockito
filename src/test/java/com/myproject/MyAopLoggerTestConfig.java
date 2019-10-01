package com.myproject;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableAspectJAutoProxy
public class MyAopLoggerTestConfig {

    @Bean
    @Primary
    public MyService myServiceMock() {
        return Mockito.mock(MyService.class);
    }
    
}
