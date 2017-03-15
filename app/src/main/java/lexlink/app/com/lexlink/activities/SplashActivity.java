package lexlink.app.com.lexlink.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import lexlink.app.com.lexlink.R;

public class SplashActivity extends AppCompatActivity {
    public static boolean isNeedToDisplayLog = true;
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //methods
            }
        });

/* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                finish();


                Intent intent = new Intent(SplashActivity.this, SignUpActivity.class);
                    startActivity(intent);



            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
