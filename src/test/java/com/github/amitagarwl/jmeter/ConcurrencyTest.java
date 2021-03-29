package com.github.amitagarwl.jmeter;

import com.github.amitagarwl.BaseTest;
import com.github.amitagarwl.config.Service;
import com.github.amitagarwl.helpers.ConcurrencyClient;
import com.github.amitagarwl.helpers.KiteClient;
import com.github.amitagarwl.utils.Method;
import com.github.amitagarwl.utils.Path;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class ConcurrencyTest extends BaseTest {

    private List<Future<Response>> futures;

    @DataProvider(name = "concurrencyData")
    public static Object[][] concurrencyData() {

        return new Object[][]{

                {2, 10},
                {10, 10}
        };
    }

    @Test(dataProvider = "concurrencyData")
    public void test(int threadPool, int invocationCount) throws InterruptedException, ExecutionException {
        String payload = KiteClient.createPayload();

        /* Create a fixed thread pool */
        ExecutorService executorService = Executors.newFixedThreadPool(threadPool);
        List<ConcurrencyClient> task = new ArrayList<>();

        /* Add all the task to be performed in a list */
        for (int i = 0; i < invocationCount; i++) {

            task.add(new ConcurrencyClient(Method.GET,
                    config.getServiceConfigMap().get(Service.TFS).getHostname()+ Path.TFS_GET_HEALTH_ENDPOINT.toString(),
                    route.getKiteHeaders(), payload));

        }
        /* Invoke all the task concurrently */
        futures = executorService.invokeAll(task);

        /* Wait for all the threads to complete */
        executorService.awaitTermination(2, TimeUnit.SECONDS);

        /* Extract the response from the futures and assert for the status code/ response */
        for (Future<Response> future : futures) {
            System.out.println(future.get().prettyPrint());
            Assert.assertEquals(future.get().getStatusCode(), 200);
        }
    }
}
