package com.github.amitagarwl.listener;

import lombok.extern.slf4j.Slf4j;
import org.testng.IClass;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.Arrays;

@Slf4j
public class ListenerClass extends TestListenerAdapter {

    @Override
    public void onTestStart(ITestResult tr)
    {
        log.info("--------------------------------------------------------------------------------------------------------------------------------------------");
        log("Test:" + Thread.currentThread().getId() +":" + tr.getName() + " with parameters" + Arrays.toString(tr.getParameters())+ "' Started");
        log.info("--------------------------------------------------------------------------------------------------------------------------------------------");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log.info("--------------------------------------------------------------------------------------------------------------------------------------------");
        log("Test:" + Thread.currentThread().getId() +":" + tr.getName() + " with parameters" + Arrays.toString(tr.getParameters())+ "' PASSED");
        log.info("--------------------------------------------------------------------------------------------------------------------------------------------");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        log.info("--------------------------------------------------------------------------------------------------------------------------------------------");
        log("Test:" + Thread.currentThread().getId() +":" + tr.getName() + " with parameters" + Arrays.toString(tr.getParameters())+ "' FAILED = "+tr.getThrowable().getMessage() );
        log.info("--------------------------------------------------------------------------------------------------------------------------------------------");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log.info("--------------------------------------------------------------------------------------------------------------------------------------------");
        log("Test:" + Thread.currentThread().getId() +":" + tr.getName() + " with parameters" + Arrays.toString(tr.getParameters())+ "' SKIPPED");
        log.info("--------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private void log(String methodName) {
        log.info(methodName);
    }

    private void log(IClass testClass) {
        System.out.println(testClass);
    }

}
