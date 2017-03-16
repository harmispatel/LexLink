package utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import lexlink.app.com.lexlink.jsonutil.JSONCommonKeywords;

/**
 * Created by Harmis on 15/03/17.
 */

public class CommonUtils {
    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null && connectivity.getActiveNetworkInfo() != null) {

            NetworkInfo[] info = connectivity.getAllNetworkInfo();

            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }


    public static boolean isSuccessFromServer(JSONObject jsonObject) {
        if (jsonObject.has(JSONCommonKeywords.success)) {
            try {
                int success = jsonObject.getInt(JSONCommonKeywords.success);
                return success == 1;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;

            }

        }
        return false;
    }

    public static boolean isMessageAvailableInResponse(JSONObject jsonObject) {
        if (jsonObject.has(JSONCommonKeywords.Message)) {
            try {
                String message = jsonObject.getString(JSONCommonKeywords.Message);

                return isTextAvailable(message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


    public static boolean isTextAvailable(String text) {
        return !(text == null || text.equals("") || text.equals("null"));


    }

    public static void commonToast(Activity activity, String text) {
        if (CommonUtils.isTextAvailable(text)) {
            Toast.makeText(activity, text, Toast.LENGTH_LONG).show();
        }


    }

    public static void displayMessageInToLog(String prefix, String text) {
        Log.d("Lex_" + prefix, text);


    }

    public static void displayMessageInToLog(String text) {
        Log.d("Lex", text);


    }
}
