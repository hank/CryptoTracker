package com.example.CryptoTracker;

public class BTCENMCBTCTicker extends BTCETicker {
    BTCENMCBTCTicker() {
        this.symbol = "NMC";
        this.url = "https://btc-e.com/api/2/nmc_btc/ticker";
    }
}
