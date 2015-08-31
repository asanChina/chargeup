package com.pengjiezhang.chargeup.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.pengjiezhang.chargeup.listener.LoginTaskCallback;
import com.pengjiezhang.chargeup.listener.LogoutTaskCallback;
import com.pengjiezhang.chargeup.listener.SearchTaskCallback;
import com.pengjiezhang.chargeup.listener.UpdateTaskCallback;

import org.json.JSONObject;

/**
 * Created by Pengjie on 8/31/2015.
 * This class is used to run some code in background. Suppose in a situation. user input user name and password, then click
 * login button, then click home button (because he know this would take a while). our "login" process
 * should still continue going on
 *
 * This class is a local service
 */
public class TaskService extends Service implements LoginTaskCallback, LogoutTaskCallback, SearchTaskCallback, UpdateTaskCallback{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void loginTaskFinished(boolean successful) {

    }

    @Override
    public void logoutTaskFinished(boolean successful) {

    }

    @Override
    public void searchTaskFinished(boolean successful, JSONObject jsonObject) {

    }

    @Override
    public void updateTaskFinished(boolean successful) {

    }
}
