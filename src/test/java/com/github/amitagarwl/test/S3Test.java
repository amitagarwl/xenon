package com.github.amitagarwl.test;

import com.github.amitagarwl.utils.S3utils;
import groovy.util.logging.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class S3Test {


    @Test(enabled = true)
    public void test(){

        S3utils s3utils = new S3utils();
        s3utils.listObjects("test-bucket");
    }
}
