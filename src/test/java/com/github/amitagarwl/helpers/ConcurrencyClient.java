package com.github.amitagarwl.helpers;

import com.github.amitagarwl.utils.Method;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.concurrent.Callable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConcurrencyClient implements Callable<Response> {

    private Method methodType;
    private String endpoint;
    private HashMap<String, String> headers;
    private String payload;


    public Response call() throws Exception {
        Response response = null;
        switch (methodType) {
            case GET:
                response = RestClient.getCall(endpoint, headers, payload);
                break;
            case POST:
                response = RestClient.postCall(endpoint, headers, payload);
                break;
            case PUT:
                response = RestClient.putCall(endpoint, headers, payload);
                break;
        }
        return response;
    }
}
