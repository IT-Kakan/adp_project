package com.recyclerush.group5.recyclerush;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.recyclerush.group5.recyclerush.MainActivity;
import com.recyclerush.group5.recyclerush.userClass;

import java.util.HashMap;

public class UserActivity extends Activity {

    EditText eText;
    String userLogin;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        eText= findViewById(R.id.editText);

        loginButton = findViewById(R.id.button3);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String userLogin = eText.getText().toString();

               if(userLogin.length() > 2){
                 //  userClass user = addMember(userLogin);
                   Intent intent = new Intent();
                   intent.putExtra("name", userLogin);
                   setResult(RESULT_OK, intent);
                   finish();
              }
              //else do nothing
            }

        });
    }
}
