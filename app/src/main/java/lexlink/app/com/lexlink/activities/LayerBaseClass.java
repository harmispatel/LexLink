package lexlink.app.com.lexlink.activities;

import android.content.res.Resources;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import lexlink.app.com.lexlink.R;


/**
 * Created by harmis on 13/12/16.
 */

public abstract class LayerBaseClass extends AppCompatActivity {


    LinearLayout linearMenu;
    TextView txtHeader;

    LinearLayout linearBack;
    TextView txtHeaderWithBack;


    void init() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setHeader(String from) {

        try {
            Toolbar toolbar;
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setPadding(0, 0, 0, 0);//for tab otherwise give space in tab
            toolbar.setContentInsetsAbsolute(0, 0);
            setSupportActionBar(toolbar);

            txtHeader = (TextView) findViewById(R.id.title);
            linearMenu = (LinearLayout) findViewById(R.id.header_menu_ll);

            linearMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.openDrawer(GravityCompat.START);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }


    }


    void setHeaderWithback(String from) {

        try {
            Toolbar toolbar;
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setPadding(0, 0, 0, 0);//for tab otherwise give space in tab
            toolbar.setContentInsetsAbsolute(0, 0);
            setSupportActionBar(toolbar);

            txtHeaderWithBack = (TextView) findViewById(R.id.txtTitleBack);
            linearBack = (LinearLayout) findViewById(R.id.header_back);

            linearBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }


    }


}
