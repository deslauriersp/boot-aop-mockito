package com.myproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyLogWriter {

    private Logger logger = LoggerFactory.getLogger(MyLogWriter.class);

    public void writeLog(String log) {
        logger.info(log);
    }

}
