package com.recyclerush.group5.recyclerush;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Arrays;

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
                CurrentUser.getInstance().logIn();
                Intent intent = new Intent(UserActivity.this, DisplayUser.class);
                intent.putExtra("user", FirebaseAuth.getInstance().getCurrentUser());
                startActivity(intent);
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
