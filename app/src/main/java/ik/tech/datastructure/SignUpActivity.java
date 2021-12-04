package ik.tech.datastructure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {
    private TextInputLayout username,email,password,rePassword;

    private FirebaseDatabase database;
    private DatabaseReference users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        database = FirebaseDatabase.getInstance();

        users=database.getReference("users");

    }

    public Boolean validateUsername(){
        String username= this.username.getEditText().getText().toString().trim();
        String spaces = "(?=\\s+$)";
        if(username.isEmpty()){
            this.username.setError("field cannot be empty");
            return false;
        }
        else if(!username.matches(spaces)){
            this.username.setError("white spaces are not allowed");
            return false;
        }
//        else if(isUserAvailable(username)){
//            this.username.setError("The username is exist");
//            return false;
//        }
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
            this.username.setError(null);
            return true;
        }
    }//end validate Password

    public Boolean validateRePassword(){
        String rePassword= this.rePassword.getEditText().getText().toString().trim();
        String password= this.password.getEditText().getText().toString().trim();
        if(rePassword.isEmpty()){
            this.username.setError("field cannot be empty");
            return false;
        }
        else if(!password.equals(rePassword)){
            this.username.setError("The repeat password can not be different");
            return false;
        }
        else{
            this.username.setError(null);
            return true;
        }
    }//end validateRePassword

    boolean isUserAvailable;
//    public Boolean isUserAvailable(String uname){
//
//            this.users.child(uname).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if(snapshot.exists())
//                        isUserAvailable=true;
//                    else
//                        isUserAvailable =false;
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    Toast.makeText(SignUpActivity.this,"Database error \n"+error.getMessage(),Toast.LENGTH_SHORT).show();
//
//                }
//            });
//        return isUserAvailable;
//    }//end isUserAvailable




    public void signUp(View view) {
        if(!(validateEmail() && validatePassword() && validateRePassword() && validateUsername()))
            return;

         String username= this.username.getEditText().getText().toString().trim();
         String email= this.email.getEditText().getText().toString().trim();
         String password= this.password.getEditText().getText().toString().trim();


        UserModel user =new UserModel(username,email,password);

            this.users.child(username).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(SignUpActivity.this,"congratulations!\n your account has been created",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignUpActivity.this,"I am sorry, there is error\n"+e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });

    }//end signUp

    public void login(View view) {
        Intent in = new Intent(this,LoginActivity.class);
        startActivity(in);
    }
}