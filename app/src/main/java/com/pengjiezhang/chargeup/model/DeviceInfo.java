package com.pengjiezhang.chargeup.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.util.Map;

/**
 * Created by Pengjie on 8/31/2015.
 * This class is used to get device information, there are two situation:
 * 1) User install our app and register first time, this class will be called and device info will be stored in shared preference
 * 2) when user login, this class will read device info from shared preference
 */
public class DeviceInfo {
    public static Activity mActivity;

    public static JSONObject getDeviceInfo() throws JSONException{
        File f = new File("/data/data/com.pengjiezhang.chargeup/shared_prefs/device_info.xml");
        JSONObject jsonObject = new JSONObject();
        if (f.exists()){
            SharedPreferences prefs = mActivity.getSharedPreferences("device_info", Context.MODE_PRIVATE);
            Map<String, ?> stored = prefs.getAll();
            for(String key : stored.keySet())
                jsonObject.put(key, stored.get(key));
            return jsonObject;
        }
        else{

        }
        return jsonObject;
    }
}
