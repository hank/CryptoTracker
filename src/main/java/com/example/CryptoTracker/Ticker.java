package com.example.CryptoTracker;

import android.util.Log;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

abstract public class Ticker {
    String symbol;
    String url;
    private String holdings;

    String getData(String surl) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(surl);
            connection = (HttpsURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonStringBuilder = new StringBuilder(in.available());
            String line;
            while ((line = r.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
            return jsonStringBuilder.toString();
        } catch (MalformedURLException e) {
            Log.i(TAG, "Failed to parse URL");
        } catch (IOException e) {
            Log.i(TAG, "Failed to make stream");
        } finally {
            if(connection != null)
                connection.disconnect();
        }
        return null;
    }

    abstract void parseData();

    final private String TAG = this.getClass().getSimpleName();
    String last;

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
