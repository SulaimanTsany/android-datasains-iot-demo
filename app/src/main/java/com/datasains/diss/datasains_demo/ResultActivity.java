package com.datasains.diss.datasains_demo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;

public class ResultActivity extends AppCompatActivity {
    TextView tvPh;
    TextView date;
    Switch swAlcohol;
    ProgressBar pb;

    float ph = 0;
    int alcohol = 0;
    String dateResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.reload_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reload();
            }
        });
        pb = findViewById(R.id.pb_main);
        tvPh = findViewById(R.id.phValue);
        swAlcohol = findViewById(R.id.alcoholValue);
        date = findViewById(R.id.lastUpdate);
        reload();

    }


    public void reload() {
        URL[] urls = new URL[2];
        urls[0]= Api.getLatestFieldEntry(3); //3
        urls[1] = Api.getLatestFieldEntry(4); //4
//        Log//.e("url", urlPh.toString());
        new DataAsyncTask().execute(urls);
    }

    private class DataAsyncTask extends AsyncTask<URL, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(URL... urls) {
            URL urlPH = urls[0];
            URL urlAlcohol = urls[1];
            String[] result = new String [2];
            try {
                result[0] = Network.getFromNetwork(urlPH);
                result[1] = Network.getFromNetwork(urlAlcohol);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("result", result[0]);
            return result;
        }

        @Override
        protected void onPostExecute(String[] s) {
            super.onPostExecute(s);

            String dateResult = "";
            FieldThingSpeak field3 = null;
            FieldThingSpeak field4 = null;
            try {
                JSONObject jsonObjectPh = new JSONObject(s[0]);
                JSONObject jsonObjectAlcohol = new JSONObject(s[1]);
                field3 = new FieldThingSpeak(jsonObjectPh, 3);
                field4 = new FieldThingSpeak(jsonObjectAlcohol, 4);
                dateResult = field3.getCreate_at();
                field3.getCreate_at();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (field3 != null) {
                tvPh.setText(field3.getField_x());
                date.setText(dateResult);
            }
            if (field4 != null) {
                boolean alcoholStatus = field4.getValue() == 0 ? true: false;
                swAlcohol.setChecked(alcoholStatus);
            }

            pb.setVisibility(View.GONE);
        }

    }
}
