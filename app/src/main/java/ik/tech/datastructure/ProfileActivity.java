package ik.tech.datastructure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    private TextView userName,userEmail;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userName=findViewById(R.id.userName);
        userEmail=findViewById(R.id.userEmail);

        auth=FirebaseAuth.getInstance();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            userName.setText(account.getDisplayName());
            userEmail.setText(account.getEmail());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,MainActivity.class));
    }

    public void logOut(View view) {
        auth.signOut();
        startActivity(new Intent(this,MainActivity.class));
    }
}