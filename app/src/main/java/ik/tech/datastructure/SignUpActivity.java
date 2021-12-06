package ik.tech.datastructure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {
    private TextInputLayout username,email,password,rePassword;

    private FirebaseDatabase database;
    private DatabaseReference users;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        users=database.getReference("users");

        username =(TextInputLayout) findViewById(R.id.username);
        email =(TextInputLayout) findViewById(R.id.email);
        password =(TextInputLayout) findViewById(R.id.password);
        rePassword =(TextInputLayout) findViewById(R.id.rePassword);

    }

    public Boolean validateUsername(){
        String username= this.username.getEditText().getText().toString().trim();

        if(username.isEmpty()){
            this.username.setError("field cannot be empty");
            return false;
        }
        else{
            this.username.setError(null);
            return true;
        }
    }//end validateUsername

    public Boolean validateEmail(){
        String email= this.email.getEditText().getText().toString().trim();
        String emailPattern ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(email.isEmpty()){
            this.email.setError("field cannot be empty");
            return false;
        }
        else if(!email.matches(emailPattern)){
            this.email.setError("invalid email address");
            return false;
        }
        else{
            this.email.setError(null);
            return true;
        }
    }//end validateEmail

    public Boolean validatePassword(){
        String password = this.password.getEditText().getText().toString().trim();
        if(password.isEmpty()){
            this.password.setError("field cannot be empty");
            return false;
        }
        else if (password.length()<6){
            this.password.setError("password length can not be less then six character");
            return false;
        }
        else{
            this.password.setError(null);
            return true;
        }
    }//end validate Password

    public Boolean validateRePassword(){
        String rePassword= this.rePassword.getEditText().getText().toString().trim();
        String password= this.password.getEditText().getText().toString().trim();
        if(rePassword.isEmpty()){
            this.rePassword.setError("field cannot be empty");
            return false;
        }
        else if(!password.equals(rePassword)){
            this.rePassword.setError("The password can not be different");
            return false;
        }
        else{
            this.rePassword.setError(null);
            return true;
        }
    }//end validateRePassword



    public void signUp(View view) {
        if(!(validateEmail() & validatePassword() & validateRePassword() & validateUsername()))
            return;
         createUser();
    }//end signUp

    private void createUser() {
        String name= this.username.getEditText().getText().toString().trim();
        String email= this.email.getEditText().getText().toString().trim();
        String password= this.password.getEditText().getText().toString().trim();

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {
                    String id=task.getResult().getUser().getUid();
                    UserModel user = new UserModel(name, email, password,id);

                    users.child(id).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(SignUpActivity.this, "congratulations!\n your account has been created", Toast.LENGTH_SHORT).show();

                            Intent in = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(in);
                        }//end onSuccess
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpActivity.this, "I am sorry, there is error\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }//end onFailure
                    });
                }//end if
                else
                    Toast.makeText(SignUpActivity.this,"this email is existing\n"
                            +task.getException(),Toast.LENGTH_SHORT).show();
            }//end onComplete
        });

    }//end createUser

    public void login(View view) {
        Intent in = new Intent(this,LoginActivity.class);
        startActivity(in);
    }
}