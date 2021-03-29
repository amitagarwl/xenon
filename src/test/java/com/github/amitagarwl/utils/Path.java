package com.github.amitagarwl.utils;


public enum Path {

    KITE_PLACE_ORDERS_POST("/mf/orders"),
    KITE_CANCEL_ORDERS_DELETE("/mf/orders/{order_id}"),
    KITE_GET_ORDERS_GET("/mf/orders"),
    KITE_RETRIEVE_ORDER_GET("/mf/orders/{order_id}"),
    KITE_SIP_ORDERS_POST("/mf/sips"),
    KITE_GET_HOLDINGS_GET("/mf/holdings"),
    KITE_GET_LIST_OF_MUTUAL_FUNDS_GET("/mf/instruments"),
    TFS_GET_HEALTH_ENDPOINT("/health"),

    ;


    String endpoint;

    Path(String endpoint) {
        this.endpoint = endpoint;
    }

    public String toString() {
        return this.endpoint;
    }
}

