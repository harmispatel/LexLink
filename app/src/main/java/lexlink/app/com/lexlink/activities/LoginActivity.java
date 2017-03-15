package lexlink.app.com.lexlink.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import lexlink.app.com.lexlink.R;
import lexlink.app.com.lexlink.baseviews.BaseEdittext;
import lexlink.app.com.lexlink.baseviews.BaseTextview;
import lexlink.app.com.lexlink.beans.LinkUserbean;
import lexlink.app.com.lexlink.httpmanager.ApiHandler;
import lexlink.app.com.lexlink.jsonutil.JSONCommonKeywords;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.CommonUtils;

public class LoginActivity extends AppCompatActivity {
    RelativeLayout rootView;
    BaseEdittext login_email,login_password;
    BaseTextview login_frogot_pass,login_reset_pass;
    Button button_sign_in;
    ToggleButton login_toggle;
    String userName,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idMapping();
        setActions();
    }

    private void setActions() {
        login_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    Log.e("tag","toggle="+b);
                }
                else
                {
                    Log.e("tag","toggle="+b);
                }
            }
        });
        button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                userName=login_email.getText().toString().trim();
                password=login_password.getText().toString().trim();

                if (TextUtils.isEmpty(userName))
                {
                    Snackbar snackbar = Snackbar
                            .make(rootView, "Enter Email", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }


                else if (TextUtils.isEmpty(password))
                {
                    Snackbar snackbar = Snackbar
                            .make(rootView, "Enter Password", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else
                {

                }


                if (CommonUtils.isConnectingToInternet(LoginActivity.this)) {

                    final ProgressDialog dialog;
                    dialog = new ProgressDialog(LoginActivity.this);
                    dialog.setMessage("please wait....");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();

                    Call<LinkUserbean> linkUserbeanCall = ApiHandler.getApiService().signInUser("application/json", createSignUpGsonJsonMap());

                    linkUserbeanCall.enqueue(new Callback<LinkUserbean>() {
                        @Override
                        public void onResponse(Call<LinkUserbean> call, Response<LinkUserbean> response) {


                            if (response.isSuccessful()) {

                                LinkUserbean linkUserbean = response.body();
                                if (linkUserbean != null) {
                                    Log.d("ID", linkUserbean.getUserId());
                                }
                                Log.d("MainActivity", "user image = " + response.message());
                            } else {

                                Log.d("MainActivity", "user image = " + response.message());

                            }


                            LinkUserbean userModelResponse = response.body();


                        }

                        @Override
                        public void onFailure(Call<LinkUserbean> call, Throwable t) {

                        }


                    });


                } else {
                    Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void idMapping() {
        rootView= (RelativeLayout) findViewById(R.id.activity_login_root);

        login_email= (BaseEdittext) findViewById(R.id.login_email);
        login_password= (BaseEdittext) findViewById(R.id.login_password);
        login_frogot_pass= (BaseTextview) findViewById(R.id.login_frogot_pass);
        login_reset_pass= (BaseTextview) findViewById(R.id.login_reset_pass);
        button_sign_in= (Button) findViewById(R.id.button_sign_in);

        //toggle with animation
        login_toggle= (ToggleButton) findViewById(R.id.login_toggle);

    }
    public  void hideKeyboard()
    {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private JsonObject createSignUpGsonJsonMap() {

        JsonObject gsonObject = new JsonObject();


        try {

            JSONObject jsonObj_forgotpassword = new JSONObject();
            jsonObj_forgotpassword.put(JSONCommonKeywords.UserName, userName);
            jsonObj_forgotpassword.put(JSONCommonKeywords.PassWord, password);
            jsonObj_forgotpassword.put(JSONCommonKeywords.isSocial, "0");
            jsonObj_forgotpassword.put(JSONCommonKeywords.LoginType, "client");
            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_forgotpassword.toString());
            Log.e("MY gson.JSON:  ", "AS PARAMETER  " + gsonObject);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return gsonObject;
    }

}
