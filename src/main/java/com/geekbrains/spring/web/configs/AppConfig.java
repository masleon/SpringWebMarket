package com.geekbrains.spring.web.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("secret.properties")
public class AppConfig {
}
