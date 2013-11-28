package com.example.CryptoTracker;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BTCETicker extends Ticker {
    final private String TAG = this.getClass().getSimpleName();

    void parseData() {
        String data = getData(this.url);
        if(data == null)
            return;
        try {
            Log.i(TAG, "Data: "+data);
            JSONObject obj = new JSONObject(data);
            JSONObject ticker = obj.optJSONObject("ticker");
            if(ticker != null)
                this.last = ticker.optString("last");
        } catch (JSONException e) {
            Log.i(TAG, "Failed to parse JSON Object: "+e.getMessage());
        }
    }
}
