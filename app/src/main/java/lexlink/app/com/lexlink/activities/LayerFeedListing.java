package lexlink.app.com.lexlink.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import lexlink.app.com.lexlink.R;

/**
 * Created by harmis on 16/3/17.
 */

public class LayerFeedListing extends LayerBaseClass {

    public static FragmentActivity mActivity;


    ImageView imgListing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layer_feed_listing_base);

        try {
            mActivity = this;
            init();
            setHeader("");
            setNavigationView();
            setHome();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setHome() {

        imgListing = (ImageView) findViewById(R.id.img1);
        imgListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent in = new Intent(LayerFeedListing.this, LayerFeedDetailsActivity.class);
                    startActivity(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    private void setNavigationView() {

        try {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity = null;

        Log.e("Call", "Destroy");
    }


}
