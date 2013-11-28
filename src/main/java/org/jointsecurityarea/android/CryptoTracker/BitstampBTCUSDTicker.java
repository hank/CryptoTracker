package org.jointsecurityarea.android.CryptoTracker;

import com.jayway.jsonpath.JsonPath;

import java.net.MalformedURLException;
import java.net.URL;

public class BitstampBTCUSDTicker extends Ticker {
    BitstampBTCUSDTicker() throws MalformedURLException {
        super();
        this.symbol = "BTC";
        this.url = new URL("https://www.bitstamp.net/api/ticker/");
        jsonPathStr = "$.last";
        jsonPath = JsonPath.compile(jsonPathStr);
    }
}
