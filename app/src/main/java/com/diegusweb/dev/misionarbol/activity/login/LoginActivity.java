package com.diegusweb.dev.misionarbol.activity.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.diegusweb.dev.misionarbol.MainActivity;
import com.diegusweb.dev.misionarbol.R;
import com.diegusweb.dev.misionarbol.api.ApiClient;
import com.diegusweb.dev.misionarbol.api.ApiInterface;
import com.diegusweb.dev.misionarbol.entities.User;
import com.diegusweb.dev.misionarbol.helper.InfoConstants;
import com.diegusweb.dev.misionarbol.models.InfoUser;
import com.diegusweb.dev.misionarbol.models.Login;
import com.diegusweb.dev.misionarbol.models.TestItems;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.btnLogin)
    LoginButton btnLogin;
    @Bind(R.id.container)
    RelativeLayout container;

    @Bind(R.id.btn_signup)
    Button btn_signup;
    @Bind(R.id.btn_reset_password)
    Button btn_reset_password;
    @Bind(R.id.btn_login)
    Button btn_login;

    @Bind(R.id.txtEmail)
    EditText txtEmail;
    @Bind(R.id.txtPassword)
    EditText txtPassword;

    private final boolean isAuth = false;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        if(AccessToken.getCurrentAccessToken() != null){
            navigateToMainScreen();
        }

        if(isAuth){
            navigateToMainScreen();
        }

        //btn_login.setEnabled(false);

        callbackManager = CallbackManager.Factory.create();
        btnLogin.setPublishPermissions(Arrays.asList("publish_actions"));
        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //navigateToMainScreen();
                Log.d("Login", "demoooo");
            }

            @Override
            public void onCancel() {
                Snackbar.make(container, R.string.login_cancel_error, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                String msgError = String.format(getString(R.string.login_cancel_error), error.getLocalizedMessage());
                Snackbar.make(container, R.string.login_cancel_error, Snackbar.LENGTH_SHORT).show();;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void navigateToMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    //signup
    @OnClick(R.id.btn_signup)
    public void btnSignup(){
        startActivity(new Intent(this, SignupActivity.class));
    }

    @OnClick(R.id.btn_reset_password)
    public void btnResetPassword(){
        startActivity(new Intent(this, ResetPasswordActivity.class));
    }

    //Login Email
    @OnClick(R.id.btn_login)
    public void btnlogin(){
        if (!validate()) {
            onLoginFailed();
            return;
        }else{

            onLoginSuccess();
        }
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btn_login.setEnabled(true);


    }

    public void onLoginSuccess() {
        //progressBar.setVisibility(View.VISIBLE);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.Theme_RicoPaRico_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        btn_login.setEnabled(false);
        //Toast.makeText(getBaseContext(), "Login Success", Toast.LENGTH_LONG).show();


        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> call = apiService.authenticate(email, password);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Login mLoginObject = response.body();
                if(mLoginObject.error != null){
                    progressDialog.hide();
                    btn_login.setEnabled(true);

                    Toast.makeText(getBaseContext(), mLoginObject.error, Toast.LENGTH_LONG).show();
                }else{

                    InfoConstants.API_TOKEN = mLoginObject.token;
                    //getUserAccountInfo(mLoginObject.token, txtEmail.getText().toString());

                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<InfoUser> calls = apiService.getInfoUser(InfoConstants.API_TOKEN , txtEmail.getText().toString());

                    calls.enqueue(new Callback<InfoUser>() {
                        @Override
                        public void onResponse(Call<InfoUser> call, Response<InfoUser> response) {
                            InfoUser mInfoUser = response.body();
                            if(mInfoUser.getEmail() != null){
                                User user = new User();
                                user.setFirstName(mInfoUser.getFirt_name());
                                user.setLastName(mInfoUser.getLast_name());
                                user.setEmail(mInfoUser.getEmail());
                                user.setToken(InfoConstants.API_TOKEN);

                                user.save();
                                progressDialog.hide();
                                Toast.makeText(getBaseContext(), "SAVE", Toast.LENGTH_LONG).show();

                            }
                            else{
                                Toast.makeText(getBaseContext(), "ERROR", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<InfoUser> call, Throwable t) {

                        }
                    });

                    //progressDialog.hide();

                    //navigateToMainScreen();
                    //Toast.makeText(getBaseContext(), mLoginObject.token, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });
    }

    /*public boolean getUserAccountInfo(final String tokenUsers, String email)
    {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<InfoUser> call = apiService.getInfoUser(tokenUsers, email);
        call.enqueue(new Callback<InfoUser>() {
            @Override
            public void onResponse(Call<InfoUser> call, Response<InfoUser> response) {
                InfoUser mInfoUser = response.body();
                if(mInfoUser.getEmail() != null){
                    User user = new User();
                    user.setFirstName(mInfoUser.getFirt_name());
                    user.setLastName(mInfoUser.getLast_name());
                    user.setEmail(mInfoUser.getEmail());
                    user.setToken(tokenUsers);

                    user.save();
                    Toast.makeText(getBaseContext(), "SAVE", Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(getBaseContext(), "ERROR", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<InfoUser> call, Throwable t) {

            }
        });


    }*/

    public boolean validate() {
        boolean valid = true;

        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmail.setError("enter a valid email address");
            valid = false;
        } else {
            txtEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            txtPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            txtPassword.setError(null);
        }

        return valid;
    }

    //db
    /*protected void insertStudent(User s) {
        // List<StudentDBEntry> arr = new ArrayList<StudentDBEntry>();
        //arr.add(s);
        TransactionManager.getInstance().addTransaction(new SaveModelTransaction<>(ProcessModelInfo.withModels(s)));
    }*/

}