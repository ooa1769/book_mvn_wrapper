package com.ooa1769.bs;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan(
        basePackageClasses = {Jsr310JpaConverters.class},
        basePackages = {"com.ooa1769.bs"}
)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BSApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BSApp.class)
                .properties("spring.config.location=classpath:kakao.properties, classpath:naver.properties")
                .run(args);
    }
}
