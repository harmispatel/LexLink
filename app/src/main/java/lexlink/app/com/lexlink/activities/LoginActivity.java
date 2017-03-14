package lexlink.app.com.lexlink.activities;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
            }
        });

    }

    private void idMapping() {
        rootView= (RelativeLayout) findViewById(R.id.activity_login_root);
        login_toggle= (ToggleButton) findViewById(R.id.login_toggle);
        login_email= (BaseEdittext) findViewById(R.id.login_email);
        login_password= (BaseEdittext) findViewById(R.id.login_password);
        login_frogot_pass= (BaseTextview) findViewById(R.id.login_frogot_pass);
        login_reset_pass= (BaseTextview) findViewById(R.id.login_reset_pass);
        button_sign_in= (Button) findViewById(R.id.button_sign_in);

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
