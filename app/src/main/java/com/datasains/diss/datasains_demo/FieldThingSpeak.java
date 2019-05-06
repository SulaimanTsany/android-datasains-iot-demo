package com.datasains.diss.datasains_demo;

import org.json.JSONException;
import org.json.JSONObject;

public class FieldThingSpeak {
    private String create_at;
    private String entry_id;
    private String field_x;

    public FieldThingSpeak(){}
    public FieldThingSpeak(JSONObject object, int field) {
        try {
            this.create_at = object.getString("created_at");
            this.entry_id = object.getString("entry_id");
            this.field_x = object.getString("field"+field);
            int ss = 0;
        } catch (JSONException e) {
        }
    }

    public String getCreate_at() {
        if (this.create_at == null) {
            return create_at;
        }
        String result = "";
        String[] arr = this.create_at.split("-|:|T|Z");
        String month = "";
        if (arr.length < 5) {
            return create_at;
        }
        switch (arr[1]) {
            case "01" :
                month = "January";
                break;
            case "02" :
                month = "February";
                break;
            case "03" :
                month = "March";
                break;
            case "04" :
                month = "April";
                break;
            case "05" :
                month = "May";
                break;
            case "06" :
                month = "June";
                break;
            case "07" :
                month = "July";
                break;
            case "08" :
                month = "August";
                break;
            case "09" :
                month = "September";
                break;
            case "10" :
                month = "October";
                break;
            case "11" :
                month = "November";
                break;
            case "12" :
                month = "December";
                break;
        }
        result = arr[3]+ " "+ month + " " + arr[0] + " ("+arr[3]+":"+arr[4]+":"+arr[5]+")";
        return result;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(String entry_id) {
        this.entry_id = entry_id;
    }

    public String getField_x() {
        return field_x;
    }

    public float getValue() {
        return Float.valueOf(this.field_x);
    }

    public void setField_x(String field_x) {
        this.field_x = field_x;
    }
}
