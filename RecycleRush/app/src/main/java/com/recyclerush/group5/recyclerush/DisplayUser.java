package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.w3c.dom.Text;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.content.pm.PackageManager.GET_SIGNATURES;

public class DisplayUser extends AppCompatActivity {


    TextView pointsText;
    TextView usernameText;
    ImageView image;
    Button shareinfo;
    CallbackManager callbackmanager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  FacebookSdk.sdkInitialize(this.getApplicationContext());

        setContentView(R.layout.activity_display_user);

        shareinfo = (Button)findViewById(R.id.Sharebutton);

        /*
        //init fb
        callbackmanager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        shareinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareLinkContent content = new ShareLinkContent.Builder()
                        .setQuote("Try this awesome app out!!!")
                        .setContentUrl(Uri.parse("https://github.com/IT-Kakan/adp_project"))
                        .build();

                if(ShareDialog.canShow(ShareLinkContent.class)){
                    shareDialog.show(content);
                }
            }
        });
        */

        View userView = this.findViewById(android.R.id.content);

        printKeyHash();


        String username = getIntent().getStringExtra("username");
        Integer points = getIntent().getIntExtra("points", -1);

        pointsText = findViewById(R.id.points_profile);
        usernameText = findViewById(R.id.username_profile);

        usernameText.setText(username);

        image = findViewById(R.id.imageView2);

        if (points < 100) {
            pointsText.setText(points.toString() + "/100");
            image.setImageResource(R.drawable.first_stage);
        } else if (points < 200) {
            pointsText.setText(points.toString() + "/200");
            image.setImageResource(R.drawable.second_stage);
        } else if (points < 300) {
            pointsText.setText(points.toString() + "/300");
            image.setImageResource(R.drawable.third_stage);
        } else if (points < 400) {
            pointsText.setText(points.toString() + "/400");
            image.setImageResource(R.drawable.fourth_stage);
        } else {
            pointsText.setText(points.toString());
            image.setImageResource(R.drawable.full_oak);
        }

        userView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeLeft() {
                Intent backToMain = new Intent(DisplayUser.this, MainActivity.class);
                //backToMain.putExtra("name", "something");
                setResult(RESULT_OK, backToMain);
                //startActivity(backToMain);
                finish();
            }
        });

    }

    private void printKeyHash() {

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.recyclerush.group5.recyclerush", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
