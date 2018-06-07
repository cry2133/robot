package com.robot.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@ConditionalOnProperty(prefix = "robot", name = "spring-session-open", havingValue = "true")
public class SpringSessionConfig {

}
