package ik.tech.datastructure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 4321;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth auth;
  //  private TextInputLayout signInEmail,signInPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth= FirebaseAuth.getInstance();
    //    signInEmail = (TextInputLayout) findViewById(R.id.signInEmail);
     //   signInPassword= (TextInputLayout) findViewById(R.id.signInPassword);
        createRequest();


    }//end onCreate

    public  void createRequest(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

    }//end createRequest


    public void signInWithGoogle(View view) {
        Intent gsIntent = mGoogleSignInClient.getSignInIntent();
        startActivityIfNeeded(gsIntent,RC_SIGN_IN);
    }//end signInWithGoogle

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            }catch (ApiException e){
                Toast.makeText(this, "error\n" +e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }//end onActivityResult

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=auth.getCurrentUser();
        if(user!=null)
            startActivity(new Intent(LoginActivity.this,ProfileActivity.class));

    }//end onStart

    public void firebaseAuthWithGoogle(GoogleSignInAccount account){
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "you are Logged In", Toast.LENGTH_SHORT).show();
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = auth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));

//                          updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                              Toast.makeText(LoginActivity.this, "sorry there is error\n" +
                                task.getResult(), Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }//end firebaseAuthWithGoogle

//    public Boolean validateEmail(){
//        String email= this.signInEmail.getEditText().getText().toString().trim();
//        String emailPattern ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//        if(email.isEmpty()){
//            this.signInEmail.setError("field cannot be empty");
//            return false;
//        }
//        else if(!email.matches(emailPattern)){
//            this.signInEmail.setError("invalid email address");
//            return false;
//        }
//        else{
//            this.signInEmail.setError(null);
//            return true;
//        }
//    }//end validateEmail
//
//    public Boolean validatePassword(){
//        String password = this.signInPassword.getEditText().getText().toString().trim();
//        if(password.isEmpty()){
//            this.signInPassword.setError("field cannot be empty");
//            return false;
//        }
//        else if (password.length()<6){
//            this.signInPassword.setError("password length can not be less then six character");
//            return false;
//        }
//        else{
//            this.signInPassword.setError(null);
//            return true;
//        }
//    }//end validate Password
//
//    public void singIN(View view) {
//        if(!(validateEmail() & validatePassword()))
//            return;
//
//        login();
//
//    }//end signIn
//
//    public void login (){
//
//        String email= this.signInEmail.getEditText().getText().toString().trim();
//        String password = this.signInPassword.getEditText().getText().toString().trim();
//
//        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//
//                    Toast.makeText(LoginActivity.this, "you are Logged In", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
//                }
//                else
//                    Toast.makeText(LoginActivity.this, "sorry there is error\n" +
//                            task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }//end login
//
//    public void signUp(View view) {
//        Intent in = new Intent(this,SignUpActivity.class);
//        startActivity(in);
//    }//end signUp

}