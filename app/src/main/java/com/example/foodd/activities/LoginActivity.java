package com.example.foodd.activities;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.foodd.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText emailField;
    EditText passwordField;
    Button loginButton;
    TextView gotoSignUpText;
    Button googleLogin;
    String email;
    String password;
    String gotoSignUp;

    SpannableString spannableText;

    private GoogleSignInClient googleSignInClient;
    private final int RC_SIGN_IN = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        auth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.login_email_field);
        passwordField = findViewById(R.id.login_password_field);
        loginButton = findViewById(R.id.login_button);
        gotoSignUpText = findViewById(R.id.goto_sign_up);
        googleLogin = findViewById(R.id.continue_with_google);

        continueWithGoogleSetUp();

        googleLogin.setOnClickListener(view -> continueWithGoogle());

        spannableText = makeSpannableText(gotoSignUpText);

        gotoSignUpText.setText(spannableText);

        isSignUpSuccess();

        gotoSignUpText.setOnClickListener(view -> moveToSignUp());

        loginButton.setOnClickListener(view -> loginUser());
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

    private void loginUser() {
        email = emailField.getText().toString().trim();
        password = passwordField.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Enter Email or Password", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                moveToHome();
                return;
            } else {
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
        if (successMessage) {
            Toast.makeText(this, "Account created Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task =
                    GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());

                Toast.makeText(this,
                        "Welcome " + account.getDisplayName(),
                        Toast.LENGTH_SHORT).show();

            } catch (ApiException e) {
                Toast.makeText(this,
                        "Google Sign-In Failed: " + e.getStatusCode(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {

        AuthCredential credential =
                GoogleAuthProvider.getCredential(idToken, null);

        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {

                    if (task.isSuccessful()) {

                        startActivity(new Intent(this,
                                HomeActivity.class));
                        finish();

                    } else {

                        Toast.makeText(this,
                                task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }

                });
    }
}
