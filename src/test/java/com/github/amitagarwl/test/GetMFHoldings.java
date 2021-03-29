package com.github.amitagarwl.test;

import com.github.amitagarwl.BaseTest;
import com.github.amitagarwl.config.Service;
import com.github.amitagarwl.helpers.KiteClient;
import com.github.amitagarwl.helpers.RestClient;
import com.github.amitagarwl.utils.Path;
import io.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.path.json.JsonPath.from;

@Slf4j
@Test(alwaysRun = true, groups = {"all"})
public class GetMFHoldings extends BaseTest {

    JsonPath eval;

    @DataProvider(name = "dataSet")
    public static Object[][] getHoldingsData(){

        return new Object[][]
                {
                        {"negative", "error", "InputException"},
                        {"positive", ""}
                };
    }


    @Test(enabled = true, dataProvider = "dataSet")
    public void getHoldings(String scenario, String status, String error) {

        String payload = KiteClient.createPayload();
        response = RestClient
                .getCall(route.getKiteRoutes().get("KITE_PLACE_ORDERS_POST"), route.getKiteHeaders(), payload);
        response.prettyPrint();
        Assert.assertEquals(from(response.asString()).getString("status"), status);
        Assert.assertEquals(from(response.asString()).getString("error_type"), error);

    }

    @Test(priority = 2)
    public void testHealthEndpoint(){

        response = RestClient.getCall(config.getServiceConfigMap().get(Service.TFS).getHostname()
                        + Path.TFS_GET_HEALTH_ENDPOINT.toString());

        Assert.assertEquals(response.htmlPath().getString("html.body"),
                "{\"status\":\"ok\",\"version\":\"2.1.186.695\"}");
    }


}
