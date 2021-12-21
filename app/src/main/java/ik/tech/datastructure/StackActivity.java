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

public class StackActivity extends AppCompatActivity {
    Stack s;

    private EditText data;
    Button codeTextBtn,algoTextBtn;
    private TextView code,algo,stackData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);


        Intent in = getIntent();

        s =new Stack(10);

    }


//    public void push(View view) {
//        try {
//            Toast.makeText(getApplicationContext(), "" + s.push(Integer.parseInt(data.getText().toString())), Toast.LENGTH_SHORT).show();
//        }catch (Exception e){ Toast.makeText(getApplicationContext(), "please enter data\n"  , Toast.LENGTH_SHORT).show();}
//        algo.setText(Html.fromHtml(getString((R.string.push_algo))));
//        code.setText(Html.fromHtml(getString((R.string.push_code))));
//        stackData.setText(s.traverse());
//        data.setText("");
//    }
//    public void pop(View view) {
//        algo.setText(Html.fromHtml(getString((R.string.pop_algo))));
//        code.setText(Html.fromHtml(getString((R.string.pop_code))));
//        Toast.makeText(getApplicationContext(), ""+s.pop(),Toast.LENGTH_SHORT).show();
//        stackData.setText(s.traverse());
//    }
//
//    public void peak(View view) {
//        algo.setText(Html.fromHtml(getString((R.string.peak_algo))));
//        code.setText(Html.fromHtml(getString((R.string.peak_code))));
//        Toast.makeText(getApplicationContext(), ""+s.peak(),Toast.LENGTH_SHORT).show();
//    }
//
//
//
//    public void showCode(View view){
//        if(codeTextBtn.getText().equals("show code")) {
//
//            codeTextBtn.setText("hide code");
//            algoTextBtn.setText("show algorithm");
//            codeTextBtn.setBackgroundColor(getResources().getColor(R.color.blackLight));
//            codeTextBtn.setTextColor(getResources().getColor(R.color.white));
//
//            algoTextBtn.setBackgroundColor(getResources().getColor(R.color.white));
//            algoTextBtn.setTextColor(getResources().getColor(R.color.black));
//
//            algo.setVisibility(View.GONE);
//            code.setVisibility(View.VISIBLE);
//
//            return;
//        }
//
//        codeTextBtn.setBackgroundColor(getResources().getColor(R.color.white));
//        codeTextBtn.setText("show code");
//
//
//        codeTextBtn.setTextColor(getResources().getColor(R.color.black));
//
//        code.setVisibility(View.GONE);
//    }
//
//    public void showAlgo(View view){
//        if(algoTextBtn.getText().equals("show algorithm")) {
//
//            algoTextBtn.setText("hide algorithm");
//            codeTextBtn.setText("show code");
//
//            algoTextBtn.setBackgroundColor(getResources().getColor(R.color.blackLight));
//            algoTextBtn.setTextColor(getResources().getColor(R.color.white));
//
//            codeTextBtn.setBackgroundColor(getResources().getColor(R.color.white));
//            codeTextBtn.setTextColor(getResources().getColor(R.color.black));
//
//            code.setVisibility(View.GONE);
//            algo.setVisibility(View.VISIBLE);
//
//            return;
//        }
//
//        algoTextBtn.setBackgroundColor(getResources().getColor(R.color.white));
//        algoTextBtn.setText("show algorithm");
//
//
//        algoTextBtn.setTextColor(getResources().getColor(R.color.black));
//        algo.setVisibility(View.GONE);
//    }

    public void insertHandler(View view) {
    }

    public void deleteHandler(View view) {
    }

    public void getHandler(View view) {
    }

    public void javaCodeHandler(View view) {
    }

    public void enterDataHandler(View view) {
    }

    public void cCodeHandler(View view) {
    }

    public void pythonCodeHandler(View view) {
    }

    public void algoHandler(View view) {
    }

    public void updateCode(View view) {
    }
}