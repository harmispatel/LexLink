package lexlink.app.com.lexlink.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import lexlink.app.com.lexlink.R;

/**
 * Created by harmis on 16/3/17.
 */

public class LayerFeedDetailsActivity extends LayerBaseClass {

    public static FragmentActivity mActivity;
    // Header
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layer_feed_details);

        try {
            mActivity = this;
            init();
            setHeaderWithback("");

            setHome();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setHome() {


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity = null;

        Log.e("Call", "Destroy");
    }


}