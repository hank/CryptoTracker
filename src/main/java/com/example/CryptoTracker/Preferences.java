package com.example.CryptoTracker;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Created with IntelliJ IDEA.
 * User: hank
 * Date: 11/27/13
 * Time: 11:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Preferences extends PreferenceActivity {
    private SharedPreferences preferences;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}