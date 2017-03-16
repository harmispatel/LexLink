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
import android.widget.ToggleButton;

import lexlink.app.com.lexlink.R;
import lexlink.app.com.lexlink.baseviews.BaseEdittext;
import lexlink.app.com.lexlink.baseviews.BaseTextview;
import lexlink.app.com.lexlink.httpmanager.ApiHandler;
import lexlink.app.com.lexlink.models.PostUserData;
import lexlink.app.com.lexlink.models.UserLoggedbean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.CommonUtils;

public class UserLoginActivity extends AppCompatActivity {
    RelativeLayout rootView;
    BaseEdittext login_email, login_password;
    BaseTextview login_frogot_pass, login_reset_pass;
    Button button_sign_in;
    ToggleButton login_toggle;
    String userName, password;
    boolean isLawyer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        idMapping();
        setActions();
    }

    private void setActions() {
        login_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Log.e("tag", "toggle=" + b);
                    setupUserView();
                } else {
                    Log.e("tag", "toggle=" + b);
                    setupLawyerView();
                }
            }
        });
        button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                userName = login_email.getText().toString().trim();
                password = login_password.getText().toString().trim();

                if (TextUtils.isEmpty(userName)) {
                    Snackbar snackbar = Snackbar
                            .make(rootView, "Enter Email", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else if (TextUtils.isEmpty(password)) {
                    Snackbar snackbar = Snackbar
                            .make(rootView, "Enter Password", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    if (CommonUtils.isConnectingToInternet(UserLoginActivity.this)) {

                        PostUserData linkUserbean = new PostUserData(userName, password, "0", "client");

                        final ProgressDialog dialog;
                        dialog = new ProgressDialog(UserLoginActivity.this);
                        dialog.setMessage("please wait....");
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();


                        Call<UserLoggedbean> call = ApiHandler.getApiService().signInUser(linkUserbean);


                        call.enqueue(new Callback<UserLoggedbean>() {
                            @Override
                            public void onResponse(Call<UserLoggedbean> call, Response<UserLoggedbean> response) {
                                try {
                                    if (response.isSuccessful()) {

                                        if (response.body().getSuccess() == 1) {


                                        }

                                    } else {

                                    }
                                    dialog.dismiss();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Snackbar snackbar = Snackbar
                                            .make(rootView, e.getMessage(), Snackbar.LENGTH_LONG);
                                    snackbar.show();

                                }
                            }

                            @Override
                            public void onFailure(Call<UserLoggedbean> call, Throwable t) {
                                Snackbar snackbar = Snackbar
                                        .make(rootView, t.getMessage(), Snackbar.LENGTH_LONG);
                                snackbar.show();
                                dialog.dismiss();
                            }
                        });


                    } else {
                        Snackbar snackbar = Snackbar
                                .make(rootView, getResources().getString(R.string.no_internet_exist), Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }


            }
        });

    }

    private void idMapping() {
        rootView = (RelativeLayout) findViewById(R.id.activity_login_root);

        login_email = (BaseEdittext) findViewById(R.id.login_email);
        login_password = (BaseEdittext) findViewById(R.id.login_password);
        login_frogot_pass = (BaseTextview) findViewById(R.id.login_frogot_pass);
        login_reset_pass = (BaseTextview) findViewById(R.id.login_reset_pass);
        button_sign_in = (Button) findViewById(R.id.button_sign_in);

        //toggle with animation
        login_toggle = (ToggleButton) findViewById(R.id.login_toggle);

    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void setupUserView() {
        isLawyer = false;

        //toggle with animation
        login_toggle = (ToggleButton) findViewById(R.id.login_toggle);


        rootView.setBackgroundResource(R.drawable.user_bg);
        login_email.setTextColor(getResources().getColor(R.color.text_color_for_customer));
        login_email.setHintTextColor(getResources().getColor(R.color.text_color_for_customer));

        login_password.setTextColor(getResources().getColor(R.color.text_color_for_customer));
        login_password.setHintTextColor(getResources().getColor(R.color.text_color_for_customer));


        login_frogot_pass.setTextColor(getResources().getColor(R.color.text_color_for_customer));


        login_reset_pass.setTextColor(getResources().getColor(R.color.text_color_for_customer));


        button_sign_in.setBackgroundColor(getResources().getColor(R.color.button_color_for_customer));
        button_sign_in.setTextColor(getResources().getColor(android.R.color.white));
    }

    public void setupLawyerView() {
        isLawyer = true;


        //toggle with animation
        login_toggle = (ToggleButton) findViewById(R.id.login_toggle);


        rootView.setBackgroundResource(R.drawable.sign_in_blue_bg);
        login_email.setTextColor(getResources().getColor(R.color.white));
        login_email.setHintTextColor(getResources().getColor(R.color.login_text_color));

        login_password.setTextColor(getResources().getColor(R.color.white));
        login_password.setHintTextColor(getResources().getColor(R.color.login_text_color));


        login_frogot_pass.setTextColor(getResources().getColor(R.color.login_text_color));


        login_reset_pass.setTextColor(getResources().getColor(R.color.login_text_color));


        button_sign_in.setBackgroundColor(getResources().getColor(R.color.button_bg));
        button_sign_in.setTextColor(getResources().getColor(R.color.white));


    }

}
