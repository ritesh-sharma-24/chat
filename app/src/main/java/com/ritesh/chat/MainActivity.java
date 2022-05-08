package com.ritesh.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ritesh.chat.Adapters.FragmentsAdapter;
import com.ritesh.chat.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    //take binding of MAinActivity
    ActivityMainBinding binding;

    //take fireBaseAuth
    FirebaseAuth auth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("hello");

        //take auth for to get the instance
        auth = FirebaseAuth.getInstance();

        binding.viewPager.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));
        binding.tablayout.setupWithViewPager(binding.viewPager);


    }



    //use onCreateOptionMenu function to add menu file in the backend code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu here
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
    //now code on menu to do some actions to use onOptionsItemSelected function
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // use switchCase for item that will do some action when it will be selected
        switch (item.getItemId()){
            case R.id.settings:
                Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(i);
                break;

            case R.id.logout:
                // use auth here for signOut
                auth.signOut();
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);

                break;
            case R.id.groupChat:

                Intent intentt = new Intent(MainActivity.this, GroupChatActivity.class);
                startActivity(intentt);

                break;
        }
        return true;

    }
}