package ik.tech.datastructure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;


import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ik.tech.datastructure.R;

public class GraphActivity extends AppCompatActivity {
    Graph g;
    DiGraph diG;
    private EditText vert,u,v;
    private EditText vert1,u1,v1;
    private Button showUndiGraph,showDiGraph;
    private LinearLayout llOutputUnDiG,llOutputDiG;
    private TextView code,code1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Intent in = getIntent();

        g=new Graph();
        diG= new DiGraph();

    }

//
//public void clearEditText(){
//        vert.setText("");
//        v.setText("");
//        u.setText("");
//}
//
//
//    public void clearEditText1(){
//        vert1.setText("");
//        v1.setText("");
//        u1.setText("");
//    }
//    public void addVertex(View view) {
//
//        try {
//            Toast.makeText(getApplicationContext(),g.addVertex(Integer.parseInt(vert.getText().toString())), Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "please enter data   \n" , Toast.LENGTH_SHORT).show();
//        }
//        display(1);
//        clearEditText();
//    }
//
//    public void addEdge(View view) {
//        try {
//            Toast.makeText(getApplicationContext(),g.addEdge(Integer.parseInt(u.getText().toString()),(Integer.parseInt(v.getText().toString()))), Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "please enter data   \n", Toast.LENGTH_SHORT).show();
//        }
//        clearEditText();
//        display(1);
//    }
//
//    public void delVertex(View view) {
//
//        try {
//            Toast.makeText(getApplicationContext(),g.delVert(Integer.parseInt(vert.getText().toString())), Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "please enter data   \n", Toast.LENGTH_SHORT).show();
//        }
//        display(1);
//        clearEditText();
//    }
//
//    public void delEdge(View view) {
//        try {
//            Toast.makeText(getApplicationContext(),g.delEdge((Integer.parseInt(u.getText().toString())),(Integer.parseInt(v.getText().toString()))), Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "please enter data   \n", Toast.LENGTH_SHORT).show();
//        }
//        clearEditText();
//        display(1);
//    }
//    public void showCode(View view){
//        if(showUndiGraph.getText().equals("show code")) {
//            showUndiGraph.setBackgroundColor(getResources().getColor(R.color.blackLight));
//            showUndiGraph.setText("hide code");
//            showUndiGraph.setTextColor(getResources().getColor(R.color.white));
//            code.setText(Html.fromHtml(getString(R.string.graph_code)));
//            code.setVisibility(View.VISIBLE);
//            return;
//        }
//
//        showUndiGraph.setBackgroundColor(getResources().getColor(R.color.white));
//        showUndiGraph.setText("show code");
//        showUndiGraph.setTextColor(getResources().getColor(R.color.black));
//        code.setText(".....");
//        code.setVisibility(View.GONE);
//    }
//
//
//    public void addVertex1(View view) {
//        try {
//            Toast.makeText(getApplicationContext(),diG.addVert(Integer.parseInt(vert1.getText().toString())), Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "please enter data   \n" , Toast.LENGTH_SHORT).show();
//        }
//        display(2);
//        clearEditText1();
//    }
//
//    public void addEdge1(View view) {
//        try {
//            Toast.makeText(getApplicationContext(),diG.addEdge(Integer.parseInt(u1.getText().toString()),(Integer.parseInt(v1.getText().toString()))), Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "please enter data   \n", Toast.LENGTH_SHORT).show();
//        }
//        clearEditText1();
//        display(2);
//    }
//
//    public void delVertex1(View view) {
//
//        try {
//            Toast.makeText(getApplicationContext(),diG.delVert(Integer.parseInt(vert1.getText().toString())), Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "please enter data   \n" , Toast.LENGTH_SHORT).show();
//        }
//        display(2);
//        clearEditText1();
//    }
//
//    public void delEdge1(View view) {
//        try {
//            Toast.makeText(getApplicationContext(),diG.delEdge((Integer.parseInt(u1.getText().toString())),(Integer.parseInt(v1.getText().toString()))), Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "please enter data   \n" , Toast.LENGTH_SHORT).show();
//        }
//        clearEditText1();
//        display(2);
//    }
//    public void showCode1(View view){
//        if(showDiGraph.getText().equals("show code")) {
//            showDiGraph.setBackgroundColor(getResources().getColor(R.color.blackLight));
//            showDiGraph.setText("hide code");
//            showDiGraph.setTextColor(getResources().getColor(R.color.white));
//            code1.setText(Html.fromHtml(getString(R.string.di_graph_code)));
//            code1.setVisibility(View.VISIBLE);
//            return;
//        }
//
//            showDiGraph.setBackgroundColor(getResources().getColor(R.color.white));
//            showDiGraph.setText("show code");
//            showDiGraph.setTextColor(getResources().getColor(R.color.black));
//            code1.setText(".....");
//        code1.setVisibility(View.GONE);
//    }
//
//    public  void display(int flag){
//
//        if(flag==1) {
//            llOutputUnDiG.removeAllViews();
//            for (int i = 0; i < g.vertices.size(); i++) {
//                String text = "";
//
//                TextView t = new TextView(getApplicationContext());
//                text = g.vertices.get(i).get(0).data + " : ";
//                for (int j = 1; j < g.vertices.get(i).size(); j++)
//                    text += g.vertices.get(i).get(j).data + ", ";
//                t.setBackgroundColor(getResources().getColor(R.color.blackLight));
//                t.setTextColor(getResources().getColor(R.color.green));
//                t.setTextSize(15);
//                t.setText(text);
//                llOutputUnDiG.addView(t);
//            }
//        }
//
//
//        if(flag==2) {
//            llOutputDiG.removeAllViews();
//            String text = "";
//            TextView t0 = new TextView(getApplicationContext());
//            t0.setBackgroundColor(getResources().getColor(R.color.blackLight));
//            t0.setTextColor(getResources().getColor(R.color.green));
//            text=String.format("%1$10s","");;
//            for(int j=0;j< diG.vertices.size();j++)
//                text+= String.format("%1$10s",diG.vertices.get(j).data);
//            t0.setText(text);
//            llOutputDiG.addView(t0);
//            for (int i = 0; i < diG.vertices.size(); i++) {
//                TextView t = new TextView(getApplicationContext());
//
//                text =String.format("%1$10s", diG.vertices.get(i).data) ;
//                for (int j = 0; j < diG.vertices.size(); j++)
//                    text += String.format("%1$11s",diG.adjacencyMat[i][j]);
//
//text+="\n";
//
//                t.setBackgroundColor(getResources().getColor(R.color.blackLight));
//                t.setTextColor(getResources().getColor(R.color.green));
//                t.setText(text);
//                llOutputDiG.addView(t);
//            }
//        }
//
//    }

    public void insertHandler(View view) {
    }

    public void deleteHandler(View view) {
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

    public void enterDataHandler(View view) {
    }
}