package com.example.foodd;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

public class UserProfileActivity extends AppCompatActivity {

    private TextView userName, userMail, userMobile, userGender;

    private FirebaseAuth auth;
    private DatabaseReference db;

    String name = "", email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        userName = findViewById(R.id.user_name);
        userMail = findViewById(R.id.user_email);
        userMobile = findViewById(R.id.user_mobile);
        userGender = findViewById(R.id.user_gender);

        auth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = auth.getCurrentUser();

        if (firebaseUser == null) {
            Toast.makeText(this, "No logged in user", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        String uid = firebaseUser.getUid();

        db = FirebaseDatabase.getInstance()
                .getReference("users")
                .child(uid);

        loadUser(firebaseUser);
    }

    private void loadUser(FirebaseUser firebaseUser) {

        db.addListenerForSingleValueEvent(new ValueEventListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    UserEntity user = snapshot.getValue(UserEntity.class);

                    if (user != null) {
                        userName.setText(user.getName());
                        userMail.setText(user.getEmail());
                        userMobile.setText("Not Added");
                        userGender.setText("Not Added");
                    }

                } else {

                    // First-time Google Sign-In user
                    name = firebaseUser.getDisplayName();
                    email = firebaseUser.getEmail();

                    if (name == null)
                        name = "User";

                    if (email == null)
                        email = "";

                    UserEntity newUser = new UserEntity(name, email);

                    db.setValue(newUser)
                            .addOnSuccessListener(unused -> {

                                userName.setText(name);
                                userMail.setText(email);
                                userMobile.setText("Not Added");
                                userGender.setText("Not Added");

                            })
                            .addOnFailureListener(e ->
                                    Toast.makeText(UserProfileActivity.this,
                                            e.getMessage(),
                                            Toast.LENGTH_LONG).show());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(UserProfileActivity.this,
                        error.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}