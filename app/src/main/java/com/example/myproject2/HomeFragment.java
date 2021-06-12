package com.example.myproject2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myproject2.Models.UserModel;
import com.example.myproject2.Rertofit.ApiClient;
import com.example.myproject2.Rertofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    ApiInterface apiInterface;
    SessionLoginManager sessionLoginManager;
    int userid;
    String user_name;
    String user_gender;
    String user_typeblood;
    String user_tel;
    UserModel user;

    TextView user_name_text;
    TextView user_gender_text;
    TextView user_typeblood_text;
    TextView user_tel_text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_home, container,false);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        sessionLoginManager = new SessionLoginManager(getContext());
        userid = sessionLoginManager.getUserID();
        user = new UserModel();
        getDataUser();

        user_name_text = (TextView) rootview.findViewById(R.id.user_name);
        user_gender_text = (TextView) rootview.findViewById(R.id.user_gender);
        user_typeblood_text = (TextView) rootview.findViewById(R.id.user_typeblood);
        user_tel_text = (TextView) rootview.findViewById(R.id.user_tel);


        return rootview;
    }

    public void getDataUser() {
        Call<UserModel> userModelCall = apiInterface.getDataUser(userid);

        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.body()!=null) {
                    UserModel userModel = response.body();

                    if(userModel!=null) {
                        user.setName(userModel.getName());
                        user.setUser_gender(userModel.getUser_gender());
                        user.setUser_bloodtype(userModel.getUser_bloodtype());
                        user.setUser_tel(userModel.getUser_tel());
                        user_name = userModel.getName();
                        user_gender = userModel.getUser_gender();
                        user_typeblood = userModel.getUser_bloodtype();
                        user_tel = userModel.getUser_tel();
                        user_name_text.setText(user_name);
                        user_gender_text.setText(user_gender);
                        user_typeblood_text.setText(user_typeblood);
                        user_tel_text.setText(user_tel);
                        Toast.makeText(HomeFragment.this.requireActivity(),user.getName()+","+user.getUser_gender()+","+user.getUser_bloodtype()+","+user.getUser_tel(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(HomeFragment.this.requireActivity(), "Error could'n connect"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
