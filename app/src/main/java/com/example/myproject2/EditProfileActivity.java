package com.example.myproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    EditText editTextUsername;
    EditText editTextEmail;
    TextInputLayout dropdown_prefix;
    AutoCompleteTextView prefixs;
    EditText editTextName;
    EditText editTextLastName;
    TextInputLayout dropdown_gender;
    AutoCompleteTextView genders;
    TextView textViewDetailMore;
    TextView textViewQuestion1;
    RadioGroup radioGroupAnswer1;
    TextView textViewQuestion2;
    RadioGroup radioGroupAnswer2;
    TextView textViewQuestion3;
    RadioGroup radioGroupAnswer3;
    TextInputLayout dropdown_typeblood;
    AutoCompleteTextView typebloods;
    TextInputLayout dropdown_typerh;
    AutoCompleteTextView typerhs;
    EditText editTextBirthday;
    EditText editTextPhone;
    RadioButton editTrueAnswer1;
    RadioButton editFalseAnswer1;
    RadioButton editTrueAnswer2;
    RadioButton editFalseAnswer2;
    RadioButton editTrueAnswer3;
    RadioButton editFalseAnswer3;

    ArrayList<String> arrayList_prefix;
    ArrayAdapter<String> arrayAdapter_prefix;
    ArrayList<String> arrayList_gender;
    ArrayAdapter<String> arrayAdapter_gender;
    ArrayList<String> arrayList_typeblood;
    ArrayAdapter<String> arrayAdapter_typeblood;
    ArrayList<String> arrayList_typerh;
    ArrayAdapter<String> arrayAdapter_typerh;

    String username;
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
    String phonenumber;

    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;

    ApiInterface apiInterface;
    SessionLoginManager sessionLoginManager;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        sessionLoginManager = new SessionLoginManager(EditProfileActivity.this);
        userId = sessionLoginManager.getUserID();

        editTextUsername = findViewById(R.id.edit_username_text);
        editTextEmail = findViewById(R.id.edit_email_text);
        dropdown_prefix = findViewById(R.id.edit_dropdown_prefix);
        prefixs = findViewById(R.id.edit_prefixs);
        editTextName = findViewById(R.id.edit_name_text);
        editTextLastName = findViewById(R.id.edit_last_name_text);
        dropdown_gender = findViewById(R.id.edit_dropdown_gender);
        genders = findViewById(R.id.edit_genders);
        textViewDetailMore = findViewById(R.id.edit_detail_more_text_view);
        textViewQuestion1 = findViewById(R.id.edit_question1_text_view);
        textViewQuestion2 = findViewById(R.id.edit_question2_text_view);
        textViewQuestion3 = findViewById(R.id.edit_question3_text_view);
        radioGroupAnswer1 = findViewById(R.id.edit_answer1_radio);
        radioGroupAnswer2 = findViewById(R.id.edit_answer2_radio);
        radioGroupAnswer3 = findViewById(R.id.edit_answer3_radio);
        dropdown_typeblood = findViewById(R.id.edit_dropdown_typeblood);
        typebloods = findViewById(R.id.edit_typebloods);
        dropdown_typerh = findViewById(R.id.edit_dropdown_typerh);
        typerhs = findViewById(R.id.edit_typerhs);
        editTextBirthday = findViewById(R.id.edit_birthday);
        editTextPhone = findViewById(R.id.edit_phonenumber);
        editTrueAnswer1 = findViewById(R.id.edit_true_answer1);
        editTrueAnswer2 = findViewById(R.id.edit_true_answer2);
        editTrueAnswer3 = findViewById(R.id.edit_true_answer3);
        editFalseAnswer1 = findViewById(R.id.edit_false_answer1);
        editFalseAnswer2 = findViewById(R.id.edit_false_answer2);
        editFalseAnswer3 = findViewById(R.id.edit_false_answer3);


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
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        getDatauser();
    }

    public void getDatauser() {
        Call<UserModel> callGetData = apiInterface.getDataUser(userId);
        callGetData.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.body()!=null) {
                    UserModel userModel = response.body();

                    if(userModel!=null) {
                        editTextUsername.setText(userModel.getUsername());
                        editTextEmail.setText(userModel.getEmail());
                        prefixs.setText(userModel.getPrefix());
                        prefix = userModel.getPrefix();
                        editTextName.setText(userModel.getName());
                        editTextLastName.setText(userModel.getLastname());
                        genders.setText(userModel.getUser_gender());
                        gender = userModel.getUser_gender();
                        if(userModel.getUser_gender().equals("หญิง")) {
                            textViewDetailMore.setVisibility(View.VISIBLE);
                            textViewQuestion1.setVisibility(View.VISIBLE);
                            radioGroupAnswer1.setVisibility(View.VISIBLE);
                            textViewQuestion2.setVisibility(View.VISIBLE);
                            radioGroupAnswer2.setVisibility(View.VISIBLE);
                            textViewQuestion3.setVisibility(View.VISIBLE);
                            radioGroupAnswer3.setVisibility(View.VISIBLE);
                            if(userModel.getDuringpregnancy().equals("ใช่")) {
                                editTrueAnswer1.setChecked(true);
                                hiddenAnswer1 = "ใช่";
                            }
                            else {
                                editFalseAnswer1.setChecked(true);
                                hiddenAnswer1 = "ไม่ใช่";
                            }
                            if(userModel.getBreastfeeding().equals("ใช่")) {
                                editTrueAnswer2.setChecked(true);
                                hiddenAnswer2 = "ใช่";
                            }
                            else {
                                editFalseAnswer2.setChecked(true);
                                hiddenAnswer2 = "ไม่ใช่";
                            }
                            if(userModel.getGivebirth_past_6().equals("ใช่")) {
                                editTrueAnswer3.setChecked(true);
                                hiddenAnswer3 = "ไม่ใช่";
                            }
                            else {
                                editFalseAnswer3.setChecked(true);
                                hiddenAnswer3 = "ไม่ใช่";
                            }
                        }
                        typebloods.setText(userModel.getUser_bloodtype());
                        typeblood = userModel.getUser_bloodtype();
                        typerhs.setText(userModel.getTyperh());
                        typerh = userModel.getTyperh();
                        editTextBirthday.setText(userModel.getDate());
                        editTextPhone.setText(userModel.getUser_tel());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this,
                        t.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void editDataUser(View v) {
        username = editTextUsername.getText().toString();
        email = editTextEmail.getText().toString();
        name = editTextName.getText().toString();
        lastname = editTextLastName.getText().toString();
        birthday = editTextBirthday.getText().toString();
        phonenumber = editTextPhone.getText().toString();

        Call<UserModel> callUser = apiInterface.updateDataUser(userId,
                username,
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
                phonenumber);
        callUser.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserModel userModel = response.body();
                    if (userModel != null) {
                        Toast.makeText(EditProfileActivity.this,
                                "Edit Profile Success",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(EditProfileActivity.this,
                            response.message()+", "+hiddenAnswer1+", "+gender,
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this,
                        t.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}