package com.pengjiezhang.chargeup;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class LoginActivity extends Activity {
    public static final String TAG = "LoginActivityTag";

    EditText username = null;
    EditText password = null;
    Button login = null;
    Button cancel = null;
    TextView register = null;
    TextView forgotPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        if(savedInstanceState != null){
            username.setText(savedInstanceState.getString("username", ""));
            password.setText(savedInstanceState.getString("password", ""));
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("username", username.getText().toString());
        outState.putString("password", password.getText().toString());
    }


    //this function just initialize basic functionality for this activity
    private void init(){
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        cancel = (Button)findViewById(R.id.cancel);
        register = (TextView)findViewById(R.id.register);
        forgotPassword = (TextView)findViewById(R.id.forgot_password);
    }
}
