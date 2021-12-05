package ik.tech.datastructure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent in;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sign_in,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.login:
                in = new Intent(this, LoginActivity.class);
                startActivity(in);
                break;

            default:

        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void arrayActivity(View view) {
        in = new Intent(this,ArrayActivity.class);
        startActivity(in);
    }

    public void stackActivity(View view) {
        in = new Intent(this,StackActivity.class);
        startActivity(in);
    }

    public void queueActivity(View view) {
        in = new Intent(this,QueueActivity.class);
        startActivity(in);
    }

    public void linkedListActivity(View view) {
        in = new Intent(this,LinkedListActivity.class);
        startActivity(in);
    }


    public void treeActivity(View view) {
        in = new Intent(this,TreeActivity.class);
        startActivity(in);
    }

    public void graphActivity(View view) {
        in = new Intent(this,GraphActivity.class);
        startActivity(in);
    }
}