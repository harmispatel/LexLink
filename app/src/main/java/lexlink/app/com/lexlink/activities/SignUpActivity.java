package lexlink.app.com.lexlink.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import lexlink.app.com.lexlink.R;
import lexlink.app.com.lexlink.baseviews.BaseEdittext;

public class SignUpActivity extends AppCompatActivity {
    String TAG="SignUpActivity";
    LinearLayout ll_camera_popup;
    RelativeLayout rootView,rl_salutation,rl_city;
    BaseEdittext signup_et_first_name,signup_et_middle_name,signup_et_last_name,signup_et_address,signup_et_post_code,signup_et_email,signup_et_password_new;
    Button signup_button;
    ImageView signup_iv_camera,signup_iv_salutation,signup_iv_city,signup_iv_slider_gallery,signup_iv_slider_camera,signup_iv_slider_delete;
    TextView tv_salutation,tv_city;
    String fName,mName,lName,address,postCode,salutation="Mr",city="London,Uk",email,passwordNew,profileImgUrl;
    TextView signup_tv_create_pro;
    int selectedId,selectedIdCity;

    private static final int SELECT_PICTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        hideKeyboard();


        idMapping();
        signup_iv_salutation.setImageResource(R.drawable.dropdown);
        signup_iv_city.setImageResource(R.drawable.dropdown);
        setActions();

    }

    private void setActions() {

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fName=signup_et_first_name.getText().toString().trim();
                mName=signup_et_middle_name.getText().toString().trim();
                lName=signup_et_last_name.getText().toString().trim();
                email=signup_et_email.getText().toString().trim();
                passwordNew=signup_et_password_new.getText().toString().trim();
                address=signup_et_address.getText().toString().trim();
                postCode=signup_et_post_code.getText().toString().trim();
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
                    Log.e(TAG,"------------------------Data---------------------------");
                    Log.e(TAG,"fName"+fName);
                    Log.e(TAG,"mName="+mName);
                    Log.e(TAG,"lName="+lName);
                    Log.e(TAG,"email="+email);
                    Log.e(TAG,"passwordNew="+passwordNew);
                    Log.e(TAG,"address="+address);
                    Log.e(TAG,"postCode="+postCode);
                    Log.e(TAG,"profileImgUrl="+profileImgUrl);
                    Log.e(TAG,"-------------------------------------------------------");

                    //all data complete
                    Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
                    startActivity(intent);
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
                showCityPopup();
            }
        });
        signup_iv_slider_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gallery Intent intent = new Intent();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);


            }
        });
        signup_iv_slider_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        signup_iv_slider_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void idMapping() {
        rootView= (RelativeLayout) findViewById(R.id.activity_sign_up_root);
        ll_camera_popup= (LinearLayout) findViewById(R.id.ll_camera_popup);
        rl_salutation= (RelativeLayout) findViewById(R.id.rl_salutation);
        rl_city= (RelativeLayout) findViewById(R.id.rl_city);
        signup_iv_salutation= (ImageView) findViewById(R.id.signup_iv_salutation);
        signup_iv_city= (ImageView) findViewById(R.id.signup_iv_city);
        signup_iv_camera= (ImageView) findViewById(R.id.signup_iv_camera);
        signup_et_first_name= (BaseEdittext) findViewById(R.id.signup_et_first_name);
        signup_et_middle_name= (BaseEdittext) findViewById(R.id.signup_et_middle_name);
        signup_et_last_name= (BaseEdittext) findViewById(R.id.signup_et_last_name);
        signup_et_address= (BaseEdittext) findViewById(R.id.signup_et_address);
        signup_et_post_code= (BaseEdittext) findViewById(R.id.signup_et_post_code);
        tv_salutation= (TextView) findViewById(R.id.tv_salutation);
        tv_city= (TextView) findViewById(R.id.tv_city);
        signup_button= (Button) findViewById(R.id.signup_button);
        signup_tv_create_pro= (TextView) findViewById(R.id.signup_tv_create_pro);
        signup_et_email= (BaseEdittext) findViewById(R.id.signup_et_email);
        signup_et_password_new= (BaseEdittext) findViewById(R.id.signup_et_password_new);
        signup_iv_slider_gallery= (ImageView) findViewById(R.id.signup_iv_slider_gallery);
        signup_iv_slider_camera= (ImageView) findViewById(R.id.signup_iv_slider_camera);
        signup_iv_slider_delete= (ImageView) findViewById(R.id.signup_iv_slider_delete);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (signup_iv_salutation !=null)
            signup_iv_salutation.setImageResource(R.drawable.dropdown);

        if (signup_iv_city !=null)
            signup_iv_city.setImageResource(R.drawable.dropdown);
    }
    private void showCameraPopup()
    {
        if(ll_camera_popup.getVisibility() == View.GONE)
        {   signup_tv_create_pro.setVisibility(View.GONE);
            ll_camera_popup.animate().alpha(1.0f);
            ll_camera_popup.setVisibility(View.VISIBLE);
            ll_camera_popup.animate().translationX(1);


        }
        else
        {
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
        final Dialog dialog = new Dialog(SignUpActivity.this);
        dialog.setContentView(R.layout.signup_salutation_spinner);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.LEFT;
        dialog.getWindow().clearFlags(lp.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(lp);

        RadioGroup salutation_radiogroup= (RadioGroup) dialog.findViewById(R.id.salutation_radiogroup);
        RadioButton rb_mr= (RadioButton) dialog.findViewById(R.id.rb_mr);
        RadioButton rb_ms= (RadioButton) dialog.findViewById(R.id.rb_ms);
        RadioButton rb_dr= (RadioButton) dialog.findViewById(R.id.rb_dr);
        RadioButton rb_prof= (RadioButton) dialog.findViewById(R.id.rb_prof);

        if (selectedId==R.id.rb_mr)
        {
            rb_mr.setChecked(true);
        }
        else if (selectedId==R.id.rb_ms)
        {
            rb_ms.setChecked(true);
        }
        else if (selectedId==R.id.rb_dr)
        {
            rb_dr.setChecked(true);
        }
        else if (selectedId==R.id.rb_prof)
        {
            rb_prof.setChecked(true);
        }
        else
        {
            rb_mr.setChecked(true);
        }
        salutation_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton= (RadioButton) dialog.findViewById(selectedId);
                salutation=radioButton.getText().toString();
                tv_salutation.setText(salutation);
                dialog.dismiss();
                signup_iv_salutation.setImageResource(R.drawable.dropdown);

            }
        });

        dialog.show();

    }
    private void showCityPopup() {
        signup_iv_city.setImageResource(R.drawable.dropdown2);
        final Dialog dialog = new Dialog(SignUpActivity.this);
        dialog.setContentView(R.layout.signup_city_spinner);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.LEFT;
        dialog.getWindow().clearFlags(lp.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(lp);

        RadioGroup city_radiogroup= (RadioGroup) dialog.findViewById(R.id.city_radiogroup);
        RadioButton rb_1= (RadioButton) dialog.findViewById(R.id.rb_1);
        RadioButton rb_2= (RadioButton) dialog.findViewById(R.id.rb_2);
        RadioButton rb_3= (RadioButton) dialog.findViewById(R.id.rb_3);
        RadioButton rb_4= (RadioButton) dialog.findViewById(R.id.rb_4);

        if (selectedIdCity==R.id.rb_1)
        {
            rb_1.setChecked(true);
        }
        else if (selectedIdCity==R.id.rb_2)
        {
            rb_2.setChecked(true);
        }
        else if (selectedIdCity==R.id.rb_3)
        {
            rb_3.setChecked(true);
        }
        else if (selectedIdCity==R.id.rb_4)
        {
            rb_4.setChecked(true);
        }
        else
        {
            rb_1.setChecked(true);
        }
        city_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedIdCity = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton= (RadioButton) dialog.findViewById(selectedIdCity);
                city=radioButton.getText().toString();
                tv_city.setText(city);
                dialog.dismiss();
                signup_iv_city.setImageResource(R.drawable.dropdown);

            }
        });

        dialog.show();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                profileImgUrl = getPath(selectedImageUri);
            }
        }
    }
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
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


    public  void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
