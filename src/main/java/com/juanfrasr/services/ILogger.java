package com.juanfrasr.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logger interface default log to loggefactory
 */

public interface ILogger {
    default Logger log(){
        return LoggerFactory.getLogger(getClass());
    }
}
