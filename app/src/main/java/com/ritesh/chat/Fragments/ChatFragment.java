package com.ritesh.chat.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ritesh.chat.Adapters.UsersAdapter;
import com.ritesh.chat.Models.Users;
import com.ritesh.chat.R;
import com.ritesh.chat.databinding.FragmentChatBinding;

import java.util.ArrayList;


public class ChatFragment extends Fragment {


    public ChatFragment() {
        // Required empty public constructor
    }

    FragmentChatBinding binding;

    ArrayList<Users> list = new ArrayList<>();

    FirebaseDatabase database;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater, container, false);

        //take database instance
        database= FirebaseDatabase.getInstance();

        //here use UserAdapter
        UsersAdapter adapter = new UsersAdapter(list, getContext());
        binding.chatRecyclarView.setAdapter(adapter);

        //set here LinearLayoutManager because here to set a view to top to bottom
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.chatRecyclarView.setLayoutManager(layoutManager);

        //take dataBase here to fetch all the user in a chat
        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                    list.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Users users = dataSnapshot.getValue(Users.class);
                    users.setUserId(dataSnapshot.getKey());
                    if (!users.getUserId().equals(FirebaseAuth.getInstance().getUid())){// this will show only other user in the chat not own
                    list.add(users);
                    }

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return  binding.getRoot();
    }
}