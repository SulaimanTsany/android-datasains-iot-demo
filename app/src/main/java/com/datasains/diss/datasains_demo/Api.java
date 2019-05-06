package com.datasains.diss.datasains_demo;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class Api {
    private static final String API_KEY = "BNUXWSIKWOS5TGV0";
    private static final String BASE_URL = "https://api.thingspeak.com/channels/724253/fields";
    private static final String KEY = "api_key";

    public static URL getLatestFieldEntry(int field){
        // https://api.thingspeak.com/channels/724253/fields/2/last.json?api_key=BNUXWSIKWOS5TGV0
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(String.valueOf(field))
                .appendPath("last.json")
                .appendQueryParameter(KEY, API_KEY)
                .build();
        URL url = null;
        try{
            url = new URL(uri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

}