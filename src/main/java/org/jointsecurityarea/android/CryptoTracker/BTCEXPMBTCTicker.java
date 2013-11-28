package org.jointsecurityarea.android.CryptoTracker;

import com.jayway.jsonpath.JsonPath;

import java.net.MalformedURLException;
import java.net.URL;

public class BTCEXPMBTCTicker extends Ticker {
    BTCEXPMBTCTicker() throws MalformedURLException {
        super();
        symbol = "XPM";
        url = new URL("https://btc-e.com/api/2/xpm_btc/ticker");
        jsonPathStr = "$.ticker.last";
        jsonPath = JsonPath.compile(jsonPathStr);
    }
}
