package lexlink.app.com.lexlink.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import lexlink.app.com.lexlink.R;
import lexlink.app.com.lexlink.baseviews.BaseEdittext;

public class SignUpActivity extends AppCompatActivity {

    RelativeLayout rootView,rl_salutation,rl_city;
    BaseEdittext signup_et_first_name,signup_et_middle_name,signup_et_last_name,signup_et_address,signup_et_post_code;
    Button signup_button;
    ImageView signup_iv_camera,signup_iv_salutation,signup_iv_city;
    TextView tv_salutation,tv_city;
    String fName,mName,lName,address,postCode,salutation="Mr",city="London,Uk";
    int selectedId,selectedIdCity;

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

    }
    private void idMapping() {
        rootView= (RelativeLayout) findViewById(R.id.activity_sign_up_root);
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
        final Dialog dialog = new Dialog(SignUpActivity.this);
        dialog.setContentView(R.layout.signup_camera_popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.TOP;
        dialog.getWindow().clearFlags(lp.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(lp);
        dialog.show();
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

        //Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
//        LayoutInflater layoutInflater =
//                    (LayoutInflater)getBaseContext()
//                            .getSystemService(LAYOUT_INFLATER_SERVICE);
//            popupView = layoutInflater.inflate(R.layout.signup_salutation_spinner, null);
//            popupWindow = new PopupWindow(popupView, AppBarLayout.LayoutParams.MATCH_PARENT, AppBarLayout.LayoutParams.WRAP_CONTENT);
//            RadioGroup salutation_radiogroup= (RadioGroup) popupView.findViewById(R.id.salutation_radiogroup);
//            salutation_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                    int selectedId = radioGroup.getCheckedRadioButtonId();
//                    RadioButton radioButton= (RadioButton) popupView.findViewById(selectedId);
//                    salutation=radioButton.getText().toString();
//                    Log.e("Tag",">"+salutation);
//                    tv_salutation.setText(salutation);
//                    popupWindow.dismiss();
//
//
//                }
//            });
//
//                popupWindow.showAsDropDown(rl_salutation, 50, 20);

    }
    private void showCityPopup()
    {
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




    public  void hideKeyboard()
    {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
