package com.example.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Root Configuration
 */
@Component
@ComponentScan(basePackageClasses = RepositoryComponents.class)
public class RepositoryRootConfig {
}
