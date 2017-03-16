package lexlink.app.com.lexlink.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import lexlink.app.com.lexlink.R;
import lexlink.app.com.lexlink.adapters.LawyerCategoryAdapter;
import lexlink.app.com.lexlink.baseviews.BaseEdittext;
import lexlink.app.com.lexlink.baseviews.BaseTextview;
import lexlink.app.com.lexlink.enums.UserType;
import lexlink.app.com.lexlink.httpmanager.ApiHandler;
import lexlink.app.com.lexlink.interfaces.Webservices;
import lexlink.app.com.lexlink.models.LawyerCategory;
import lexlink.app.com.lexlink.models.LawyerCategoryList;
import lexlink.app.com.lexlink.models.RegisterBase;
import lexlink.app.com.lexlink.models.RegisterRequestBean;
import lexlink.app.com.lexlink.recyle_decorate.RecyclerItemClickListener;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.CommonUtils;

public class UserSignUpActivity extends AppCompatActivity {
    private static final int SELECT_PICTURE_FROM_GALLERY = 1;
    private static final int SELECT_PICTURE_FROM_CAMERA = 2;
    String TAG = "SignUpActivity";
    LinearLayout ll_camera_popup;
    ToggleButton signup_toggle;
    RelativeLayout rootView, rl_salutation, rl_city, rl_lawyer_category;
    BaseEdittext signup_et_first_name, signup_et_middle_name, signup_et_last_name, signup_et_address, signup_et_post_code, signup_et_email, signup_et_password_new;
    Button signup_button;
    ImageView signup_iv_camera, signup_iv_salutation, signup_iv_city, signup_iv_slider_gallery, signup_iv_slider_camera, signup_iv_slider_delete, signup_iv_lawyer_category;
    TextView tv_salutation, tv_city;
    String fName, mName, lName, address, postCode, salutation = "Mr", city = "London,Uk", email, passwordNew, profileImgUrl;
    TextView signup_tv_create_pro;
    BaseTextview tv_lawyer_category;
    int selectedId, selectedIdCity;
    boolean isLawyer = true;
    List<LawyerCategoryList> lawyerCategoryLists = new ArrayList<>();
    LawyerCategoryAdapter lawyerCategoryAdapter;
    LinearLayout linearLayout_gallery = null, linearLayout_camera = null, linearLayout_delete = null;
    CircleImageView circleImageView_profile = null;
    RecyclerView signup_rv_lawyer_category = null;
    String categoryID = null;
    Dialog dialog_lawyer_category = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        hideKeyboard();


