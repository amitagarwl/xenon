package com.github.amitagarwl.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbConfig {

    private String driverClass;
    private String url;
    private String username;
    private String password;
}
