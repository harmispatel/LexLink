package lexlink.app.com.lexlink.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import org.json.JSONException;
import org.json.JSONObject;

import lexlink.app.com.lexlink.R;
import lexlink.app.com.lexlink.baseviews.BaseEdittext;
import lexlink.app.com.lexlink.beans.LinkUserbean;
import lexlink.app.com.lexlink.httpmanager.ApiHandler;
import lexlink.app.com.lexlink.jsonutil.JSONCommonKeywords;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.CommonUtils;

public class SignUpActivity extends AppCompatActivity {
    RelativeLayout rootView;
    BaseEdittext signup_et_first_name, signup_et_middle_name, signup_et_last_name, signup_et_address, signup_et_post_code, signup_et_password, signup_et_email;
    Button signup_button;
    String fName, mName, lName, address, postCode, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        idMapping();
        setActions();
    }

    private void setActions() {

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fName = signup_et_first_name.getText().toString().trim();
                mName = signup_et_middle_name.getText().toString().trim();
                lName = signup_et_last_name.getText().toString().trim();
                address = signup_et_address.getText().toString().trim();
                postCode = signup_et_post_code.getText().toString().trim();
                password = signup_et_password.getText().toString();
                if (TextUtils.isEmpty(fName)) {
                    Snackbar snackbar = Snackbar
                            .make(rootView, "Enter First Name", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else if (TextUtils.isEmpty(mName)) {
                    Snackbar snackbar = Snackbar
                            .make(rootView, "Enter Middle Name", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else if (TextUtils.isEmpty(lName)) {
                    Snackbar snackbar = Snackbar
                            .make(rootView, "Enter Last Name", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else if (TextUtils.isEmpty(address)) {
                    Snackbar snackbar = Snackbar
                            .make(rootView, "Enter Address", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else if (fName.length() < 3 || mName.length() < 3 || lName.length() < 3 || address.length() < 3) {
                    Snackbar snackbar = Snackbar
                            .make(rootView, "Minimum 3 Characters Allowed", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else if (!TextUtils.isEmpty(postCode) && postCode.length() < 3) {

                    Snackbar snackbar = Snackbar
                            .make(rootView, "Minimum 3 Characters Allowed", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    //all data complete


                    if (CommonUtils.isConnectingToInternet(SignUpActivity.this)) {

                        //   File file = new File(imageUri.getPath());
                        //   RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), file);


                        final ProgressDialog dialog;
                        dialog = new ProgressDialog(SignUpActivity.this);
                        dialog.setMessage("please wait....");
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();

                       /* ApiHandler.getApiService().signUpUser("application/json", createSignUpGsonJsonMap(), new Callback<LinkUserbean>() {
                            @Override
                            public void success(LinkUserbean userbean, Response response) {

                                dialog.dismiss();
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void failure(RetrofitError error) {

                                dialog.dismiss();
                            }
                        });*/


                    } else {
                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void idMapping() {
        rootView = (RelativeLayout) findViewById(R.id.activity_sign_up_root);
        signup_et_first_name = (BaseEdittext) findViewById(R.id.signup_et_first_name);
        signup_et_middle_name = (BaseEdittext) findViewById(R.id.signup_et_middle_name);
        signup_et_last_name = (BaseEdittext) findViewById(R.id.signup_et_last_name);
        signup_et_address = (BaseEdittext) findViewById(R.id.signup_et_address);
        signup_et_post_code = (BaseEdittext) findViewById(R.id.signup_et_post_code);

        signup_et_password = (BaseEdittext) findViewById(R.id.signup_et_password_new);
        signup_et_email = (BaseEdittext) findViewById(R.id.signup_et_email);

        signup_button = (Button) findViewById(R.id.signup_button);


    }


    private JsonObject createSignUpGsonJsonMap() {

        JsonObject gsonObject = new JsonObject();


        try {

            JSONObject jsonObj_forgotpassword = new JSONObject();
            jsonObj_forgotpassword.put(JSONCommonKeywords.Salutation, "Mr.");
            jsonObj_forgotpassword.put(JSONCommonKeywords.FirstName, signup_et_first_name.getText().toString());
            jsonObj_forgotpassword.put(JSONCommonKeywords.LastName, signup_et_last_name.getText().toString());
            jsonObj_forgotpassword.put(JSONCommonKeywords.MiddleName, signup_et_middle_name.getText().toString());
            jsonObj_forgotpassword.put(JSONCommonKeywords.Email, signup_et_email.getText().toString());

            jsonObj_forgotpassword.put(JSONCommonKeywords.City, "Ahmedabad");
            jsonObj_forgotpassword.put(JSONCommonKeywords.PostalCode, "23456");


            jsonObj_forgotpassword.put(JSONCommonKeywords.PassWord, password);
            jsonObj_forgotpassword.put(JSONCommonKeywords.DeviceToken, "");
            jsonObj_forgotpassword.put(JSONCommonKeywords.DeviceAccess, "");


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("json_content", jsonObj_forgotpassword);

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObject.toString());
            Log.e("MY gson.JSON:  ", "AS PARAMETER  " + gsonObject);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return gsonObject;
    }


    public void register() {

        try {
            // create upload service client

            // add another part within the multipart request
            // RequestBody reqFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
            RequestBody email = RequestBody.create(MediaType.parse("text/plain"), "upload_test4@gmail.com");

            Call<LinkUserbean> call = ApiHandler.getApiService().updateProfilePhotoProcess(email, null);
            call.enqueue(new Callback<LinkUserbean>() {
                @Override
                public void onResponse(Call<LinkUserbean> call, Response<LinkUserbean> response) {
                    String TAG = response.body().toString();

                    LinkUserbean userModelResponse = response.body();

                    Log.d("MainActivity", "user image = " + response.message());

                }

                @Override
                public void onFailure(Call<LinkUserbean> call, Throwable t) {

                }


            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
