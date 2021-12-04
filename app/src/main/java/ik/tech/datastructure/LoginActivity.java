package ik.tech.datastructure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void singIN(View view) {

    }//end signIn

    public void signUp(View view) {
        Intent in = new Intent(this,SignUpActivity.class);
        startActivity(in);
    }//end signUp

    public void forgetPassword(View view) {
    }//end forgetPassword


}