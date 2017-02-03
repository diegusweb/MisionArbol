package com.diegusweb.dev.misionarbol.activity.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.diegusweb.dev.misionarbol.R;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends AppCompatActivity {

    @Bind(R.id.email)
    EditText inputEmail;

    @Bind(R.id.password)
    EditText inputPassword;

    @Bind(R.id.sign_up_button)
    Button sign_up_button;

    @Bind(R.id.sign_in_button)
    Button sign_in_button;

    @Bind(R.id.btn_reset_password)
    Button btn_reset_password;

    //private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);
    }


    @OnClick(R.id.sign_up_button)
    public void btnSignup(){
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
	
		//call api register
		ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> call = apiService.signup(email, password);
		call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Login model = response.body();
                
				 if (model==null) {
					//404 or the response cannot be converted to GitHubUser.
					ResponseBody responseBody = response.errorBody();
					if (responseBody!=null) {
						try {
							Toast.makeText(getBaseContext(), "responseBody = "+responseBody.string(), Toast.LENGTH_LONG).show();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						Toast.makeText(getBaseContext(), "responseBody = null", Toast.LENGTH_LONG).show();
					}
				} else {
					//200
					
				}

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
				Toast.makeText(getBaseContext(), "t = "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
		//------------

        /*User arr = new User();
        arr.setEmail(email);
        arr.setPassword(password);
        arr.setFirstName("Diego");
        arr.setLastName("Rueda");

        if(getUsers() > 0){
            Toast.makeText(getBaseContext(), "count "+ getUsers(), Toast.LENGTH_LONG).show();
        }
        else{
            TransactionManager
                    .getInstance()
                    .addTransaction(new SaveModelTransaction<>(ProcessModelInfo.withModels(arr)));
        }*/
    }

   /* private int getUsers() {
        List<User> users =  SQLite.select()
                .from(User.class)
                .where(User_Table.email.eq("diedf@dd.com"))
                .queryList();

        return users.size();
    }*/
}
