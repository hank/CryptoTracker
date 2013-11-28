package org.jointsecurityarea.android.CryptoTracker;

import com.jayway.jsonpath.JsonPath;

import java.net.MalformedURLException;
import java.net.URL;

public class BTCEFTCBTCTicker extends Ticker {
    BTCEFTCBTCTicker() throws MalformedURLException {
        super();
        this.symbol = "FTC";
        this.url = new URL("https://btc-e.com/api/2/ftc_btc/ticker");
        jsonPathStr = "$.ticker.last";
        jsonPath = JsonPath.compile(jsonPathStr);
    }
}
