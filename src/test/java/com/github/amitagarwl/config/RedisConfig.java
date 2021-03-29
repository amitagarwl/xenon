package com.github.amitagarwl.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisConfig {

    private String redisHost;
    private String redisPassword;
    private String redisKey;

}