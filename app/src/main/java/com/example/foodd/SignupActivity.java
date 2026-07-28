package com.example.foodd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    EditText nameField;
    EditText emailField;
    EditText passwordField;
    EditText confirmPasswordField;
    Button signUpButton;
    TextView gotoLoginText;

    private FirebaseAuth auth;
    private DatabaseReference db;

    String name = "";
    String email = "";
    String password = "";
    String confirmPassword = "";
    String gotoLogin = "";

    SpannableString spannableText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference("users");

        setContentView(R.layout.signup_screen);

        nameField = findViewById(R.id.signup_name_field);
        emailField = findViewById(R.id.signup_email_field);
        passwordField = findViewById(R.id.signup_password_field);
        confirmPasswordField =findViewById(R.id.signup_c_password_field);
        gotoLoginText = findViewById(R.id.goto_login);

        signUpButton = findViewById(R.id.signup_button);

        gotoLogin = gotoLoginText.getText().toString().trim();
        spannableText = makeSpannableText(gotoLogin);
        gotoLoginText.setText(spannableText);

        gotoLoginText.setOnClickListener(view -> moveToLogIn());

        signUpButton.setOnClickListener(view -> registerUser());
    }

    private void registerUser() {
        name = nameField.getText().toString().trim();
        email = emailField.getText().toString().trim();
        password = passwordField.getText().toString().trim();
        confirmPassword = confirmPasswordField.getText().toString().trim();

        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Enter Values", Toast.LENGTH_LONG).show();
            return;
        }
        if(password.length() < 6) {
            Toast.makeText(this, "password must be at least 6 characters long", Toast.LENGTH_LONG).show();
            return;
        }
        if(!password.equals(confirmPassword)) {
            Toast.makeText(this, "Password do not match", Toast.LENGTH_LONG).show();
            return;
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if(task.isSuccessful()) {
                FirebaseUser firebaseUser = auth.getCurrentUser();

                if(firebaseUser != null) {

                    String userId = firebaseUser.getUid();

                    UserEntity user = new UserEntity(name, email);

                    db.child(userId).setValue(user)
                            .addOnSuccessListener(unused -> {
                                Toast.makeText(this, "Account created Successfully!", Toast.LENGTH_SHORT).show();
                                moveToLogIn();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                            });
                }
            } else {
                Toast.makeText(SignupActivity.this, "User already Existing!", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }

    private SpannableString makeSpannableText(String text) {

        SpannableString spannable = new SpannableString(text);

        int start = gotoLogin.indexOf("Login");
        int end = gotoLogin.length();

        spannable.setSpan(
                new StyleSpan(Typeface.BOLD),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        spannable.setSpan(
                new ForegroundColorSpan(ContextCompat.getColor(this, R.color.orange)),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        return spannable;
    }

    private void moveToLogIn() {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
