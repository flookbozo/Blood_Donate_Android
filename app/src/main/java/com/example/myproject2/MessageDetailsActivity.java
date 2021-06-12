package com.example.myproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject2.Models.Message;
import com.example.myproject2.Rertofit.ApiClient;
import com.example.myproject2.Rertofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageDetailsActivity extends AppCompatActivity {

    Button okButtonView;
    Button cancelButtonView;
    int answer;
    int activestatus;
    int idRequest;
    Message mMessage;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);

        Intent intent = getIntent();
        String hospitalname = intent.getStringExtra("hosname");
        activestatus = intent.getIntExtra("active", 0);
        idRequest = intent.getIntExtra("idRequest", 0);

        TextView hosname = findViewById(R.id.hospital_name_text_view);
        hosname.setText(hospitalname);

        okButtonView = findViewById(R.id.ok_button);
        cancelButtonView = findViewById(R.id.button_cancel);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        okButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = 1;
                userReply(idRequest, answer);
            }
        });
    }

    public void userReply(int idRequest, int answer) {
        Call<Message> callmessage = apiInterface.userReply(idRequest,answer);
        callmessage.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(response.isSuccessful()) {
                    mMessage = response.body();
                    if(mMessage.isSuccess()) {
                        Toast.makeText(MessageDetailsActivity.this,
                                "ขอบคุณสำหรับความร่วมมือ ทางโรงพยาบาลกำลังรอโลหิตของคุณอยู่",
                                Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(MessageDetailsActivity.this,
                            "error",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(MessageDetailsActivity.this,
                        t.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}