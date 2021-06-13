package com.example.myproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject2.Models.UserModel;
import com.example.myproject2.Rertofit.ApiClient;
import com.example.myproject2.Rertofit.ApiInterface;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.username_text) EditText editTextUsername;
    @BindView(R.id.password_text) EditText editTextPassword;
    @BindView(R.id.password_again_text) EditText editTextPassWordAgain;
    @BindView(R.id.email_text) EditText editTextEmail;
    @BindView(R.id.dropdown_prefix) TextInputLayout dropdown_prefix;
    @BindView(R.id.prefixs) AutoCompleteTextView prefixs;
    @BindView(R.id.name_text) EditText editTextName;
    @BindView(R.id.last_name_text) EditText editTextLastName;
    @BindView(R.id.dropdown_gender) TextInputLayout dropdown_gender;
    @BindView(R.id.genders) AutoCompleteTextView genders;
    @BindView(R.id.detail_more_text_view) TextView textViewDetailMore;
    @BindView(R.id.question1_text_view) TextView textViewQuestion1;
    @BindView(R.id.answer1_radio) RadioGroup radioGroupAnswer1;
    @BindView(R.id.question2_text_view) TextView textViewQuestion2;
    @BindView(R.id.answer2_radio) RadioGroup radioGroupAnswer2;
    @BindView(R.id.question3_text_view) TextView textViewQuestion3;
    @BindView(R.id.answer3_radio) RadioGroup radioGroupAnswer3;
    @BindView(R.id.dropdown_typeblood) TextInputLayout dropdown_typeblood;
    @BindView(R.id.typebloods) AutoCompleteTextView typebloods;
    @BindView(R.id.dropdown_typerh) TextInputLayout dropdown_typerh;
    @BindView(R.id.typerhs) AutoCompleteTextView typerhs;
    @BindView(R.id.birthday) EditText editTextBirthday;
    @BindView(R.id.phonenumber) EditText editTextPhone;
    @BindView(R.id.dropdown_question) TextInputLayout dropdown_question;
    @BindView(R.id.questions) AutoCompleteTextView questions;
    @BindView(R.id.answer) EditText editTextAnswer;

    ArrayList<String> arrayList_prefix;
    ArrayAdapter<String> arrayAdapter_prefix;
    ArrayList<String> arrayList_gender;
    ArrayAdapter<String> arrayAdapter_gender;
    ArrayList<String> arrayList_typeblood;
    ArrayAdapter<String> arrayAdapter_typeblood;
    ArrayList<String> arrayList_typerh;
    ArrayAdapter<String> arrayAdapter_typerh;
    ArrayList<String> arrayList_question;
    ArrayAdapter<String> arrayAdapter_question;

    String username;
    String password;
    String passwordAgain;
    String email;
    String prefix = "";
    String name;
    String lastname;
    String gender = "";
    String hiddenAnswer1;
    String hiddenAnswer2;
    String hiddenAnswer3;
    String typeblood = "";
    String typerh = "";
    String birthday;
    String question = "";
    String answer;
    String phonenumber;

    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;

   /* @BindView(R.id.editTextUsername) EditText editTextUsername;
    @BindView(R.id.editTextPassword) EditText editTextPassword;
    @BindView(R.id.editTextFullname) EditText editTextFullname;
    @BindView(R.id.editTextPhone) EditText editTextPhone;*/

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        arrayList_prefix = new ArrayList<>();
        arrayList_prefix.add("นาย");
        arrayList_prefix.add("นาง");
        arrayList_prefix.add("นางสาว");
        arrayAdapter_prefix = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                arrayList_prefix);
        prefixs.setAdapter(arrayAdapter_prefix);
        prefixs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prefix = (String) parent.getItemAtPosition(position);
            }
        });

        arrayList_gender = new ArrayList<>();
        arrayList_gender.add("ชาย");
        arrayList_gender.add("หญิง");
        arrayAdapter_gender = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                arrayList_gender);
        genders.setAdapter(arrayAdapter_gender);
        genders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gender = (String) parent.getItemAtPosition(position);
                if(gender.equals("หญิง")) {
                    textViewDetailMore.setVisibility(View.VISIBLE);
                    textViewQuestion1.setVisibility(View.VISIBLE);
                    radioGroupAnswer1.setVisibility(View.VISIBLE);
                    textViewQuestion2.setVisibility(View.VISIBLE);
                    radioGroupAnswer2.setVisibility(View.VISIBLE);
                    textViewQuestion3.setVisibility(View.VISIBLE);
                    radioGroupAnswer3.setVisibility(View.VISIBLE);
                    radioGroupAnswer1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            radioButton1 = findViewById(checkedId);
                            hiddenAnswer1 = radioButton1.getText().toString();
                        }
                    });
                    radioGroupAnswer2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            radioButton2 = findViewById(checkedId);
                            hiddenAnswer2 = radioButton2.getText().toString();
                        }
                    });
                    radioGroupAnswer3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            radioButton3 = findViewById(checkedId);
                            hiddenAnswer3 = radioButton3.getText().toString();
                        }
                    });
                }
                else {
                    textViewDetailMore.setVisibility(View.GONE);
                    textViewQuestion1.setVisibility(View.GONE);
                    radioGroupAnswer1.setVisibility(View.GONE);
                    textViewQuestion2.setVisibility(View.GONE);
                    radioGroupAnswer2.setVisibility(View.GONE);
                    textViewQuestion3.setVisibility(View.GONE);
                    radioGroupAnswer3.setVisibility(View.GONE);
                }
            }
        });

        arrayList_typeblood = new ArrayList<>();
        arrayList_typeblood.add("A");
        arrayList_typeblood.add("B");
        arrayList_typeblood.add("AB");
        arrayList_typeblood.add("O");
        arrayAdapter_typeblood = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                arrayList_typeblood);
        typebloods.setAdapter(arrayAdapter_typeblood);
        typebloods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeblood = (String) parent.getItemAtPosition(position);
            }
        });

        arrayList_typerh = new ArrayList<>();
        arrayList_typerh.add("RH+");
        arrayList_typerh.add("RH-");
        arrayAdapter_typerh = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                arrayList_typerh);
        typerhs.setAdapter(arrayAdapter_typerh);
        typerhs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typerh = (String) parent.getItemAtPosition(position);
            }
        });

        arrayList_question = new ArrayList<>();
        arrayList_question.add("สัตว์เลี้ยงตัวแรก");
        arrayList_question.add("ชื่อสัตว์เลี้ยงตัวแรก");
        arrayList_question.add("จังหวัดที่คุณเกิด");
        arrayList_question.add("ชื่อเล่นบิดา");
        arrayList_question.add("ชื่อเล่นมารดา");
        arrayList_question.add("สัตว์ที่คุณไม่ชอบ");
        arrayList_question.add("สีทีีคุณชอบ");
        arrayAdapter_question = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                arrayList_question);
        questions.setAdapter(arrayAdapter_question);
        questions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                question = (String) parent.getItemAtPosition(position);
            }
        });
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void regisUser(View v) {
        int error = 0;
        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();
        passwordAgain = editTextPassWordAgain.getText().toString();
        email = editTextEmail.getText().toString();
        name = editTextName.getText().toString();
        lastname = editTextLastName.getText().toString();
        birthday = editTextBirthday.getText().toString();
        answer = editTextAnswer.getText().toString();
        phonenumber = editTextPhone.getText().toString();

        /*Toast.makeText(RegisterActivity.this,
                gender+", "+hiddenAnswer1+", "+hiddenAnswer2+", "+hiddenAnswer3,
                Toast.LENGTH_LONG).show();*/

        if(username.isEmpty()) {
            error++;
        }
        if(password.isEmpty()) {
            error++;
        }
        if(passwordAgain.isEmpty()) {
            error++;
        }
        if(email.isEmpty()) {
            error++;
        }
        if(prefix.isEmpty()) {
            error++;
        }
        if(name.isEmpty()) {
            error++;
        }
        if(lastname.isEmpty()) {
            error++;
        }
        if(gender.isEmpty()) {
            error++;
        }
        if(gender.equals("หญิง")) {
            if(hiddenAnswer1.isEmpty()) {
                error++;
            }
            if(hiddenAnswer2.isEmpty()) {
                error++;
            }
            if(hiddenAnswer3.isEmpty()) {
                error++;
            }
        }
        if(typeblood.isEmpty()) {
            error++;
        }
        if(typerh.isEmpty()) {
            error++;
        }
        if(birthday.isEmpty()) {
            error++;
        }
        if(question.isEmpty()) {
            error++;
        }
        if(answer.isEmpty()) {
            error++;
        }
        if(phonenumber.isEmpty()) {
            error++;
        }
        if(error > 0) {
            Toast.makeText(RegisterActivity.this,
                "Every Field is required."+" "+birthday,
                Toast.LENGTH_LONG).show();
        }
        else {
            Call<UserModel> callRegister = apiInterface.registerUser(username,
                    password,
                    email,
                    prefix,
                    name,
                    lastname,
                    gender,
                    hiddenAnswer1,
                    hiddenAnswer2,
                    hiddenAnswer3,
                    typeblood,
                    typerh,
                    birthday,
                    question,
                    answer,
                    phonenumber);
            callRegister.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    if(response.isSuccessful() && response.body()!=null) {
                        UserModel userModel = response.body();
                        if (userModel!=null) {
                            Toast.makeText(RegisterActivity.this,
                                    userModel.getMessage(),
                                    Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this,
                            t.toString(),
                            Toast.LENGTH_LONG).show();
                }
            });
            Toast.makeText(RegisterActivity.this,
                "Register Success.",
                Toast.LENGTH_LONG).show();
        }

    }

    /*public void registerUser(View v) {
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
    }*/
}