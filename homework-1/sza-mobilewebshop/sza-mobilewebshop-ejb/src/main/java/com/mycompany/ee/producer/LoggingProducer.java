package com.mycompany.ee.producer;

import java.util.logging.Logger;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggingProducer {

    public Logger produceLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}