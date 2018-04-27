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


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.recyclerush.group5.recyclerush.MainActivity;
import com.recyclerush.group5.recyclerush.userClass;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UserActivity extends Activity {
    private static final int RC_SIGN_IN = 123;
    private static final String TAG = "UserActivity";
    EditText eText;
    String userLogin;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        firebaseLogin();
        //TODO remove
        /*eText= findViewById(R.id.editText);

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

        });*/
    }

    private void firebaseLogin() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.EmailBuilder().build()))
                        .build(),
                RC_SIGN_IN);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                //TODO display user here
                startActivity(new Intent(this, DisplayUser.class));
                finish();
            } else {
                // Sign in failed
                if (response == null) {
                    Log.i(TAG, "No response");
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Log.i(TAG, "No network connection: " + response.getError());
                    return;
                }

                //TODO handle
                Log.e(TAG, "Sign-in error: ", response.getError());
            }
        }
    }
}
