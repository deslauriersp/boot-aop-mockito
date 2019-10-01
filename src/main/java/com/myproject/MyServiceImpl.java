package com.myproject;

import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {

    @Override
    public Object getAnObject() throws MyException {
        return "a string";
    }

}
