package com.github.amitagarwl.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Config class mapper
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Config {

    private DbConfig masterDbConfig;
    private S3Config s3Config;
    private RedisConfig redisConfig;
    private Map<Service, ExternalServiceConfig> serviceConfigMap;

}
