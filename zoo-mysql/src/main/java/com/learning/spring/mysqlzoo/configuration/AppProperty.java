package com.learning.spring.mysqlzoo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "zooapp")
@Getter
@Setter
public class AppProperty 
{
    private String storagePath;

    private Features features;

    // --- nested class ---
    @Getter
    @Setter
    public static class Features {

        private int maxThreads;

        private boolean enableAudit;

    }

}
