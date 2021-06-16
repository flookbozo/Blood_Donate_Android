package com.example.myproject2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject2.MessageDetailsActivity;
import com.example.myproject2.Models.Message;
import com.example.myproject2.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private int mResource;
    private List<Message> mMessageList;

    public RecyclerViewAdapter(Context mContext, int mResource, List<Message> mMessageList) {
        this.mContext = mContext;
        this.mResource = mResource;
        this.mMessageList = mMessageList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Message message = mMessageList.get(position);

        holder.message = message;
        holder.hosnameTextView.setText(message.hosname);

    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView hosnameTextView;
        private Message message;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.hosnameTextView = itemView.findViewById(R.id.hos_name_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MessageDetailsActivity.class);
                    intent.putExtra("idUser", message.idUser);
                    intent.putExtra("hosname", message.hosname);
                    intent.putExtra("active", message.active);
                    intent.putExtra("idRequest", message.idRequest);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
