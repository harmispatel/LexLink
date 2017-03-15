package lexlink.app.com.lexlink.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import lexlink.app.com.lexlink.R;
import lexlink.app.com.lexlink.baseviews.BaseEdittext;

public class SignUpActivity extends AppCompatActivity {
    RelativeLayout rootView;
    BaseEdittext signup_et_first_name,signup_et_middle_name,signup_et_last_name,signup_et_address,signup_et_post_code;
    Button signup_button;
    String fName,mName,lName,address,postCode;

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
    }

    private void idMapping() {
        rootView= (RelativeLayout) findViewById(R.id.activity_sign_up_root);
        signup_et_first_name= (BaseEdittext) findViewById(R.id.signup_et_first_name);
        signup_et_middle_name= (BaseEdittext) findViewById(R.id.signup_et_middle_name);
        signup_et_last_name= (BaseEdittext) findViewById(R.id.signup_et_last_name);
        signup_et_address= (BaseEdittext) findViewById(R.id.signup_et_address);
        signup_et_post_code= (BaseEdittext) findViewById(R.id.signup_et_post_code);
        signup_button= (Button) findViewById(R.id.signup_button);
    }

}
