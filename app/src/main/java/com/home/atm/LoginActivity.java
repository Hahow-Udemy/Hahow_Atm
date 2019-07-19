package com.home.atm;

import android.content.DialogInterface;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName() ;
    private EditText edUserid;
    private EditText edPasswd;
    private CheckBox cbRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate:" + edUserid);
        edUserid = findViewById(R.id.userid);
        edPasswd = findViewById(R.id.passwd);
        cbRemember = findViewById(R.id.cb_rem_userid);
        cbRemember.setChecked(getSharedPreferences("atm",MODE_PRIVATE)
                    .getBoolean("REMEMBER_USERID", false));
        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getSharedPreferences("atm", MODE_PRIVATE)
                        .edit()
                        .putBoolean("REMEMBER_USERID", isChecked)
                        .apply();
            }
        });
        String userid = getSharedPreferences("atm", MODE_PRIVATE)
                .getString("userid", "");
        edUserid.setText(userid);
    }
    public void login(View view){
        final String userid = edUserid.getText().toString();
        final String passwd = edPasswd.getText().toString();
        FirebaseDatabase.getInstance().getReference("users").child(userid).child("password")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String pw = (String) dataSnapshot.getValue();
                        if (pw.equals(passwd)){
                            boolean remember = getSharedPreferences("atm", MODE_PRIVATE)
                                    .getBoolean("REMEMBER_USERID", false);
                            if (remember){
                                getSharedPreferences("atm",MODE_PRIVATE)
                                        .edit()
                                        .putString("userid", userid)
                                        .apply();
                            }
                                setResult(RESULT_OK);
                                finish();
                        }else{
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("登入結果")
                                    .setMessage("登入失敗")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            edPasswd.setText("");
                                            edUserid.setText("");
                                        }
                                    })
                                    .show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
//        if ("jack".equals(userid) && "1234".equals(passwd)){
////
////        }
    }

    public void quit(View view){


    }
}
