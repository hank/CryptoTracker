package com.example.CryptoTracker;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Balance extends ListActivity {
    final private String TAG = this.getClass().getSimpleName();
    private ArrayList<Ticker> tickers;
    private TickerArrayAdapter ticker_adapter;
    private BitstampBTCUSDTicker bitstamp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.tickers = new ArrayList<Ticker>();
        this.bitstamp = new BitstampBTCUSDTicker();
        this.tickers.add(this.bitstamp);
        this.tickers.add(new BTCELTCBTCTicker());
        this.tickers.add(new BTCEFTCBTCTicker());
        this.tickers.add(new BTCENMCBTCTicker());
        this.ticker_adapter = new TickerArrayAdapter(
                this, android.R.layout.simple_expandable_list_item_1);
        setListAdapter(this.ticker_adapter);
        new AsyncFetch().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new AsyncFetch().execute();
    }

    class TickerArrayAdapter extends ArrayAdapter<Ticker> {
        public TickerArrayAdapter(Activity activity, int simple_expandable_list_item_1) {
            super(activity, simple_expandable_list_item_1);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i(TAG, "In getView()");
            View v;
            if(null == convertView)
                v = getLayoutInflater().inflate(R.layout.ticker_row, null);
            else
                v = convertView;
            TextView currency  = (TextView)v.findViewById(R.id.currency);
            TextView amountBTC = (TextView)v.findViewById(R.id.amountBTC);
            TextView amountUSD = (TextView)v.findViewById(R.id.amountUSD);
            TextView amountUSDTotal = (TextView)v.findViewById(R.id.amountUSDTotal);

            currency.setText(getItem(position).getSymbol());
            NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);

            if(getItem(position).getLast() != null) {
                Ticker item = getItem(position);
                if(item.getClass() == BitstampBTCUSDTicker.class) {
                    // It's BTC/USD!
                    amountBTC.setText("1 BTC");
                    amountUSD.setText("$"+item.getLast());
                    if(item.getHoldings() != null) {
                        BigDecimal i = new BigDecimal(item.getLast());
                        BigDecimal j = new BigDecimal(item.getHoldings());
                        BigDecimal total = i.multiply(j);
                        amountUSDTotal.setText(n.format(total.doubleValue()));
                    }
                } else {
                    // It's ???/BTC
                    amountBTC.setText(item.getLast());
                    // Update price using BTC/USD
                    String bitstamp_last = bitstamp.getLast();
                    if(bitstamp_last != null) {
                        BigDecimal i = new BigDecimal(bitstamp_last);
                        BigDecimal j = new BigDecimal(getItem(position).getLast());
                        BigDecimal lastDollars = (i.multiply(j)).setScale(4, RoundingMode.HALF_UP);
                        amountUSD.setText(n.format(lastDollars.doubleValue()));
                        // Set total holdings
                        if(item.getHoldings() != null) {
                            BigDecimal k = new BigDecimal(item.getHoldings());
                            BigDecimal total = lastDollars.multiply(k);
                            amountUSDTotal.setText(n.format(total.doubleValue()));
                        }
                    }
                }

            }
            return v;
        }
    }

    class AsyncFetch extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Balance.this, ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("Loading data");
            progressDialog.setMessage("Refreshing prices");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            int i = 0;
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Balance.this);
            for(Ticker ticker: tickers) {
                String holdings = preferences.getString("pref"+ticker.getSymbol()+"Holdings", "0.00");
                ticker.parseData();
                ticker.setHoldings(holdings);
                ++i;
                // Set progress to percent we're at
                progressDialog.setProgress((i / tickers.size()) * 100);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void params) {
            super.onPostExecute(params);
            progressDialog.dismiss();
            ArrayAdapter<Ticker> adapter = (ArrayAdapter<Ticker>)getListAdapter();
            adapter.clear();
            adapter.addAll(tickers);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_settings:
                intent = new Intent(getApplicationContext(), Preferences.class);
                startActivity(intent);
                break;
            case R.id.action_refresh:
                new AsyncFetch().execute();
                break;
            default:
                break;
        }

        return true;
    }
}
