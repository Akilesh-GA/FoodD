package com.example.foodd;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    TextView userName;
    TextView userMail;
    TextView userMobile;
    TextView userGender;

    Button deleteAccount;
    Button disableAccount;

    private FirebaseAuth auth;
    private DatabaseReference db;

    String userId = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        initViews();
        initFirebase();
    }

    private void initViews() {
        userName = findViewById(R.id.user_name);
        userMail = findViewById(R.id.user_email);
        userGender = findViewById(R.id.user_gender);
        userMobile = findViewById(R.id.user_mobile);
    }

    private void initFirebase() {
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if(user != null) {
            userId = user.getUid();
            db = FirebaseDatabase.getInstance().getReference("users").child(userId);

            fetchUserData();
        }
    }

    private void fetchUserData() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserEntity user = snapshot.getValue(UserEntity.class);

                if(user != null) {
                    userName.setText(user.getName());
                    userMail.setText(user.getEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfile.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
