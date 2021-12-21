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

public class TreeActivity extends AppCompatActivity {
 Tree t;
    private Button ino,pre,post;
    private EditText data;
    private TextView code,treeData,algo;
    private Button algoTextBtn,codeTextBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);

        Intent in = getIntent();

       t=new Tree();

    }


//
//
//    public void insert(View view) {
//        try {
//            Toast.makeText(getApplicationContext(), "" + t.insert(Integer.parseInt(data.getText().toString())), Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(TreeActivity.this, "please enter data  \n" , Toast.LENGTH_SHORT).show();
//        } algo.setText(Html.fromHtml(getString((R.string.insertion_tree_algo))));
//        code.setText(Html.fromHtml(getString((R.string.insertion_tree_code))));
//        treeData.setText(t.display(1));
//        data.setText("");
//        //treeDrawing.draw();
//    }
//    public void delete(View view) {
//        algo.setText(Html.fromHtml(getString((R.string.deletion_tree_algo))));
//        code.setText(Html.fromHtml(getString((R.string.deletion_tree_code))));
//        try {
//
//        Toast.makeText(getApplicationContext(), ""+t.delete(Integer.parseInt(data.getText().toString())),Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(TreeActivity.this, "please enter data  \n" , Toast.LENGTH_SHORT).show();
//        }
//      //  treeDrawing.draw();
//        treeData.setText(t.display(1));
//        data.setText("");
//    }
//
//    public void search(View view) {
//        algo.setText(Html.fromHtml(getString((R.string.search_tree_algo))));
//        code.setText(Html.fromHtml(getString((R.string.search_tree_code))));
//        try {
//        Toast.makeText(getApplicationContext(), ""+t.search(Integer.parseInt(data.getText().toString())),Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(TreeActivity.this, "please enter data  \n" , Toast.LENGTH_SHORT).show();
//        }
//        data.setText("");
//    }
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
//
//
//    public void pre(View view) {
//        algo.setText(Html.fromHtml(getString((R.string.preorder_tree_algo))));
//        code.setText(Html.fromHtml(getString((R.string.preorder_tree_code))));
//        pre.setBackgroundColor(getResources().getColor(R.color.blackLight));
//        pre.setTextColor(getResources().getColor(R.color.white));
//        post.setBackgroundColor(getResources().getColor(R.color.white));
//        post.setTextColor(getResources().getColor(R.color.black));
//        ino.setBackgroundColor(getResources().getColor(R.color.white));
//        ino.setTextColor(getResources().getColor(R.color.black));
//        treeData.setText(t.display(2));
//    }
//
//    public void in(View view) {
//        algo.setText(Html.fromHtml(getString((R.string.inorder_tree_algo))));
//        code.setText(Html.fromHtml(getString((R.string.inorder_tree_code))));
//        ino.setBackgroundColor(getResources().getColor(R.color.blackLight));
//        ino.setTextColor(getResources().getColor(R.color.white));
//        pre.setBackgroundColor(getResources().getColor(R.color.white));
//        pre.setTextColor(getResources().getColor(R.color.black));
//        post.setBackgroundColor(getResources().getColor(R.color.white));
//        post.setTextColor(getResources().getColor(R.color.black));
//        treeData.setText(t.display(1));
//    }
//
//    public void post(View view) {
//        algo.setText(Html.fromHtml(getString((R.string.postorder_tree_algo))));
//        code.setText(Html.fromHtml(getString((R.string.postorder_tree_code))));
//        post.setBackgroundColor(getResources().getColor(R.color.blackLight));
//        post.setTextColor(getResources().getColor(R.color.white));
//        pre.setBackgroundColor(getResources().getColor(R.color.white));
//        pre.setTextColor(getResources().getColor(R.color.black));
//        ino.setBackgroundColor(getResources().getColor(R.color.white));
//        ino.setTextColor(getResources().getColor(R.color.black));
//        treeData.setText(t.display(3));
//    }

    public void insertHandler(View view) {
    }

    public void deleteHandler(View view) {
    }

    public void searchHandler(View view) {
    }

    public void enterDataHandler(View view) {
    }

    public void javaCodeHandler(View view) {
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