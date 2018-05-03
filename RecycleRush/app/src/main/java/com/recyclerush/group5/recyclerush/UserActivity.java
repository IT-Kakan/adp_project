package com.recyclerush.group5.recyclerush;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserActivity extends Activity {
    EditText eText;
    String userLogin;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        eText= findViewById(R.id.editText);

        View userView = this.findViewById(android.R.id.content);

        userView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeLeft () {
                Intent backToMain = new Intent(UserActivity.this, MainActivity.class);
                //backToMain.putExtra("name", "something");
                setResult(RESULT_OK, backToMain);
                //startActivity(backToMain);
                finish();
            }
        });

        loginButton = findViewById(R.id.button3);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String userLogin = eText.getText().toString();

               if(userLogin.length() > 2){
                 //  User user = addMember(userLogin);
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