        idMapping();
        signup_iv_salutation.setImageResource(R.drawable.dropdown);
        signup_iv_city.setImageResource(R.drawable.dropdown);
        setActions();
        setupLawyerView();

    }

    private void setActions() {
        signup_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fName = signup_et_first_name.getText().toString().trim();
                mName = signup_et_middle_name.getText().toString().trim();
                lName = signup_et_last_name.getText().toString().trim();
                email = signup_et_email.getText().toString().trim();
                passwordNew = signup_et_password_new.getText().toString().trim();
                address = signup_et_address.getText().toString().trim();
                postCode = signup_et_post_code.getText().toString().trim();
                salutation = tv_salutation.getText().toString().trim();

                if (TextUtils.isEmpty(fName))
                {
                    Snackbar snackbar = Snackbar
                            .make(rootView, "Enter First Name", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (TextUtils.isEmpty(mName))
                {
                    Snackbar snackbar = Snackbar
                            .make(rootView, "Enter Middle Name", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (TextUtils.isEmpty(lName))
                {
                    Snackbar snackbar = Snackbar
                            .make(rootView, "Enter Last Name", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (TextUtils.isEmpty(email))
                {
                    Snackbar snackbar = Snackbar
                            .make(rootView, "Enter Email", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else if (!CommonUtils.isEmailValid(email)) {
                    Snackbar snackbar = Snackbar
                            .make(rootView, getResources().getString(R.string.enter_valid_email), Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (TextUtils.isEmpty(passwordNew))
                {
                    Snackbar snackbar = Snackbar
                            .make(rootView, "Enter Password", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (TextUtils.isEmpty(address))
                {
                    Snackbar snackbar = Snackbar
                            .make(rootView, "Enter Address", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (fName.length()<3 || mName.length()<3 || lName.length()<3 || address.length()<3)
                {
                    Snackbar snackbar = Snackbar
                            .make(rootView, "Minimum 3 Characters Allowed", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (!TextUtils.isEmpty(postCode) && postCode.length()<3)
                {

                    Snackbar snackbar = Snackbar
                            .make(rootView, "Minimum 3 Characters Allowed", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else
                {
                    Log.e(TAG, "------------------------Data---------------------------");
                    Log.e(TAG, "fName" + fName);
                    Log.e(TAG, "mName=" + mName);
                    Log.e(TAG, "lName=" + lName);
                    Log.e(TAG, "email=" + email);
                    Log.e(TAG, "passwordNew=" + passwordNew);
                    Log.e(TAG, "address=" + address);
                    Log.e(TAG, "postCode=" + postCode);
                    Log.e(TAG, "profileImgUrl=" + profileImgUrl);
                    Log.e(TAG, "-------------------------------------------------------");

                    MultipartBody.Part body = null;
                    // register
                    if (profileImgUrl != null) {
                        File file = new File(profileImgUrl);
                        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                        body = MultipartBody.Part.createFormData("profile_pic", file.getName(), reqFile);

                    }
                    final ProgressDialog dialog;
                    dialog = new ProgressDialog(UserSignUpActivity.this);
                    dialog.setMessage("Please wait....");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();

                    RegisterRequestBean registerRequestBean = null;

                    if (isLawyer) {
                        registerRequestBean = new RegisterRequestBean(address, salutation, fName, mName, lName, email, "1", postCode, passwordNew, "fIs1hf_q6mc:APA91bHeAKKyq5_olJ9nwE4EMfGsCifas87ohx_B3l3RYUU6BRsrFr7O8VN2e6ubrEnjPqpKvv4-6D__", "1", categoryID);

                        CommonUtils.displayMessageInToLog("Categort ID" + categoryID);
                        Call<RegisterBase> registerCall = ApiHandler.getApiService().lawerregister(registerRequestBean, body);
                        registerCall.enqueue(new Callback<RegisterBase>() {
                            @Override
                            public void onResponse(Call<RegisterBase> registerCall, Response<RegisterBase> response) {

                                try {

                                    Log.w(" Full json gson => ", new Gson().toJson(response));
                                    JSONObject jsonObj = new JSONObject(new Gson().toJson(response).toString());
                                    Log.w(" responce => ", jsonObj.getJSONObject("body").toString());

                                    if (response.isSuccessful()) {


                                        int success = response.body().getSuccess();
                                        if (success == 1) {


                                            if (CommonUtils.isTextAvailable(response.body().getMessage())) {
                                                CommonUtils.commonToast(UserSignUpActivity.this, response.body().getMessage());
                                            }

                                            finish();

                                            Intent i = new Intent(UserSignUpActivity.this, HomeActivity.class);
// set the new task and clear flags
                                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(i);
                                        } else if (success == 0) {

                                            String message = response.message();
                                            if (CommonUtils.isTextAvailable(message)) {
                                                Snackbar snackbar = Snackbar
                                                        .make(rootView, message, Snackbar.LENGTH_LONG);
                                                snackbar.show();
                                            } else {

                                            }

                                        } else {
                                            Snackbar snackbar = Snackbar
                                                    .make(rootView, getResources().getString(R.string.user_sign_up_failed), Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        }
                                    } else {
                                        Snackbar snackbar = Snackbar
                                                .make(rootView, getResources().getString(R.string.user_sign_up_failed), Snackbar.LENGTH_LONG);
                                        snackbar.show();

                                    }

                                    dialog.dismiss();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Snackbar snackbar = Snackbar
                                            .make(rootView, e.getMessage(), Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                    dialog.dismiss();

                                }
                            }

                            @Override
                            public void onFailure(Call<RegisterBase> loginCall, Throwable t) {
                                Log.e("Tag", "error" + t.toString());
                                Snackbar snackbar = Snackbar
                                        .make(rootView, t.getMessage(), Snackbar.LENGTH_LONG);
                                snackbar.show();
                                dialog.dismiss();

                            }


                        });
                    } else {
                        registerRequestBean = new RegisterRequestBean(address, salutation, fName, mName, lName, email, "1", postCode, passwordNew, "fIs1hf_q6mc:APA91bHeAKKyq5_olJ9nwE4EMfGsCifas87ohx_B3l3RYUU6BRsrFr7O8VN2e6ubrEnjPqpKvv4-6D__", "1");
                        Call<RegisterBase> registerCall = ApiHandler.getApiService().register(registerRequestBean, body);
                        registerCall.enqueue(new Callback<RegisterBase>() {
                            @Override
                            public void onResponse(Call<RegisterBase> registerCall, Response<RegisterBase> response) {

                                try {

                                    Log.w(" Full json gson => ", new Gson().toJson(response));
                                    JSONObject jsonObj = new JSONObject(new Gson().toJson(response).toString());
                                    Log.w(" responce => ", jsonObj.getJSONObject("body").toString());

                                    if (response.isSuccessful()) {


                                        int success = response.body().getSuccess();
                                        if (success == 1) {


                                            if (CommonUtils.isTextAvailable(response.body().getMessage())) {
                                                CommonUtils.commonToast(UserSignUpActivity.this, response.body().getMessage());
                                            }

                                            if (response.body().getProfile().getLoginType().equals(UserType.client.name())) {
                                                finish();

                                                Intent i = new Intent(UserSignUpActivity.this, ClientHomeActivity.class);
// set the new task and clear flags
                                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(i);
                                            } else {
                                                finish();

                                                Intent i = new Intent(UserSignUpActivity.this, LayerHomeActivity.class);
// set the new task and clear flags
                                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(i);
                                            }


                                        } else if (success == 0) {

                                            String message = response.message();
                                            if (CommonUtils.isTextAvailable(message)) {
                                                Snackbar snackbar = Snackbar
                                                        .make(rootView, message, Snackbar.LENGTH_LONG);
                                                snackbar.show();
                                            } else {

                                            }

                                        } else {
                                            Snackbar snackbar = Snackbar
                                                    .make(rootView, getResources().getString(R.string.user_sign_up_failed), Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        }
                                    } else {
                                        Snackbar snackbar = Snackbar
                                                .make(rootView, getResources().getString(R.string.user_sign_up_failed), Snackbar.LENGTH_LONG);
                                        snackbar.show();

                                    }

                                    dialog.dismiss();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Snackbar snackbar = Snackbar
                                            .make(rootView, e.getMessage(), Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                    dialog.dismiss();

                                }
                            }

                            @Override
                            public void onFailure(Call<RegisterBase> loginCall, Throwable t) {
                                Log.e("Tag", "error" + t.toString());
                                Snackbar snackbar = Snackbar
                                        .make(rootView, t.getMessage(), Snackbar.LENGTH_LONG);
                                snackbar.show();
                                dialog.dismiss();

                            }


                        });


                    }


                }
            }
        });

        signup_iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCameraPopup();

            }
        });
        rl_salutation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSalutationPopup();
            }
        });
        rl_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // showCityPopup();
            }
        });
        linearLayout_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gallery Intent intent = new Intent();
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECT_PICTURE_FROM_GALLERY);


            }
        });
        linearLayout_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, SELECT_PICTURE_FROM_CAMERA);

            }
        });
        linearLayout_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                circleImageView_profile.setImageResource(R.drawable.lawyer_image);
                profileImgUrl = null;


            }
        });
        rl_lawyer_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLawyerPopup();
            }
        });
    }


    private void idMapping() {
        rootView = (RelativeLayout) findViewById(R.id.activity_sign_up_root);
        ll_camera_popup = (LinearLayout) findViewById(R.id.ll_camera_popup);
        rl_salutation = (RelativeLayout) findViewById(R.id.rl_salutation);
        rl_city = (RelativeLayout) findViewById(R.id.rl_city);
        signup_toggle = (ToggleButton) findViewById(R.id.signup_toggle);
        signup_iv_salutation = (ImageView) findViewById(R.id.signup_iv_salutation);
        signup_iv_city = (ImageView) findViewById(R.id.signup_iv_city);
        signup_iv_camera = (ImageView) findViewById(R.id.signup_iv_camera);
        signup_et_first_name = (BaseEdittext) findViewById(R.id.signup_et_first_name);
        signup_et_middle_name = (BaseEdittext) findViewById(R.id.signup_et_middle_name);
        signup_et_last_name = (BaseEdittext) findViewById(R.id.signup_et_last_name);
        signup_et_address = (BaseEdittext) findViewById(R.id.signup_et_address);
        signup_et_post_code = (BaseEdittext) findViewById(R.id.signup_et_post_code);
        tv_salutation = (TextView) findViewById(R.id.tv_salutation);
        tv_city = (TextView) findViewById(R.id.tv_city);
        signup_button = (Button) findViewById(R.id.signup_button);
        signup_tv_create_pro = (TextView) findViewById(R.id.signup_tv_create_pro);
        signup_et_email = (BaseEdittext) findViewById(R.id.signup_et_email);
        signup_et_password_new = (BaseEdittext) findViewById(R.id.signup_et_password_new);
        signup_iv_slider_gallery = (ImageView) findViewById(R.id.signup_iv_slider_gallery);
        signup_iv_slider_camera = (ImageView) findViewById(R.id.signup_iv_slider_camera);
        signup_iv_slider_delete = (ImageView) findViewById(R.id.signup_iv_slider_delete);
        rl_lawyer_category = (RelativeLayout) findViewById(R.id.rl_lawyer_category);
        tv_lawyer_category = (BaseTextview) findViewById(R.id.tv_lawyer_category);
        signup_iv_lawyer_category = (ImageView) findViewById(R.id.signup_iv_lawyer_category);
        circleImageView_profile = (CircleImageView) findViewById(R.id.profile_image);
        linearLayout_camera = (LinearLayout) findViewById(R.id.layout_camera);
        linearLayout_gallery = (LinearLayout) findViewById(R.id.layout_gallery);
        linearLayout_delete = (LinearLayout) findViewById(R.id.layout_delete);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (signup_iv_salutation != null)
            signup_iv_salutation.setImageResource(R.drawable.dropdown);

        if (signup_iv_city != null)
            signup_iv_city.setImageResource(R.drawable.dropdown);
    }
    private void showCameraPopup() {
        if (ll_camera_popup.getVisibility() == View.GONE) {
            signup_tv_create_pro.setVisibility(View.GONE);
            ll_camera_popup.animate().alpha(1.0f);
            ll_camera_popup.setVisibility(View.VISIBLE);
            ll_camera_popup.animate().translationX(1);


        } else {
            ll_camera_popup.setVisibility(View.GONE);
            ll_camera_popup.animate().translationX(-1);
            signup_tv_create_pro.setVisibility(View.VISIBLE);

        }
//        if (ll_camera_popup.getVisibility() == View.GONE) {
//            ll_camera_popup.animate()
//                    .translationX(ll_camera_popup.getHeight()).alpha(1.0f)
//                    .setListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationStart(Animator animation) {
//                            super.onAnimationStart(animation);
//                            ll_camera_popup.setVisibility(View.VISIBLE);
//                            ll_camera_popup.setAlpha(0.0f);
//                        }
//                    });
//        } else {
//            ll_camera_popup.animate()
//                    .translationX(0).alpha(0.0f)
//                    .setListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            super.onAnimationEnd(animation);
//                            ll_camera_popup.setVisibility(View.GONE);
//                        }
//                    });
//        }
    }
    private void showSalutationPopup() {
        signup_iv_salutation.setImageResource(R.drawable.dropdown2);
        final Dialog dialog = new Dialog(UserSignUpActivity.this);
        dialog.setContentView(R.layout.signup_salutation_spinner);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.LEFT;
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(lp);

        RadioGroup salutation_radiogroup = (RadioGroup) dialog.findViewById(R.id.salutation_radiogroup);
        RadioButton rb_mr = (RadioButton) dialog.findViewById(R.id.rb_mr);
        RadioButton rb_ms = (RadioButton) dialog.findViewById(R.id.rb_ms);
        RadioButton rb_dr = (RadioButton) dialog.findViewById(R.id.rb_dr);
        RadioButton rb_prof = (RadioButton) dialog.findViewById(R.id.rb_prof);

        if (selectedId == R.id.rb_mr) {
            rb_mr.setChecked(true);
        } else if (selectedId == R.id.rb_ms) {
            rb_ms.setChecked(true);
        } else if (selectedId == R.id.rb_dr) {
            rb_dr.setChecked(true);
        } else if (selectedId == R.id.rb_prof) {
            rb_prof.setChecked(true);
        } else {
            rb_mr.setChecked(true);
        }
        salutation_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) dialog.findViewById(selectedId);
                salutation = radioButton.getText().toString();
                tv_salutation.setText(salutation);
                dialog.dismiss();
                signup_iv_salutation.setImageResource(R.drawable.dropdown);

            }
        });

        dialog.show();

    }

    //    private void showCityPopup() {
