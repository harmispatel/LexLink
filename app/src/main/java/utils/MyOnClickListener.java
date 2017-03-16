package utils;

import android.content.Context;
import android.view.View;

import lexlink.app.com.lexlink.R;


/**
 * Created by chinkal on 1/20/17.
 */

public abstract class MyOnClickListener implements View.OnClickListener {
    Context context = null;

    public MyOnClickListener(Context context) {
        this.context = context;
    }

    public boolean isAvailableInternet() {
        ConnectionDetector connectionDetector = new ConnectionDetector(context);
        return connectionDetector.isConnectingToInternet();

    }


    public void showInternetNotfoundMessage() {
        CommonUtils.commonToast(context, this.context.getResources().getString(R.string.no_internet_exist));
    }


}
