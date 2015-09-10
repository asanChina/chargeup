package com.pengjiezhang.chargeup.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.pengjiezhang.chargeup.R;


public class MainActivity extends Activity {
    public static final String TAG = MainActivity.class.getName();

    private TextView statusTextView = null;
    private Button loginButton = null;
    private Button logoutButton = null;
    private TextView userProfileEditTextView = null;
    private TextView usernameTextView = null;
    private TextView emailTextView = null;
    private TextView passwordTextView = null;
    private TextView deviceInfoEditTextView = null;
    private TextView deviceNameTextView = null;
    private TextView deviceSystemTextView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    //this function just initialize basic functionality for this activity
    private void init(){
        statusTextView = (TextView)findViewById(R.id.status);
        loginButton = (Button)findViewById(R.id.login);
        logoutButton = (Button)findViewById(R.id.logout);
        userProfileEditTextView = (TextView)findViewById(R.id.user_profile_edit);
        usernameTextView = (TextView)findViewById(R.id.username);
        emailTextView = (TextView)findViewById(R.id.email);
        passwordTextView = (TextView)findViewById(R.id.password);
        deviceInfoEditTextView = (TextView)findViewById(R.id.device_info_edit);
        deviceNameTextView = (TextView)findViewById(R.id.device_name);
        deviceSystemTextView = (TextView)findViewById(R.id.device_system);
    }
}
