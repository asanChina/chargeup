package com.pengjiezhang.chargeup.http;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Pengjie on 8/31/2015.
 * This class is just a wrapper for functionality of HttpURLConnection, not subclass of HttpURLConnection.
 * This class use "POST" method to talk with the server, the data posted is JSONObject
 */
public class PostHttpURLConnection {
    public String execute(String str, JSONObject jsonObject) throws MalformedURLException {
        URL url = new URL(str);
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
            out.write(jsonObject.toString());

            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String tmp;
            while ((tmp = in.readLine()) != null)
                response.append(tmp);
            return response.toString();
        }
        catch(IOException e){
            return null;
        }
        finally {
            try{
                urlConnection.disconnect();
                in.close();
            }
            catch(Exception e){
                //do nothing
            }
        }
    }
}
