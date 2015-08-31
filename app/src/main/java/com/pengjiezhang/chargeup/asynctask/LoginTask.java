package com.pengjiezhang.chargeup.asynctask;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

/**
 * Created by Pengjie on 8/31/2015.
 * Since wireless network is unreliable, we'd better delegate "connecting server" job to another background thread
 */
public class LoginTask extends AsyncTask<String, Integer, Boolean>{

    @Override
    protected Boolean doInBackground(String... params) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", params[0]);
            jsonObject.put("password", params[1]);

        }
        catch(JSONException e){
            return false;
        }
        return true;
    }
}
