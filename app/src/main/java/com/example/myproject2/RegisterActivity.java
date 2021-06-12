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

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.editTextUsername) EditText editTextUsername;
    @BindView(R.id.editTextPassword) EditText editTextPassword;
    @BindView(R.id.editTextFullname) EditText editTextFullname;
    @BindView(R.id.editTextPhone) EditText editTextPhone;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void registerUser(View v) {
        Call<UserModel> callRegister = apiInterface.registerUser(editTextUsername.getText().toString(),
                editTextPassword.getText().toString(),
                editTextFullname.getText().toString(),
                editTextPhone.getText().toString());

        callRegister.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.isSuccessful() && response.body()!=null) {
                    UserModel userModel = response.body();

                    if(userModel!=null) {
                        Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "User could'n be registered", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error could'n connect."+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}