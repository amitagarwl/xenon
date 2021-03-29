package com.github.amitagarwl.jmeter;

import com.github.amitagarwl.BaseTest;
import com.github.amitagarwl.config.Service;
import com.github.amitagarwl.utils.Constants;
import com.github.amitagarwl.utils.Path;
import lombok.extern.slf4j.Slf4j;
import org.apache.jmeter.threads.JMeterVariables;
import org.eclipse.jetty.http.HttpMethod;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import us.abstracta.jmeter.javadsl.core.DslTestPlan;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import static com.github.amitagarwl.utils.Constants.SUMMARY_HEADER;
import static com.github.amitagarwl.utils.Constants.SUMMARY_HEADER_VALUE;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

import org.eclipse.jetty.http.MimeTypes.Type;


/**
 * This is a jmeter dsl usage using java code instead of jmeter GUI
 * Library being used - https://github.com/abstracta/jmeter-java-dsl
 * Explore - https://gettaurus.org/
 */


@Slf4j
public class PerformanceTest extends BaseTest {


    String path = new String("src/test/resources");
    String file = new String(path + "/test.jtl");
    String jmxFile = new String(path + "/dsl-test-plan.jmx");
    String htmlReport = new String(path + "/html-report");


    /* Creates a test plan and execute http sampler */
    @Test
    public void performanceTest() throws IOException {

        /* Cleanup jtl file before test plan execution */
       new File(file).delete();

       /* Code to create Test Plan */
       TestPlanStats stats = testPlan(
                threadGroup(2, 10,
                        httpSampler("https://www.google.com/")
                                .post((String) null, Type.APPLICATION_JSON)
                ), jtlWriter(file)
        ).run();

        Assert.assertTrue(stats.overall().elapsedTimePercentile90().getSeconds() < 500);
    }

    /* Creates a jmx file which can be shared and run by peers */
    @Test
    public void saveTestPlan() throws IOException {

        testPlan(
                threadGroup(2, 10,
                        httpSampler("https://www.google.com/")
                )
        ).saveAsJmx(jmxFile);

    }

    /* Creates a jmx file which can be shared and run by peers */
    @Test
    public void performanceTestUsingJmxFile() throws IOException {

        TestPlanStats stats = DslTestPlan.fromJmx("dsl-test-plan.jmx").run();
        Assert.assertTrue(stats.overall().elapsedTimePercentile90().getSeconds() < 500);

    }

    /* Creates a html report */
    @Test
    public void performanceTestWitReport() throws IOException {
        TestPlanStats stats = testPlan(
                threadGroup(2, 10,
                        httpSampler(config.getServiceConfigMap().get(Service.TFS).getHostname() +
                                Path.TFS_GET_HEALTH_ENDPOINT.toString())
                        .method(HttpMethod.GET)
                ),jtlWriter(file),
                htmlReporter(htmlReport)
        ).run();
        Assert.assertTrue(stats.overall().elapsedTimePercentile90().getSeconds() < 500);
    }


    /* Creates a custom test plan using jmeter vars */
    @Test
    public void performanceTestWithCustomVars() throws IOException {
        TestPlanStats stats = testPlan(
                threadGroup(2, 10,
                        httpSampler("https://www.google.com/")
                                .header("Authorization","Bearer <auth_token>")
                                .post("${REQUEST_BODY}", Type.TEXT_PLAIN)
                                .children(
                                        jsr223PreProcessor(s -> s.vars.put("REQUEST_BODY", buildRequestBody(s.vars)))
                                )
                ), jtlWriter(file)
        ).run();
        Assert.assertTrue(stats.overall().elapsedTimePercentile90().getSeconds() < 500);
    }


    public static String buildRequestBody(JMeterVariables vars) {
        Iterator iterator = vars.entrySet().iterator();
       while (iterator.hasNext()){
           log.info(iterator.next().toString());
       }
        String countVarName = "REQUEST_COUNT";
        Integer countVar = (Integer) vars.getObject(countVarName);
        int count = countVar != null ? countVar + 1 : 1;
        vars.putObject(countVarName, count);
        return "MyBody" + count;
    }
}
