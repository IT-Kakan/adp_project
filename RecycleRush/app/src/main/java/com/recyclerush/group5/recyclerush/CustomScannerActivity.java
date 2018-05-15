package com.recyclerush.group5.recyclerush;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.Arrays;
import java.util.Collections;

/**
 * This activity has a margin.
 */
public class CustomScannerActivity extends CaptureActivity {
    private static final int RC_SIGN_IN = 123;
    private static final String TAG = "CustomScannerActivity";
    TextView text1;
    TextView text2;

    @Override
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.activity_custom_barcode_scanner);
        DecoratedBarcodeView barcodeScannerView = findViewById(R.id.zxing_barcode_scanner);
        barcodeScannerView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeRight() {
                if (CurrentUser.getInstance().isLoggedIn()) {
                    Intent startDisplayUser  = new Intent (CustomScannerActivity.this, DisplayUserActivity.class);
                    startActivity(startDisplayUser);
                } else {
                    firebaseLogin();
                }
            }

            public void onSwipeTop (){
                Intent backToMain = new Intent(CustomScannerActivity.this, CategoriesActivity.class);
                startActivity(backToMain);
            }

            public void onSwipeLeft() {
                Intent intent = new Intent(CustomScannerActivity.this, BarcodeReaderActivity.class);
                startActivity(intent);
            }
        });

        Button enterBarcode = findViewById(R.id.button3);
        enterBarcode.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(CustomScannerActivity.this, BarcodeReaderActivity.class);
                startActivity(intent);
            }
        });
        return barcodeScannerView;
    }

    private void firebaseLogin() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Collections.singletonList(
                                new AuthUI.IdpConfig.EmailBuilder().build()))
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent in) {
        super.onActivityResult(requestCode, resultCode, in);

        if(requestCode == 100){
            //if someone logged in, set that user as current
            Intent sendToUserInfo = new Intent(this, MainActivity.class);
            startActivity(sendToUserInfo);
        }

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(in);
            // Successfully signed in
            if (resultCode == RESULT_OK) {
                CurrentUser.getInstance().logIn();
                Intent intent = new Intent(CustomScannerActivity.this, DisplayUserActivity.class);
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

                Log.e(TAG, "Sign-in error: ", response.getError());
            }
        }
    }

}

