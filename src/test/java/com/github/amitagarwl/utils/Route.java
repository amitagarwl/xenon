package com.github.amitagarwl.utils;

import com.github.amitagarwl.config.Config;
import com.github.amitagarwl.config.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

import static com.github.amitagarwl.utils.Path.*;



@Data
@AllArgsConstructor
@Slf4j
public class Route {

    private HashMap<String,String> kiteRoutes;
    private HashMap<String,String> kiteHeaders;
    private String kiteHostname;


    public Route(Config config) {

        kiteHostname = config.getServiceConfigMap().get(Service.KITE).getHostname();
        kiteHeaders = new HashMap<>();
        kiteRoutes = new HashMap<String, String>() {{

            put("KITE_PLACE_ORDERS_POST", kiteHostname + KITE_PLACE_ORDERS_POST.toString());
            put("KITE_CANCEL_ORDERS_DELETE", kiteHostname + KITE_CANCEL_ORDERS_DELETE.toString());
            put("KITE_GET_ORDERS_GET", kiteHostname + KITE_GET_ORDERS_GET.toString());
            put("KITE_RETRIEVE_ORDER_GET", kiteHostname + KITE_RETRIEVE_ORDER_GET.toString());
            put("KITE_SIP_ORDERS_POST", kiteHostname + KITE_SIP_ORDERS_POST.toString());
            put("KITE_GET_HOLDINGS_GET", kiteHostname + KITE_GET_HOLDINGS_GET.toString());
            put("KITE_GET_LIST_OF_MUTUAL_FUNDS_GET", kiteHostname + KITE_GET_LIST_OF_MUTUAL_FUNDS_GET.toString());

        }};
        String headers = config.getServiceConfigMap().get(Service.KITE).getHeaders();
        String headerList[] = headers.split(",");
        for (int i = 0; i < headerList.length; i++) {
            String[] keyValue = headerList[i].split(":");
            kiteHeaders.put(keyValue[0], keyValue[1]);
        }

    }

    public HashMap<String, String> getKiteHeaders() {
        return kiteHeaders;
    }




}
