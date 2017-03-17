package utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class CommonSession {

    private static final String COMPARESESSION_PREFENCE_NAME = "lexlink_my_common_session";
    private static final String LOGGED_USER_USERID = "lexlink_userID";

    private static final String USER_TYPE = "user_type_lex_link";


    private SharedPreferences sharedPref;
    private Editor editor;


    // constructor

    @SuppressLint("CommitPrefEdits")
    public CommonSession(Context activity) {
        sharedPref = activity.getSharedPreferences(
                COMPARESESSION_PREFENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();

    }

    @SuppressLint("CommitPrefEdits")
    public CommonSession(Activity mfragmentactivity) {
        sharedPref = mfragmentactivity.getSharedPreferences(
                COMPARESESSION_PREFENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();

    }


    // device width or height


    // user type
    public String getUserType() {
        return sharedPref.getString(LOGGED_USER_USERID, null);
    }

    public void storeUserType(String user_email) {
        try {
            editor.putString(LOGGED_USER_USERID, user_email);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetUserTypeD() {
        try {
            editor.putString(LOGGED_USER_USERID, null);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // login user email
    public String getLoggedUserID() {
        return sharedPref.getString(LOGGED_USER_USERID, null);
    }

    public void storeLoggedUserID(String user_email) {
        try {
            editor.putString(LOGGED_USER_USERID, user_email);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetLoggedUserID() {
        try {
            editor.putString(LOGGED_USER_USERID, null);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
