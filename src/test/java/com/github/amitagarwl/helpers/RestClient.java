package com.github.amitagarwl.helpers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RestClient {


    public static Response getCall(final String endpoint){
        return given()
                .contentType(ContentType.JSON)
                .log().everything(true)
                .get(endpoint);
    }

    public static Response getCall(final String endpoint, final HashMap<String,String> headers, final String payload){
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .headers(headers)
                .body(payload)
                .get(endpoint);
    }

    public static Response getCall(final String endpoint, final HashMap<String,String> pathParams, final HashMap<String,String> headers, final String payload){
        return given()
                .contentType(ContentType.JSON)
                .log().everything(true)
                .pathParams(pathParams)
                .headers(headers)
                .body(payload)
                .get(endpoint);
    }

    public static Response postCall(final String endpoint, final HashMap<String,String> headers, final String payload){
        return given()
                .contentType(ContentType.JSON)
                .log().everything(true)
                .headers(headers)
                .body(payload)
                .post(endpoint);
    }

    public static Response postCall(final String endpoint, final HashMap<String,String> pathParams, final HashMap<String,String> headers, final String payload){
        return given()
                .contentType(ContentType.JSON)
                .log().everything(true)
                .pathParams(pathParams)
                .headers(headers)
                .body(payload)
                .post(endpoint);
    }

    public static Response putCall(final String endpoint, final HashMap<String,String> headers, final String payload){
        return given()
                .contentType(ContentType.JSON)
                .log().everything(true)
                .headers(headers)
                .body(payload)
                .put(endpoint);
    }

    public static Response putCall(final String endpoint, final HashMap<String,String> pathParams, final HashMap<String,String> headers, final String payload){
        return given()
                .contentType(ContentType.JSON)
                .log().everything(true)
                .pathParams(pathParams)
                .headers(headers)
                .body(payload)
                .put(endpoint);
    }







}