//        signup_iv_city.setImageResource(R.drawable.dropdown2);
//        final Dialog dialog = new Dialog(SignUpActivity.this);
//        dialog.setContentView(R.layout.signup_city_spinner);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(dialog.getWindow().getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        lp.gravity = Gravity.LEFT;
//        dialog.getWindow().clearFlags(lp.FLAG_DIM_BEHIND);
//        dialog.getWindow().setAttributes(lp);
//
//        RadioGroup city_radiogroup= (RadioGroup) dialog.findViewById(R.id.city_radiogroup);
//        RadioButton rb_1= (RadioButton) dialog.findViewById(R.id.rb_1);
//        RadioButton rb_2= (RadioButton) dialog.findViewById(R.id.rb_2);
//        RadioButton rb_3= (RadioButton) dialog.findViewById(R.id.rb_3);
//        RadioButton rb_4= (RadioButton) dialog.findViewById(R.id.rb_4);
//
//        if (selectedIdCity==R.id.rb_1)
//        {
//            rb_1.setChecked(true);
//        }
//        else if (selectedIdCity==R.id.rb_2)
//        {
//            rb_2.setChecked(true);
//        }
//        else if (selectedIdCity==R.id.rb_3)
//        {
//            rb_3.setChecked(true);
//        }
//        else if (selectedIdCity==R.id.rb_4)
//        {
//            rb_4.setChecked(true);
//        }
//        else
//        {
//            rb_1.setChecked(true);
//        }
//        city_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                selectedIdCity = radioGroup.getCheckedRadioButtonId();
//                RadioButton radioButton= (RadioButton) dialog.findViewById(selectedIdCity);
//                city=radioButton.getText().toString();
//                tv_city.setText(city);
//                dialog.dismiss();
//                signup_iv_city.setImageResource(R.drawable.dropdown);
//
//            }
//        });
//
//        dialog.show();
//
//    }
    private void showLawyerPopup() {
        signup_iv_lawyer_category.setImageResource(R.drawable.dropdown2);
        dialog_lawyer_category = new Dialog(UserSignUpActivity.this);
        dialog_lawyer_category.setContentView(R.layout.signup_city_dialog);
        dialog_lawyer_category.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog_lawyer_category.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.LEFT;
        dialog_lawyer_category.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog_lawyer_category.getWindow().setAttributes(lp);
        signup_rv_lawyer_category = (RecyclerView) dialog_lawyer_category.findViewById(R.id.signup_rv_lawyer_category);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        signup_rv_lawyer_category.setLayoutManager(mLayoutManager);

        signup_rv_lawyer_category.addOnItemTouchListener(
                new RecyclerItemClickListener(UserSignUpActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click

                        List<LawyerCategoryList> lawyerCategoryLists_my = new ArrayList<LawyerCategoryList>();

                        lawyerCategoryAdapter = new LawyerCategoryAdapter(getApplicationContext(), lawyerCategoryLists_my, dialog_lawyer_category);
                        signup_rv_lawyer_category.setAdapter(lawyerCategoryAdapter);

                        LawyerCategoryList lawyerCategoryList = lawyerCategoryLists.get(position);

                        for (int i = 0; i < lawyerCategoryLists.size(); i++) {
                            LawyerCategoryList lawyerCategoryList1_inner = lawyerCategoryLists.get(i);

                            if (lawyerCategoryList.getId().equals(lawyerCategoryList1_inner.getId())) {
                                lawyerCategoryList1_inner.setItemSelected(true);
                                categoryID = lawyerCategoryList1_inner.getId();
                                tv_lawyer_category.setText(lawyerCategoryList1_inner.getCategoryName());
                            } else {
                                lawyerCategoryList1_inner.setItemSelected(false);

                            }
                            lawyerCategoryLists.remove(i);
                            lawyerCategoryLists.add(i, lawyerCategoryList1_inner);

                        }


                        lawyerCategoryAdapter = new LawyerCategoryAdapter(getApplicationContext(), lawyerCategoryLists, dialog_lawyer_category);
                        signup_rv_lawyer_category.setAdapter(lawyerCategoryAdapter);
                        if (dialog_lawyer_category != null && dialog_lawyer_category.isShowing()) {
                            dialog_lawyer_category.dismiss();
                        }
                    }
                })
        );
        // getCityListApi(signup_city_listview,dialog);
        if (lawyerCategoryLists.size() == 0) {
            getLawyerCatwgoryApi(signup_rv_lawyer_category, dialog_lawyer_category);

        } else {
            List<LawyerCategoryList> lawyerCategoryLists_my = new ArrayList<LawyerCategoryList>();

            lawyerCategoryAdapter = new LawyerCategoryAdapter(getApplicationContext(), lawyerCategoryLists_my, dialog_lawyer_category);
            signup_rv_lawyer_category.setAdapter(lawyerCategoryAdapter);
            lawyerCategoryAdapter = new LawyerCategoryAdapter(getApplicationContext(), lawyerCategoryLists, dialog_lawyer_category);
            signup_rv_lawyer_category.setAdapter(lawyerCategoryAdapter);
        }
        dialog_lawyer_category.show();
    }

    private void getLawyerCatwgoryApi(final RecyclerView signup_rv_lawyer_category, final Dialog dialogCat) {
        if (CommonUtils.isConnectingToInternet(UserSignUpActivity.this)) {
            Webservices api = ApiHandler.getApiService();
            Call<LawyerCategory> callLawyerCat = api.getLawyerCategory();
            callLawyerCat.enqueue(new Callback<LawyerCategory>() {
                @Override
                public void onResponse(Call<LawyerCategory> call, Response<LawyerCategory> response) {
                    try {
                        if (response.isSuccessful()) {

                            if (response.body().getSuccess() == 1) {
                                lawyerCategoryLists = response.body().getCategoryList();
                                lawyerCategoryAdapter = new LawyerCategoryAdapter(getApplicationContext(), lawyerCategoryLists, dialogCat);
                                signup_rv_lawyer_category.setAdapter(lawyerCategoryAdapter);

                            }

                        } else {

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Snackbar snackbar = Snackbar
                                .make(rootView, e.getMessage(), Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }

                @Override
                public void onFailure(Call<LawyerCategory> call, Throwable t) {
                    Snackbar snackbar = Snackbar
                            .make(rootView, t.getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                }


            });
        } else {
            Snackbar snackbar = Snackbar
                    .make(rootView, getResources().getString(R.string.no_internet_exist), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (resultCode == RESULT_OK) {
                if (requestCode == SELECT_PICTURE_FROM_GALLERY && resultCode == RESULT_OK && null != data) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    profileImgUrl = cursor.getString(columnIndex);
                    cursor.close();
                    circleImageView_profile.setImageBitmap(BitmapFactory.decodeFile(profileImgUrl));
                } else if (requestCode == SELECT_PICTURE_FROM_CAMERA && resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    circleImageView_profile.setImageBitmap(imageBitmap);
                    Uri selectedImageUri = data.getData();
                    profileImgUrl = getPath(selectedImageUri);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            CommonUtils.displayMessageInToLog(e.getMessage());
        }
    }

    public String getPath(Uri uri) {
        // just some safety built in
        if (uri == null) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();
    }

    public void setupUserView() {
        isLawyer = false;
        rl_lawyer_category.setVisibility(View.GONE);
        //toggle with animation
        signup_toggle = (ToggleButton) findViewById(R.id.login_toggle);


        rootView.setBackgroundResource(R.drawable.sign_ingrey_bg);
        signup_et_first_name.setTextColor(getResources().getColor(R.color.text_color_for_customer));
        signup_et_first_name.setHintTextColor(getResources().getColor(R.color.text_color_for_customer));

        signup_et_middle_name.setTextColor(getResources().getColor(R.color.text_color_for_customer));
        signup_et_middle_name.setHintTextColor(getResources().getColor(R.color.text_color_for_customer));

        signup_et_last_name.setTextColor(getResources().getColor(R.color.text_color_for_customer));
        signup_et_last_name.setHintTextColor(getResources().getColor(R.color.text_color_for_customer));

        signup_et_address.setTextColor(getResources().getColor(R.color.text_color_for_customer));
        signup_et_address.setHintTextColor(getResources().getColor(R.color.text_color_for_customer));

        signup_et_post_code.setTextColor(getResources().getColor(R.color.text_color_for_customer));
        signup_et_post_code.setHintTextColor(getResources().getColor(R.color.text_color_for_customer));

        signup_et_email.setTextColor(getResources().getColor(R.color.text_color_for_customer));
        signup_et_email.setHintTextColor(getResources().getColor(R.color.text_color_for_customer));

        signup_et_password_new.setTextColor(getResources().getColor(R.color.text_color_for_customer));
        signup_et_password_new.setHintTextColor(getResources().getColor(R.color.text_color_for_customer));

        //login_frogot_pass.setTextColor(getResources().getColor(R.color.text_color_for_customer));
        //login_reset_pass.setTextColor(getResources().getColor(R.color.text_color_for_customer));

        signup_button.setBackgroundColor(getResources().getColor(R.color.button_color_for_customer));
        signup_button.setTextColor(getResources().getColor(android.R.color.white));


        tv_lawyer_category.setTextColor(getResources().getColor(R.color.text_color_for_customer));
        tv_city.setTextColor(getResources().getColor(R.color.text_color_for_customer));
        tv_salutation.setTextColor(getResources().getColor(R.color.text_color_for_customer));

        signup_iv_salutation.setImageResource(R.drawable.dropdown);
        signup_iv_lawyer_category.setImageResource(R.drawable.dropdown);

    }

    public void setupLawyerView() {
        isLawyer = true;

        rl_lawyer_category.setVisibility(View.VISIBLE);
        //toggle with animation
        signup_toggle = (ToggleButton) findViewById(R.id.login_toggle);
        rootView.setBackgroundResource(R.drawable.sign_in_blue_bg);
        signup_et_first_name.setTextColor(getResources().getColor(R.color.white));
        signup_et_first_name.setHintTextColor(getResources().getColor(R.color.login_text_color));

        signup_et_middle_name.setTextColor(getResources().getColor(R.color.white));
        signup_et_middle_name.setHintTextColor(getResources().getColor(R.color.login_text_color));

        signup_et_last_name.setTextColor(getResources().getColor(R.color.white));
        signup_et_last_name.setHintTextColor(getResources().getColor(R.color.login_text_color));

        signup_et_address.setTextColor(getResources().getColor(R.color.white));
        signup_et_address.setHintTextColor(getResources().getColor(R.color.login_text_color));

        signup_et_post_code.setTextColor(getResources().getColor(R.color.white));
        signup_et_post_code.setHintTextColor(getResources().getColor(R.color.login_text_color));

        signup_et_email.setTextColor(getResources().getColor(R.color.white));
        signup_et_email.setHintTextColor(getResources().getColor(R.color.login_text_color));

        signup_et_password_new.setTextColor(getResources().getColor(R.color.white));
        signup_et_password_new.setHintTextColor(getResources().getColor(R.color.login_text_color));

        tv_lawyer_category.setTextColor(getResources().getColor(R.color.login_text_color));
        tv_city.setTextColor(getResources().getColor(R.color.login_text_color));
        tv_salutation.setTextColor(getResources().getColor(R.color.login_text_color));


        //login_frogot_pass.setTextColor(getResources().getColor(R.color.text_color_for_customer));
        //login_reset_pass.setTextColor(getResources().getColor(R.color.text_color_for_customer));

        signup_button.setBackgroundColor(getResources().getColor(R.color.button_bg));
        signup_button.setTextColor(getResources().getColor(android.R.color.white));

        signup_iv_salutation.setImageResource(R.drawable.white_down_arrow);
        signup_iv_lawyer_category.setImageResource(R.drawable.white_down_arrow);

    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
