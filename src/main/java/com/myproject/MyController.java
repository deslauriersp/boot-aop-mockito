package com.myproject;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private MyService myService;

    @Autowired
    public MyController(MyService myService) {
        this.myService = myService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/object")
    public Object getMessage() throws MyException {
        Map<String, Object> myMap = new HashMap<>();
        myMap.put("response", myService.getAnObject());
        return myMap;
    }

}
