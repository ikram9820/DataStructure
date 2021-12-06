package ik.tech.datastructure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private TextView userName,userEmail;
    private ImageView profileImg;
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private DatabaseReference users;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db=FirebaseDatabase.getInstance();
        users=db.getReference("users");

        userName=findViewById(R.id.userName);
        userEmail=findViewById(R.id.userEmail);

        profileImg=(ImageView) findViewById(R.id.profileImg) ;
        auth=FirebaseAuth.getInstance();
        FirebaseUser user =auth.getCurrentUser();// GoogleSignIn.getLastSignedInAccount(this);
        if(user!=null){
            name =user.getDisplayName();

            if(name==null) {
                users.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                            name = snapshot.child("name").getValue(String.class);
                        Toast.makeText(ProfileActivity.this, " name = " + name, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ProfileActivity.this, "error to find name" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }//end if
            userName.setText(name);
            userEmail.setText(user.getEmail());
            Uri imageUri = user.getPhotoUrl();
            if(imageUri!=null)
            profileImg.setImageURI(imageUri);

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