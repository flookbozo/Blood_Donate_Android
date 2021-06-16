package com.example.myproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myproject2.Models.UserModel;
import com.example.myproject2.Rertofit.ApiClient;
import com.example.myproject2.Rertofit.ApiInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    SessionLoginManager sessionLoginManager;

    @BindView(R.id.editText_username) EditText editTextEmail;
    @BindView(R.id.editText_password) EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        sessionLoginManager = new SessionLoginManager(this);
    }

    public void loginUser(View v) {
        Call<UserModel> userModelCall = apiInterface.loginUser(editTextEmail.getText().toString(),
                editTextPassword.getText().toString());

        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.body()!=null) {
                    UserModel userModel = response.body();


                    if(userModel!=null) {
                        int userId = userModel.getId();
                        sessionLoginManager.createSession(userId);
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "user not found"+userModel.getName(), Toast.LENGTH_LONG).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error could'n connect"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void registerUser(View v) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}