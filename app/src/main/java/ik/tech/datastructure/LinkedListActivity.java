package ik.tech.datastructure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import ik.tech.datastructure.R;


public class LinkedListActivity extends AppCompatActivity {


    LinkList l;
    private Spinner delSpin,insSpin;
    private EditText data,index;
    private TextView code,linkedListData,algo;
    private Button algoTextBtn,codeTextBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked_list);

        Intent in = getIntent();

        l= new LinkList();
        data=(EditText)findViewById(R.id.dataet);
        data.setVisibility(View.VISIBLE);

        index=(EditText)findViewById(R.id.indexet);
        algoTextBtn = (Button)findViewById(R.id.algoTextBtn);
        codeTextBtn=(Button)findViewById(R.id.codeTextBtn);
        delSpin= (Spinner)findViewById(R.id.ads);
        insSpin= (Spinner)findViewById(R.id.ais);


        code=(TextView)findViewById(R.id.acodetext);
        algo = (TextView)findViewById(R.id.aAlgoetext);
        linkedListData=(TextView)findViewById(R.id.adatatext);

        spinner();
    }


    public void spinner(){



        delSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ch=delSpin.getSelectedItem().toString();

                if (ch.equals("delete from specific index")) {
                    data.setVisibility(View.GONE);
                    index.setVisibility(View.VISIBLE);
                }
                else if(ch.equals("delete specific item")){
                    index.setVisibility(View.GONE);
                    data.setVisibility(View.VISIBLE);
                }
             
                else{
                    index.setVisibility(View.GONE);
                    data.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(LinkedListActivity.this,insSpin.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                data.setVisibility(View.VISIBLE);
            }
        });

        insSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ch=insSpin.getSelectedItem().toString();

                if (ch.equals("insert at specific index")) {
                    data.setVisibility(View.VISIBLE);
                    index.setVisibility(View.VISIBLE);
                } else{
                    index.setVisibility(View.GONE);
                    data.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(LinkedListActivity.this,insSpin.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }//end spinner()

    public void showData(){
        data.setVisibility(View.VISIBLE);
        index.setVisibility(View.GONE);
    }
    public void showIndex(){
        data.setVisibility(View.GONE);
        index.setVisibility(View.VISIBLE);
    }

    public  void showDataIndex(){
        data.setVisibility(View.VISIBLE);
        index.setVisibility(View.VISIBLE);
    }

    public void hideDataIndex(){
        data.setVisibility(View.GONE);
        index.setVisibility(View.GONE);
    }



    public void insertion(View view) {

        String ch = insSpin.getSelectedItem().toString();
        if(ch.equals("insert at specific index")) {
            code.setText(Html.fromHtml(getString((R.string.insertion_list_code))));
            algo.setText(Html.fromHtml(getString((R.string.insertion_list_algo))));
            try {
                Toast.makeText(LinkedListActivity.this,  l.insertAt( Integer.parseInt(index.getText().toString()),Integer.parseInt(data.getText().toString())),
                        Toast.LENGTH_SHORT).show();;
            } catch (Exception e) {
                showDataIndex();
                Toast.makeText(LinkedListActivity.this, "please enter data and index  \n" , Toast.LENGTH_SHORT).show();
            }

        }
        else if(ch.equals("insert at first")) {
            code.setText(Html.fromHtml(getString((R.string.insertfirst_list_code))));
            algo.setText(Html.fromHtml(getString((R.string.insertfirst_list_algo))));
         try{
            Toast.makeText(LinkedListActivity.this,  l.insertFirst(Integer.parseInt(data.getText().toString())), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            showData();
                Toast.makeText(LinkedListActivity.this, "please enter data  \n" , Toast.LENGTH_SHORT).show();
            }
        }
        else if(ch.equals("insert at last")) {

            code.setText(Html.fromHtml(getString((R.string.insertlast_list_code))));
            algo.setText(Html.fromHtml(getString((R.string.insertlast_list_algo))));
            try{
            Toast.makeText(LinkedListActivity.this, l.insertLast(Integer.parseInt(data.getText().toString())), Toast.LENGTH_SHORT).show();
             } catch (Exception e) {
               showData();
                Toast.makeText(LinkedListActivity.this, "please enter data   \n" , Toast.LENGTH_SHORT).show();
            }
        }
        else if(ch.equals("insert in sorted list")) {

            code.setText(Html.fromHtml(getString((R.string.insert_in_list_code))));
            algo.setText(Html.fromHtml(getString((R.string.insert_in_list_algo))));
            l.insertionSort();
            try{
            Toast.makeText(LinkedListActivity.this,  l.insertIn(Integer.parseInt(data.getText().toString())), Toast.LENGTH_SHORT).show();
      } catch (Exception e) {

                showData();
                Toast.makeText(LinkedListActivity.this, "please enter data  \n" , Toast.LENGTH_SHORT).show();
            }
        }
        else
            Toast.makeText(LinkedListActivity.this,"\""+ch+"\"", Toast.LENGTH_SHORT).show();

        linkedListData.setText(l.traverseForward());
        data.setText("");
        index.setText("");
    }//end arrayInsertion()

    public void deletion(View view) {

        String ch = delSpin.getSelectedItem().toString();
        if(ch.equals("delete from specific index")) {

            code.setText(Html.fromHtml(getString((R.string.delete_at_list_code))));
            algo.setText(Html.fromHtml(getString((R.string.delete_at_list_algo))));
            try {
                Toast.makeText(LinkedListActivity.this,  l.deleteAt(Integer.parseInt(index.getText().toString())),
                        Toast.LENGTH_SHORT).show();;
            } catch (Exception e) {
               showIndex();
                Toast.makeText(LinkedListActivity.this, "please enter index  \n"  .toString(), Toast.LENGTH_SHORT).show();
            }

        }
        else if(ch.equals("delete first")) {

            code.setText(Html.fromHtml(getString((R.string.deletefirst_list_code))));
            algo.setText(Html.fromHtml(getString((R.string.deletefirst_list_algo))));
            Toast.makeText(LinkedListActivity.this,  l.deleteFirst(), Toast.LENGTH_SHORT).show();
            hideDataIndex();

        }
        else if(ch.equals("delete last")) {

            code.setText(Html.fromHtml(getString((R.string.deletelast_list_code))));
            algo.setText(Html.fromHtml(getString((R.string.deletelast_list_algo))));
            Toast.makeText(LinkedListActivity.this, l.deleteLast(), Toast.LENGTH_SHORT).show();
            hideDataIndex();
        }
        else if(ch.equals("delete specific item")) {

            code.setText(Html.fromHtml(getString((R.string.deletion_list_code))));
            algo.setText(Html.fromHtml(getString((R.string.deletion_list_algo))));
            try{
            Toast.makeText(LinkedListActivity.this,  l.delete(Integer.parseInt(data.getText().toString())), Toast.LENGTH_SHORT).show();
             } catch (Exception e) {
                data.setVisibility(View.VISIBLE);
                index.setVisibility(View.GONE);
                Toast.makeText(LinkedListActivity.this, "please enter data  \n"  .toString(), Toast.LENGTH_SHORT).show();
            }
        }
        else
            Toast.makeText(LinkedListActivity.this,"\""+ch+"\"", Toast.LENGTH_SHORT).show();

        linkedListData.setText(l.traverseForward());
        data.setText("");
        index.setText("");
    }

    public void getAt(View view) {
        algo.setText(Html.fromHtml(getString((R.string.get_at_list_algo))));
        code.setText(Html.fromHtml(getString((R.string.get_at_list_code))));
        if(index.getVisibility()!=view.VISIBLE){
           showIndex();
        }
        else {
            try {
                Toast.makeText(LinkedListActivity.this,  l.getAt(Integer.parseInt(index.getText().toString())).data+" is at "+index.getText().toString()+"th index",
                        Toast.LENGTH_SHORT).show();
            } catch (Exception e) {

                Toast.makeText(LinkedListActivity.this, "please enter index\n"  , Toast.LENGTH_SHORT).show();
            }

            index.setText("");
        }
    }//end arrayGetAt()

    public void sort(View view) {


            algo.setText(Html.fromHtml(getString((R.string.sort_list_algo))));
            code.setText(Html.fromHtml(getString((R.string.sort_list_code))));
            l.insertionSort();
            Toast.makeText(LinkedListActivity.this, "array is sorted by insertion sort", Toast.LENGTH_SHORT).show();

        linkedListData.setText(l.traverseForward());

    }//end arraySort()

    public void search(View view) {

            algo.setText(Html.fromHtml(getString((R.string.search_list_algo))));
            code.setText(Html.fromHtml(getString((R.string.search_list_code))));
            try{
            Toast.makeText(LinkedListActivity.this, l.search(Integer.parseInt(data.getText().toString())).data+" is found in list", Toast.LENGTH_SHORT).show();
    } catch (Exception e) {
               showData();
        Toast.makeText(LinkedListActivity.this, "please enter data  \n"  .toString(), Toast.LENGTH_SHORT).show();
    }
        data.setText("");
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