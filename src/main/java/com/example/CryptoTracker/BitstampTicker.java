package com.example.CryptoTracker;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class BitstampTicker extends Ticker {
    final private String TAG = this.getClass().getSimpleName();

    void parseData() {
        String data = getData(this.url);
        if(data == null)
            return;
        try {
            Log.i(TAG, "Data: "+data);
            JSONObject ticker = new JSONObject(data);
            if(ticker != null)
                this.last = ticker.optString("last");
        } catch (JSONException e) {
            Log.i(TAG, "Failed to parse JSON Object: "+e.getMessage());
        }
    }
}
