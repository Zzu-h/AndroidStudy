package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ChatData> chatList;

    private EditText EditText_chat;
    private Button Button_send;

    private DatabaseReference myRef;

    private String nick = "nick1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        EditText_chat = findViewById(R.id.EditText_chat);
        Button_send = findViewById(R.id.Button_send);

        Button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = EditText_chat.getText().toString();
                if(msg != null) {
                    ChatData chat = new ChatData();
                    chat.setMsg(msg);
                    chat.setNickname(nick);
                    myRef.push().setValue(chat);
                }

            }
        });

        recyclerView = findViewById(R.id.RecyclerView_view);

        recyclerView.setHasFixedSize(true);



        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ChatAdapter(chatList, ChatActivity.this, nick);
        recyclerView.setAdapter(mAdapter);
        // Write a message to trnrhe database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        ChatData chat = new ChatData();
        chat.setMsg("HI");
        chat.setNickname(nick);
        myRef.setValue(chat);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ChatData chat = snapshot.getValue(ChatData.class);
                ((ChatAdapter) mAdapter).addChat(chat);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}