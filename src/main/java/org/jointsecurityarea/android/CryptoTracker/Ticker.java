package org.jointsecurityarea.android.CryptoTracker;

import android.util.Log;
import com.jayway.jsonpath.JsonPath;
import java.io.*;
import java.net.URL;

abstract public class Ticker {
    final private String TAG = this.getClass().getSimpleName();
    protected String last;
    protected String symbol;
    protected URL url;
    protected String holdings;
    protected String jsonPathStr;
    protected JsonPath jsonPath;

    void parseData() {
        try {
            // Uses the supplied JSON path to parse the data from the URL
            this.last = this.jsonPath.read(this.url).toString();
        } catch (IOException e) {
            Log.i(TAG, "Failed to parse JSON Object: " + e.getMessage());
        }
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getSymbol() {

        return symbol;
    }

    public void setHoldings(String holdings) {
        this.holdings = holdings;
    }

    public String getHoldings() {
        return holdings;
    }
}
