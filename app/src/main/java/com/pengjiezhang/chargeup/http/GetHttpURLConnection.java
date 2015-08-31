package com.pengjiezhang.chargeup.http;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Pengjie on 8/31/2015.
 * This class is just a wrapper for functionality of HttpURLConnection, not subclass of HttpURLConnection.
 * This class use "GET" method to talk with the server
 */
public class GetHttpURLConnection {

    public String execute(String str) throws MalformedURLException{
        URL url = new URL(str);
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
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
