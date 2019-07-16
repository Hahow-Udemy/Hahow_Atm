package com.home.atm;

import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText edUserid;
    private EditText edPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUserid = findViewById(R.id.userid);
        edPasswd = findViewById(R.id.passwd);
    }
    public void login(View view){
        String userid = edUserid.getText().toString();
        String passwd = edPasswd.getText().toString();
        if ("jack".equals(userid) && "1234".equals(passwd)){
            setResult(RESULT_OK);
            finish();
        }
    }

    public void quit(View view){


    }
}
