package org.jointsecurityarea.android.CryptoTracker;

import com.jayway.jsonpath.JsonPath;

import java.net.MalformedURLException;
import java.net.URL;

public class BTCENMCBTCTicker extends Ticker {
    BTCENMCBTCTicker() throws MalformedURLException {
        super();
        this.symbol = "NMC";
        this.url = new URL("https://btc-e.com/api/2/nmc_btc/ticker");
        jsonPathStr = "$.ticker.last";
        jsonPath = JsonPath.compile(jsonPathStr);
    }
}
