package ik.tech.datastructure;

import androidx.annotation.ArrayRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class ArrayActivity extends AppCompatActivity {

    Array arr;

    private Spinner spinner;
    private EditText dataEt,indexEt,questEt;
    private TextView codeTv,outputTv,insertTv,deleteTv,getTv,sortTv,searchTv;
    private Button enterBt,postBt,insertBt,deleteBt,searchBt,sortBt,javaBt,cBt,pythonBt,algoBt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array);

        Intent in = getIntent();

        arr=new Array(10);

        codeTv=(TextView)findViewById(R.id.codeTv);
        outputTv = (TextView)findViewById(R.id.outputTv);
        insertTv = (TextView)findViewById(R.id.insertTv);
        deleteTv = (TextView)findViewById(R.id.deleteTv);
        getTv = (TextView)findViewById(R.id.getTv);
        sortTv = (TextView)findViewById(R.id.sortTv);
        searchTv = (TextView)findViewById(R.id.searchTv);


        enterBt = (Button)findViewById(R.id.enterBt);
        postBt=(Button)findViewById(R.id.postBt);
        javaBt= (Button) findViewById(R.id.javaBt);
        cBt= (Button) findViewById(R.id.cBt);
        pythonBt= (Button) findViewById(R.id.pythonBt);
        algoBt= (Button) findViewById(R.id.algoBt);


        dataEt=(EditText)findViewById(R.id.dataEt);
        indexEt=(EditText)findViewById(R.id.indexEt);
        questEt=(EditText)findViewById(R.id.questEt);

        spinner= (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                codeName = spinner.getSelectedItem().toString();

                switch (codeName) {
                    case "insert at specific index":
                        showDataIndex();
                        break;

                    case "insert at first":
                        showData();
                        break;

                    case "insert at last":
                        showData();
                        break;

                    case "iinsert in sorted array":
                        showData();
                        break;

                    case "delete from specific index":
                        showIndex();
                        break;

                    case "delete first":
                        hideDataIndex();
                        break;

                    case "delete last":
                        hideDataIndex();
                        break;
                    case "delete specific item":
                        showData();
                        break;
                    case "get data from specific index":
                        showIndex();
                        break;
                    case "linear search":
                        showData();
                        break;
                    case "binary search":
                        showData();
                        break;
                    case "insertion sort":
                        hideDataIndex();
                        break;
                    case "selection sort":
                        hideDataIndex();
                        break;
                    case "bubble sort":
                        hideDataIndex();
                        break;

                    case "quick sort":
                        hideDataIndex();
                        break;

                    default:
                        showData();
                }//end switch
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ArrayActivity.this,"nothing is selected",Toast.LENGTH_SHORT).show();
            }
        });



    }//end onCreate




