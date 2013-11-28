package com.example.CryptoTracker;

public class BTCEFTCBTCTicker extends BTCETicker {
    BTCEFTCBTCTicker() {
        this.symbol = "FTC";
        this.url = "https://btc-e.com/api/2/ftc_btc/ticker";
    }
}
