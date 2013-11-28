package com.example.CryptoTracker;

public class BTCELTCBTCTicker extends BTCETicker {
    BTCELTCBTCTicker() {
        this.symbol = "LTC";
        this.url = "https://btc-e.com/api/2/ltc_btc/ticker";
    }
}
