package com.example.myproject2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject2.Models.Message;
import com.example.myproject2.adapter.RecyclerViewAdapter;
import com.example.myproject2.room.MessageRepository;

import java.util.List;

public class MessageFragment extends Fragment {

    private RecyclerView recyclerView;
    SessionLoginManager sessionLoginManager;
    int userid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_message, container,false);
        sessionLoginManager = new SessionLoginManager(getContext());
        userid = sessionLoginManager.getUserID();
        recyclerView = rootview.findViewById(R.id.recyclerview);
        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
        reloadData();
    }

    private void reloadData() {
        MessageRepository repo = new MessageRepository(MessageFragment.this.getContext());

        repo.getMessageId(userid, new MessageRepository.GetMessageIdCallback() {
            @Override
            public void onGetSuccess(List<Message> itemList) {
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(
                        MessageFragment.this.getContext(),
                        R.layout.item_message,
                        itemList
                );
                recyclerView.setLayoutManager(new LinearLayoutManager(MessageFragment.this.getContext()));
                recyclerView.setAdapter(adapter);
            }
        });
    }

}
