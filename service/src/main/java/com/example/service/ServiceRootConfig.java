package com.example.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Root Configuration
 */
@Configuration
@ComponentScan(basePackageClasses = ServiceComponents.class)
public class ServiceRootConfig {
}