Integer index=null,data=null;
    public void enterDataHandler(View view) {
        data = Integer.parseInt(dataEt.getText().toString());
        index = Integer.parseInt(indexEt.getText().toString());
        Toast.makeText(this, "data inserted with "+codeName,Toast.LENGTH_SHORT).show();
      if(data ==null && index ==null) {
          Toast.makeText(this,"input fields are not filled",Toast.LENGTH_SHORT).show();
      }
          switch (codeName) {
              case "insert at specific index":

                  Toast.makeText(this,arr.insert(data, index),Toast.LENGTH_SHORT).show();
                  break;

              case "insert at first":
                  Toast.makeText(this,arr.insertFirst(data),Toast.LENGTH_SHORT).show();

                  break;

              case "insert at last":
                  Toast.makeText(this,arr.insertLast(data),Toast.LENGTH_SHORT).show();

                  break;

              case "insert in sorted array":
                  Toast.makeText(this,arr.insertInSorted(data),Toast.LENGTH_SHORT).show();

                  break;

              case "delete from specific index":
                  Toast.makeText(this,arr.deleteAt(index),Toast.LENGTH_SHORT).show();

                  break;

              case "delete first":
                  Toast.makeText(this,arr.deleteFirst(),Toast.LENGTH_SHORT).show();

                  break;

              case "delete last":
                  Toast.makeText(this,arr.deleteLast(),Toast.LENGTH_SHORT).show();

                  break;
              case "delete specific item":
                  Toast.makeText(this,arr.delete(data),Toast.LENGTH_SHORT).show();

                  break;
              case "get data from specific index":
                  Toast.makeText(this,arr.getAt(index),Toast.LENGTH_SHORT).show();

                  break;
              case "linear search":
                  Toast.makeText(this,arr.search(data),Toast.LENGTH_SHORT).show();

                  break;
              case "binary search":
                  Toast.makeText(this,arr.bSearch(data),Toast.LENGTH_SHORT).show();

                  break;
              case "insertion sort":
                  arr.insertionSort();
                  Toast.makeText(this,"array is sorted with insertion sort",Toast.LENGTH_SHORT).show();

                  break;
              case "selection sort":
                  arr.selectionSort();
                  Toast.makeText(this,"array is sorted with selection sort",Toast.LENGTH_SHORT).show();

                  break;
              case "bubble sort":
                  arr.bubbleSort();
                  Toast.makeText(this,"array is sorted with bubble sort",Toast.LENGTH_SHORT).show();

                  break;

              case "quick sort":
                  arr.quickSort(0, arr.getNelement());
                  Toast.makeText(this,"array is sorted with quick sort",Toast.LENGTH_SHORT).show();

                  break;

              default:
                  Toast.makeText(this,"this is error",Toast.LENGTH_SHORT).show();

          }//end switch
      outputTv.setText(arr.traverse());
    }//end enterDataHandler


    private String codeName="", language="java";
    private final String ds="array";
    String [] spinnerArray= null;
    ArrayAdapter<String> spinnerArrayAdapter=null;

    public void insertHandler(View view) {
        setSpinnerData(R.array.insertion_choice_array);
        setTextColor(insertTv);
        enterBt.setText("insert");
    }//end insertHandler

    public void deleteHandler(View view) {
        setSpinnerData(R.array.deletion_choice);
        setTextColor(deleteTv);
        enterBt.setText("delete");

    }//end deleteHandler

    public void getHandler(View view) {
//        spinner.setVisibility(View.GONE);
        setSpinnerData(R.array.get_choice);
        setTextColor(getTv);
        enterBt.setText("get");
        showIndex();


    }//end getHandler

    public void sortHandler(View view) {
        setSpinnerData(R.array.sort_choice);
        setTextColor(sortTv);
        enterBt.setText("sort");
        hideDataIndex();
    }//end sortHandler

    public void searchHandler(View view) {
        setSpinnerData(R.array.search_choice);
        setTextColor(searchTv);
        enterBt.setText("search");
        showData();
    }//end searchHandler

    public void setTextColor(TextView tv){
        insertTv.setTextColor(getResources().getColor(R.color.black));
        deleteTv.setTextColor(getResources().getColor(R.color.black));
        sortTv.setTextColor(getResources().getColor(R.color.black));
        getTv.setTextColor(getResources().getColor(R.color.black));
        searchTv.setTextColor(getResources().getColor(R.color.black));
        tv.setTextColor(getResources().getColor(R.color.blue));

    }//end setTextColor

    public void setSpinnerData(int choice){
        spinnerArray = getResources().getStringArray(choice);
        spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,spinnerArray);
        spinner.setAdapter(spinnerArrayAdapter);
    }//end setSpinnerData




    public void javaCodeHandler(View view) {

    }//end javaCodeHandler

    public void cCodeHandler(View view) {

    }//end cCodeHandler

    public void pythonCodeHandler(View view) {

    }//end pythonCodeHandler

    public void algoHandler(View view) {

    }//end algoHandler



    public void postQuestHandler(View view) {

    }//end postQuestHandler




    public void showData(){
        dataEt.setVisibility(View.VISIBLE);
        indexEt.setVisibility(View.GONE);
    }
    public void showIndex(){
        dataEt.setVisibility(View.GONE);
        indexEt.setVisibility(View.VISIBLE);

    }
    public  void showDataIndex(){
        dataEt.setVisibility(View.VISIBLE);
        indexEt.setVisibility(View.VISIBLE);
    }
    public void hideDataIndex(){
        dataEt.setVisibility(View.GONE);
        indexEt.setVisibility(View.GONE);
    }




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


}