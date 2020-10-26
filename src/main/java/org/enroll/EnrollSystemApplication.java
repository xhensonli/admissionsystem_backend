package org.enroll;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@MapperScan("org.enroll.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EnrollSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnrollSystemApplication.class, args);
    }

}
