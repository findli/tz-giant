package com.giant.boot;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
@Data
public class AppConfig {
    String giantCliPath;
    String giantConfFilePath;
}