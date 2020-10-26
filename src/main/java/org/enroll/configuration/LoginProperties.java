package org.enroll.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "enroll.login")
public class LoginProperties {

    private String adminName;

    private String adminPass;
}
