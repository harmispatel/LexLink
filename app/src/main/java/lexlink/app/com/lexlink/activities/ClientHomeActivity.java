package lexlink.app.com.lexlink.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import lexlink.app.com.lexlink.R;

/**
 * Created by harmis on 16/3/17.
 */

public class ClientHomeActivity extends AppCompatActivity {


    CircleImageView userProfileimage;
    TextView txtUserName;

    LinearLayout linearMyProfile, linearMyClaim, linearNotification, linearSearchLawyer, linearCreateCase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_home_screen);


        try {
            initViews();
            setEvent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEvent() {


        linearMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent in = new Intent(ClientHomeActivity.this, ClientMyProfileActivity.class);
                    startActivity(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        linearMyClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    Intent in = new Intent(ClientHomeActivity.this, ClientMyClaimActivity.class);
                    startActivity(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        linearNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent in = new Intent(ClientHomeActivity.this, ClientNotificationActivity.class);
                    startActivity(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        linearSearchLawyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent in = new Intent(ClientHomeActivity.this, ClientSearchLawyerActivity.class);
                    startActivity(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        linearCreateCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent in = new Intent(ClientHomeActivity.this, ClientCreateCaseActivity.class);
                    startActivity(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }

    private void initViews() {

        try {
            userProfileimage = (CircleImageView) findViewById(R.id.home_profile_image);
            txtUserName = (TextView) findViewById(R.id.home_userName);

            linearMyProfile = (LinearLayout) findViewById(R.id.myProfile_ll);
            linearMyClaim = (LinearLayout) findViewById(R.id.MyClaim_ll);
            linearNotification = (LinearLayout) findViewById(R.id.notification_ll);
            linearSearchLawyer = (LinearLayout) findViewById(R.id.searchLawyer_ll);
            linearCreateCase = (LinearLayout) findViewById(R.id.createcase_ll);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}