package com.github.amitagarwl.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalServiceConfig {

    private String hostname;
    private String headers;
}
