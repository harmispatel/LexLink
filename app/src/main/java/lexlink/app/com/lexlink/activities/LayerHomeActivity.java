package lexlink.app.com.lexlink.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import lexlink.app.com.lexlink.R;

public class LayerHomeActivity extends AppCompatActivity {


    CircleImageView userProfileimage;
    TextView txtUserName;

    LinearLayout linearHome, linearFeed, linearOngoing, linearPastFed, linearNotification, linearMyProfile;
    TextView txtHome, txtFeed, txtOngoing, txtPastFed, txtNotification, txtMyProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layer_home_screen);


        try {
            initViews();
            setEvent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEvent() {

        linearHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        linearFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent in = new Intent(LayerHomeActivity.this, LayerFeedListing.class);
                    startActivity(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        linearOngoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    Intent in = new Intent(LayerHomeActivity.this, LayerFeedListing.class);
                    startActivity(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        linearPastFed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent in = new Intent(LayerHomeActivity.this, LayerFeedListing.class);
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
                    Intent in = new Intent(LayerHomeActivity.this, LawyerNotificationActivity.class);
                    startActivity(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        linearMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent in = new Intent(LayerHomeActivity.this, LawyerMyProfileActivity.class);
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

            linearHome = (LinearLayout) findViewById(R.id.home_ll);
            linearFeed = (LinearLayout) findViewById(R.id.feed_ll);
            linearOngoing = (LinearLayout) findViewById(R.id.ongoing_ll);
            linearPastFed = (LinearLayout) findViewById(R.id.pastClaim_ll);
            linearNotification = (LinearLayout) findViewById(R.id.notification_ll);
            linearMyProfile = (LinearLayout) findViewById(R.id.MyProfile_ll);

            txtHome = (TextView) findViewById(R.id.txtMenuHome);
            txtFeed = (TextView) findViewById(R.id.txtMenuFeed);
            txtOngoing = (TextView) findViewById(R.id.txtMenuOngoing);
            txtPastFed = (TextView) findViewById(R.id.txtMenuPastClaim);
            txtNotification = (TextView) findViewById(R.id.txtMenuNotification);
            txtMyProfile = (TextView) findViewById(R.id.txtMenuMyProfile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
