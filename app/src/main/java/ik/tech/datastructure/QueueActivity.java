package ik.tech.datastructure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ik.tech.datastructure.R;

public class QueueActivity extends AppCompatActivity {
    Queue q;
    private EditText data;
    Button codeTextBtn,algoTextBtn;
    private TextView code,algo,qData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);


        Intent in = getIntent();


        q= new Queue(10);

        data = (EditText) findViewById(R.id.det);
        codeTextBtn = (Button)findViewById(R.id.codeTextBtn);
        algoTextBtn = (Button)findViewById(R.id.algoTextBtn);
        algo = (TextView)findViewById(R.id.algotext) ;
        code=(TextView)findViewById(R.id.codetext1);
        qData=(TextView)findViewById(R.id.datatext);


    }

    public void enQ(View view) {
        try {
            Toast.makeText(getApplicationContext(), "" + q.enqueue(Integer.parseInt(data.getText().toString())), Toast.LENGTH_SHORT).show();
        }catch (Exception e){ Toast.makeText(getApplicationContext(), "please enter data" , Toast.LENGTH_SHORT).show();}
        algo.setText(Html.fromHtml(getString((R.string.enq_algo))));
        code.setText(Html.fromHtml(getString((R.string.enq_code))));
        qData.setText(q.traverse());
        data.setText("");
    }
    public void deQ(View view) {
        algo.setText(Html.fromHtml(getString((R.string.deq_algo))));
        code.setText(Html.fromHtml(getString((R.string.deq_code))));
        Toast.makeText(getApplicationContext(), ""+q.dequeue(),Toast.LENGTH_SHORT).show();
        qData.setText(q.traverse());
    }

    public void front(View view) {
        algo.setText(Html.fromHtml(getString((R.string.front_algo))));
        code.setText(Html.fromHtml(getString((R.string.front_code))));
        Toast.makeText(getApplicationContext(), ""+q.front(),Toast.LENGTH_SHORT).show();
    }


    public void showCode(View view){
        if(codeTextBtn.getText().equals("show code")) {

            codeTextBtn.setText("hide code");
            algoTextBtn.setText("show algorithm");
            codeTextBtn.setBackgroundColor(getResources().getColor(R.color.blackLight));
            codeTextBtn.setTextColor(getResources().getColor(R.color.white));

            algoTextBtn.setBackgroundColor(getResources().getColor(R.color.white));
            algoTextBtn.setTextColor(getResources().getColor(R.color.black));

            algo.setVisibility(View.GONE);
            code.setVisibility(View.VISIBLE);

            return;
        }

        codeTextBtn.setBackgroundColor(getResources().getColor(R.color.white));
        codeTextBtn.setText("show code");


        codeTextBtn.setTextColor(getResources().getColor(R.color.black));

        code.setVisibility(View.GONE);
    }

    public void showAlgo(View view){
        if(algoTextBtn.getText().equals("show algorithm")) {

            algoTextBtn.setText("hide algorithm");
            codeTextBtn.setText("show code");

            algoTextBtn.setBackgroundColor(getResources().getColor(R.color.blackLight));
            algoTextBtn.setTextColor(getResources().getColor(R.color.white));

            codeTextBtn.setBackgroundColor(getResources().getColor(R.color.white));
            codeTextBtn.setTextColor(getResources().getColor(R.color.black));

            code.setVisibility(View.GONE);
            algo.setVisibility(View.VISIBLE);

            return;
        }

        algoTextBtn.setBackgroundColor(getResources().getColor(R.color.white));
        algoTextBtn.setText("show algorithm");


        algoTextBtn.setTextColor(getResources().getColor(R.color.black));
        algo.setVisibility(View.GONE);
    }

}