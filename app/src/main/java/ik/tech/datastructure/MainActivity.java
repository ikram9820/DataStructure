package ik.tech.datastructure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ik.tech.datastructure.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void arrayActivity(View view) {
        Intent in = new Intent(this,ArrayActivity.class);
        startActivity(in);
    }

    public void stackActivity(View view) {
        Intent in = new Intent(this,StackActivity.class);
        startActivity(in);
    }

    public void queueActivity(View view) {
        Intent in = new Intent(this,QueueActivity.class);
        startActivity(in);
    }

    public void linkedListActivity(View view) {
        Intent in = new Intent(this,LinkedListActivity.class);
        startActivity(in);
    }


    public void treeActivity(View view) {
        Intent in = new Intent(this,TreeActivity.class);
        startActivity(in);
    }

    public void graphActivity(View view) {
        Intent in = new Intent(this,GraphActivity.class);
        startActivity(in);
    }
}