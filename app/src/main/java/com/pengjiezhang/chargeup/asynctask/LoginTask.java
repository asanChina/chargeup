package com.pengjiezhang.chargeup.asynctask;

import android.os.AsyncTask;

import com.pengjiezhang.chargeup.http.PostHttpURLConnection;
import com.pengjiezhang.chargeup.listener.LoginTaskCallback;
import com.pengjiezhang.chargeup.model.ServerInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;

/**
 * Created by Pengjie on 8/31/2015.
 * Since wireless network is unreliable, we'd better delegate "connecting server" job to another background thread
 */
public class LoginTask extends AsyncTask<String, Integer, String>{

    private LoginTaskCallback caller = null;

    public LoginTask(LoginTaskCallback c){
        caller = c;
    }


    @Override
    protected String doInBackground(String... params) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", params[0]);
            jsonObject.put("password", params[1]);
        }
        catch(JSONException e){
            //do nothing
        }

        String result = null;
        try {
            result = PostHttpURLConnection.execute(ServerInfo.LOGIN, jsonObject);
        }
        catch(MalformedURLException e){
            //do nothing
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        if(s == null)
            caller.onLoginTaskFinished(true, s);
        else
            caller.onLoginTaskFinished(false, s);
    }
}
