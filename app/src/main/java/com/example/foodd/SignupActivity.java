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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class SignupActivity extends AppCompatActivity {
    EditText nameField;
    EditText emailField;
    EditText passwordField;
    EditText confirmPasswordField;
    Button signUpButton;
    TextView gotoLoginText;
    Button googleSignUp;

    private FirebaseAuth auth;
    private DatabaseReference db;

    String name = "";
    String email = "";
    String password = "";
    String confirmPassword = "";
    String gotoLogin = "";

    SpannableString spannableText;

    private GoogleSignInClient googleSignInClient;
    private final int RC_SIGN_IN = 100;

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
        googleSignUp = findViewById(R.id.continue_with_google);

        gotoLogin = gotoLoginText.getText().toString().trim();
        spannableText = makeSpannableText(gotoLogin);
        gotoLoginText.setText(spannableText);

        gotoLoginText.setOnClickListener(view -> moveToLogIn());

        continueWithGoogleSetUp();

        googleSignUp.setOnClickListener(view -> continueWithGoogle());

        signUpButton.setOnClickListener(view -> registerUser());
    }

    private void continueWithGoogleSetUp() {
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void continueWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void registerUser() {
        name = nameField.getText().toString().trim();
        email = emailField.getText().toString().trim();
        password = passwordField.getText().toString().trim();
        confirmPassword = confirmPasswordField.getText().toString().trim();

        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Enter Values", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length() < 6) {
            Toast.makeText(this, "password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password.equals(confirmPassword)) {
            Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task =
                    GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                Toast.makeText(this,
                        "Welcome " + account.getDisplayName(),
                        Toast.LENGTH_SHORT).show();

                startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                finish();

            } catch (ApiException e) {
                Toast.makeText(this,
                        "Google Sign-In Failed: " + e.getStatusCode(),
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
