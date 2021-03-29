package com.github.amitagarwl.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class S3Config {

    private String accessKey;
    private String secretKey;
    private String bucketName;
}
