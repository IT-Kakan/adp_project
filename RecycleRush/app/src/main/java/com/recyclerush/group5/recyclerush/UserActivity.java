package com.recyclerush.group5.recyclerush;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.recyclerush.group5.recyclerush.MainActivity;
import com.recyclerush.group5.recyclerush.userClass;

import java.util.HashMap;

public class UserActivity extends Activity {

    EditText eText;
    HashMap<String, userClass> map = new HashMap<String, userClass>();
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
                   userClass user = addMember(userLogin);
                  // finish();


                   // TOOODOOO
                   //openScanner();
                   //start camera with the user.
              }
              //else do nothing
            }

        });
    }

    private userClass addMember(String user){
        if (getUser(user) == null){
            //user not in list
            map.put(user, new userClass(user));
        }
            return getUser(user);
    }

    private userClass getUser(String id){
        return map.get(id);
    }

}
