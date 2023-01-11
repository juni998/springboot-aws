package com.jpa.aws.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPAAuditing 활성화
 *
 * @Test => 일반적인 @Configuration 스캔하지 않음
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
