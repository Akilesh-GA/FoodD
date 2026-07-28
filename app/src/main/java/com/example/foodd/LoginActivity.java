package com.example.foodd;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
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

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText emailField;
    EditText passwordField;
    Button loginButton;
    TextView gotoSignUpText;

    String email;
    String password;
    String gotoSignUp;

    boolean isSignUpSuccess;

    SpannableString spannableText;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        auth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.login_email_field);
        passwordField = findViewById(R.id.login_password_field);
        loginButton = findViewById(R.id.login_button);
        gotoSignUpText = findViewById(R.id.goto_sign_up);

        spannableText = makeSpannableText(gotoSignUpText);

        gotoSignUpText.setText(spannableText);

        isSignUpSuccess();

        gotoSignUpText.setOnClickListener(view -> moveToSignUp());

        loginButton.setOnClickListener(view -> loginUser());
    }

    private void loginUser() {
        email = emailField.getText().toString().trim();
        password = passwordField.getText().toString().trim();

        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Enter Values", Toast.LENGTH_LONG).show();
            return;
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task ->{
            if(task.isSuccessful()) {
                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();
                moveToHome();
                return;
            }
            else {
                String err = (task.getException() != null) ? task.getException().getMessage() : "Authentication Failed!";
                Toast.makeText(LoginActivity.this, err, Toast.LENGTH_LONG).show();
                return;
            }
        });

    }

    private SpannableString makeSpannableText(TextView gotoSignUpText) {
        gotoSignUp = gotoSignUpText.getText().toString().trim();

        int start = gotoSignUp.indexOf("Sign Up");
        int end = gotoSignUp.length();

        SpannableString spannable = new SpannableString(gotoSignUp);

        spannable.setSpan(
                new StyleSpan(Typeface.BOLD),
                start,
                end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        spannable.setSpan(
                new ForegroundColorSpan(ContextCompat.getColor(this, R.color.orange)),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        return spannable;
    }

    private void moveToSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
        finish();
    }

    private void moveToHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void isSignUpSuccess() {
        boolean successMessage = getIntent().getBooleanExtra("signup_success", false);
        if(successMessage) {
            Toast.makeText(this, "Account created Successfully!", Toast.LENGTH_LONG).show();
        }
    }
}
