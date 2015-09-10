package com.pengjiezhang.chargeup.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pengjiezhang.chargeup.R;
import com.pengjiezhang.chargeup.asynctask.LoginTask;
import com.pengjiezhang.chargeup.listener.LoginTaskCallback;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Pengjie on 9/9/2015.
 * This class is responsible for loginButton user. user can registerTextView here
 */
public class LoginActivity extends Activity implements LoginTaskCallback{
    private EditText usernameEditText = null;
    private EditText passwordEditText = null;
    private EditText emailEditText = null;
    private Button loginButton = null;
    private Button cancelButton = null;
    private TextView registerTextView = null;
    private TextView forgotPasswordTextView = null;
    private TextView wrongUsernameTextView = null;
    private TextView wrongEmailTextView = null;
    private TextView wrongPasswordTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    @Override
    public void onLoginTaskFinished(boolean exceptionHappened, String result) {
        if(exceptionHappened){
            failLogin();
            return;
        }


        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
        }
        catch(JSONException e){
            wrongUsernameTextView.setText("error in server, try later");
        }

        String status; //"successful" or "failed"
        String sessionId; //if status is successful then will have 40 characters session id
        try{
            status = jsonObject.getString("status");
            if(status.equals("successful"))
                sessionId = jsonObject.getString("sessionId");
        }
        catch(JSONException e){
            wrongUsernameTextView.setText("error in server, try later");
            return;
        }


    }

    /**
     * This class is used to initialize this LoginActivity
     */
    private void init(){
        wrongUsernameTextView = (TextView)findViewById(R.id.wrong_username);
        usernameEditText = (EditText)findViewById(R.id.username);
        wrongEmailTextView = (TextView)findViewById(R.id.wrong_email);
        emailEditText = (EditText)findViewById(R.id.email);
        wrongPasswordTextView = (TextView)findViewById(R.id.wrong_password);
        passwordEditText = (EditText)findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.login);
        cancelButton = (Button)findViewById(R.id.cancel);
        registerTextView = (TextView)findViewById(R.id.register);
        forgotPasswordTextView = (TextView)findViewById(R.id.forgot_password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkInternet()){
                    failInternet();
                    return;
                }

                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!checkFields(username, email, password)) {
                    wrongUsernameTextView.setText("username should have [6,20] characters!");
                    wrongEmailTextView.setText("should be valid email address");
                    wrongPasswordTextView.setText("password should have [8, 20] characters!");
                    return;
                }

                LoginTask task = new LoginTask(LoginActivity.this);
                task.execute(username, password);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(RESULT_CANCELED, returnIntent);
                finish();
            }
        });
    }


    /*=======================================================================================================
    * below are utility functions
    *========================================================================================================*/
    /**
     * This class is used to make sure that username and password satisfy requirements. This function can be changed later
     * to satisfy more requirements
     * @param username Username filled by user. the number of characters is [6, 20]
     * @param email Email filled by user. must be valid email address
     * @param password Password filled by user. the number of characters is [8, 20]
     * @return
     */
    private boolean checkFields(String username, String email, String password){
        int usernameLength = username.length();
        int passwordLength = password.length();
        return checkEmail(email)&&usernameLength>=6&&usernameLength<=20&&passwordLength>=8&&passwordLength<=20;
    }

    private void failLogin(){
        wrongUsernameTextView.setText("wrong user name");
        wrongPasswordTextView.setText("wrong password");
    }

    private void failInternet(){
        wrongUsernameTextView.setText("internet lost!!!");
    }

    private boolean checkInternet(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo()!=null;
    }

    private boolean checkEmail(String email){
        return true;
    }
}
