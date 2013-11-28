package org.jointsecurityarea.android.CryptoTracker;

import com.jayway.jsonpath.JsonPath;

import java.net.MalformedURLException;
import java.net.URL;

public class BTCELTCBTCTicker extends Ticker {
    BTCELTCBTCTicker() throws MalformedURLException {
        super();
        this.symbol = "LTC";
        this.url = new URL("https://btc-e.com/api/2/ltc_btc/ticker");
        jsonPathStr = "$.ticker.last";
        jsonPath = JsonPath.compile(jsonPathStr);
    }
}
