package ik.tech.datastructure;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ArrayActivity extends AppCompatActivity {


    private static final String TAG ="ik.Array Activity" ;
    private static FirebaseDatabase database;
    static {
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
    }
    private DatabaseReference codes;
    private DatabaseReference users;
    private DatabaseReference questions;
    private DatabaseReference answers;



    private FirebaseAuth auth;
    private FirebaseUser user;
    private GoogleSignInAccount account;

    private Array arr;
    private String codeName , language ;
    private final String ds = "array";
    private String codeId, code=".....";
    private String[] spinnerArray = null;
    private ArrayAdapter<String> spinnerArrayAdapter = null;
    private Spinner spinner;
    private EditText codeEt,dataEt, indexEt, questEt;
    private TextView codeTv, outputTv, insertTv, deleteTv, getTv, sortTv, searchTv;
    private Button enterBt, postBt, insertBt, deleteBt, searchBt, sortBt, javaBt, cBt, pythonBt, algoBt,editCodeBt;

    private RecyclerView questionRv;
    private QuestionsAdapter questAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array);


        Intent in = getIntent();

        codes = database.getReference("codes");
        codes.keepSynced(true);
        users = database.getReference("users");
        questions = database.getReference("questions");
        answers = database.getReference("answers");

        auth=FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        arr = new Array(10);
        codeName="";
        language="java";

        codeTv = (TextView) findViewById(R.id.codeTv);
        outputTv = (TextView) findViewById(R.id.outputTv);
        insertTv = (TextView) findViewById(R.id.insertTv);
        deleteTv = (TextView) findViewById(R.id.deleteTv);
        getTv = (TextView) findViewById(R.id.getTv);
        sortTv = (TextView) findViewById(R.id.sortTv);
        searchTv = (TextView) findViewById(R.id.searchTv);


        enterBt = (Button) findViewById(R.id.enterBt);
        postBt = (Button) findViewById(R.id.postBt);
        javaBt = (Button) findViewById(R.id.javaBt);
        cBt = (Button) findViewById(R.id.cBt);
        pythonBt = (Button) findViewById(R.id.pythonBt);
        algoBt = (Button) findViewById(R.id.algoBt);
        editCodeBt=(Button) findViewById(R.id.editCodeBt);
        codeEt = (EditText) findViewById(R.id.codeEt);
        dataEt = (EditText) findViewById(R.id.dataEt);
        indexEt = (EditText) findViewById(R.id.indexEt);
        questEt = (EditText) findViewById(R.id.questEt);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                codeName = spinner.getSelectedItem().toString();
                setCodeTvText();
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
                Toast.makeText(ArrayActivity.this, "nothing is selected", Toast.LENGTH_SHORT).show();
            }
        });



    }//end onCreate

    @Override
    protected void onStart() {
        super.onStart();
        //GoogleSignIn.getLastSignedInAccount(this);
        if(user!=null) {

            if (user.getEmail().equals("achakzai9820@gmail.com")) {
                Toast.makeText(this, "admin account", Toast.LENGTH_SHORT).show();

                editCodeBt.setVisibility(View.VISIBLE);
                codeEt.setVisibility(View.VISIBLE);
            }
            else {
                editCodeBt.setVisibility(View.GONE);
                codeEt.setVisibility(View.GONE);
            }
        }//end if
        else{
            editCodeBt.setVisibility(View.GONE);
            codeEt.setVisibility(View.GONE);
        }
        }//end onStart



    Integer index = null, data = null;

    public void enterDataHandler(View view) {
        try {
            this.data = Integer.parseInt(dataEt.getText().toString());
        } catch (Exception e) {
            Log.w(null, "please enter data in text filed");
            return;
        }
        try {
            this.index = Integer.parseInt(indexEt.getText().toString());
        } catch (Exception e) {
            Log.e(null, "please enter index in text filed");
            return;
        }

        switch (codeName) {
            case "insert at specific index":

                Toast.makeText(this, arr.insert(data, index), Toast.LENGTH_SHORT).show();
                break;

            case "insert at first":
                Toast.makeText(this, arr.insertFirst(data), Toast.LENGTH_SHORT).show();

                break;

            case "insert at last":
                Toast.makeText(this, arr.insertLast(data), Toast.LENGTH_SHORT).show();

                break;

            case "insert in sorted array":
                Toast.makeText(this, arr.insertInSorted(data), Toast.LENGTH_SHORT).show();

                break;

            case "delete from specific index":
                Toast.makeText(this, arr.deleteAt(index), Toast.LENGTH_SHORT).show();

                break;

            case "delete first":
                Toast.makeText(this, arr.deleteFirst(), Toast.LENGTH_SHORT).show();

                break;

            case "delete last":
                Toast.makeText(this, arr.deleteLast(), Toast.LENGTH_SHORT).show();

                break;
            case "delete specific item":
                Toast.makeText(this, arr.delete(data), Toast.LENGTH_SHORT).show();

                break;
            case "get data from specific index":
                Toast.makeText(this, arr.getAt(index), Toast.LENGTH_SHORT).show();

                break;
            case "linear search":
                Toast.makeText(this, arr.search(data), Toast.LENGTH_SHORT).show();

                break;
            case "binary search":
                Toast.makeText(this, arr.bSearch(data), Toast.LENGTH_SHORT).show();

                break;
            case "insertion sort":
                arr.insertionSort();
                Toast.makeText(this, "array is sorted with insertion sort", Toast.LENGTH_SHORT).show();

                break;
            case "selection sort":
                arr.selectionSort();
                Toast.makeText(this, "array is sorted with selection sort", Toast.LENGTH_SHORT).show();

                break;
            case "bubble sort":
                arr.bubbleSort();
                Toast.makeText(this, "array is sorted with bubble sort", Toast.LENGTH_SHORT).show();

                break;

            case "quick sort":
                arr.quickSort(0, arr.getNelement());
                Toast.makeText(this, "array is sorted with quick sort", Toast.LENGTH_SHORT).show();

                break;

            default:
                Toast.makeText(this, "this is error", Toast.LENGTH_SHORT).show();

        }//end switch
        this.outputTv.setText(arr.traverse());
    }//end enterDataHandler

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
        setSpinnerData(R.array.get_choice);
        setTextColor(getTv);
        enterBt.setText("get");
    }//end getHandler

    public void sortHandler(View view) {
        setSpinnerData(R.array.sort_choice);
        setTextColor(sortTv);
        enterBt.setText("sort");

    }//end sortHandler

    public void searchHandler(View view) {
        setSpinnerData(R.array.search_choice);
        setTextColor(searchTv);
        enterBt.setText("search");

    }//end searchHandler

    public void setTextColor(TextView tv) {
        insertTv.setTextColor(getResources().getColor(R.color.black));
        deleteTv.setTextColor(getResources().getColor(R.color.black));
        sortTv.setTextColor(getResources().getColor(R.color.black));
        getTv.setTextColor(getResources().getColor(R.color.black));
        searchTv.setTextColor(getResources().getColor(R.color.black));
        tv.setTextColor(getResources().getColor(R.color.blue));
    }//end setTextColor

    public void setSpinnerData(int choice) {
        spinnerArray = getResources().getStringArray(choice);
        spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spinner.setAdapter(spinnerArrayAdapter);
    }//end setSpinnerData


    public void javaCodeHandler(View view) {
        handleLangBt(this.javaBt, "java");


    }//end javaCodeHandler

    public void cCodeHandler(View view) {
        handleLangBt(this.cBt, "c++");

    }//end cCodeHandler

    public void pythonCodeHandler(View view) {
        handleLangBt(this.pythonBt, "python");
    }//end pythonCodeHandler

    public void algoHandler(View view) {
        handleLangBt(this.algoBt, "algo");
    }//end algoHandler

    public void handleLangBt(Button bt, String lang) {
        this.javaBt.setTextColor(getResources().getColor(R.color.black));
        this.cBt.setTextColor(getResources().getColor(R.color.black));
        this.pythonBt.setTextColor(getResources().getColor(R.color.black));
        this.algoBt.setTextColor(getResources().getColor(R.color.black));
        bt.setTextColor(getResources().getColor(R.color.blue));
        this.language = lang;
        setCodeTvText();

    }//end setTextColor


    public void updateCode(View view) {

        code = codeEt.getText().toString();

        CodeModel codeModel = new CodeModel(codeId,code);
        codes.child(codeId).setValue(codeModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                setCodeTvText();
                Log.e(TAG,"value is set to database ");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ArrayActivity.this,"value is not set to database",Toast.LENGTH_SHORT);
                Log.e(TAG,e.getMessage());
            }
        });



    }//end updateCode
    public void setRecycler(){
        questionRv = (RecyclerView) findViewById(R.id.questionRv);
        questAdapter = new QuestionsAdapter(this,user.getUid(),codeId);

        questionRv.setAdapter(questAdapter);
        questionRv.setLayoutManager(new LinearLayoutManager(this));

    }//end setRecycler
    public void setCodeTvText(){
        String codeNameWithoutSpace= this.codeName.replaceAll("\\s","");
        codeId=(this.language+ this.ds + codeNameWithoutSpace).trim();
//        setRecycler();
        codes.child(codeId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    String codeFromDb=snapshot.child("code").getValue(String.class);
                    codeTv.setText(codeFromDb);
                    codeEt.setText(codeFromDb);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ArrayActivity.this,"no such code exist "+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }//end setCodeTvText

    public  String getTime(){
        String postedTime=null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime dt = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            postedTime = dt.format(format);
        }

        return postedTime;
    }

    public void postQuestHandler(View view) {
        if(user == null) {
            Toast.makeText(this, "you should logged in for asking questions", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            return;
        }
        String question = questEt.getText().toString();
        if(question.isEmpty()){
            Toast.makeText(this, "Question field can not be empty ", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(ArrayActivity.this,"Question is submitting please wait",Toast.LENGTH_SHORT).show();


        String questId = questions.push().getKey();

        QuestionModel questionData=new QuestionModel(questId,question,codeId,user.getUid(),getTime());
        questions.child(questId).setValue(questionData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ArrayActivity.this,"Question is submitted",Toast.LENGTH_SHORT).show();
                Log.e(TAG,"value is set to database ");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ArrayActivity.this,"value is not set to database",Toast.LENGTH_SHORT).show();
                Log.e(TAG,e.getMessage());
            }
        });

    }//end postQuestHandler

    public void setQuestions(){

    }

    public void showData() {
        this.dataEt.setVisibility(View.VISIBLE);
        this.indexEt.setVisibility(View.GONE);
    }

    public void showIndex() {
        this.dataEt.setVisibility(View.GONE);
        this.indexEt.setVisibility(View.VISIBLE);

    }

    public void showDataIndex() {
        this.dataEt.setVisibility(View.VISIBLE);
        this.indexEt.setVisibility(View.VISIBLE);
    }

    public void hideDataIndex() {
        this.dataEt.setVisibility(View.GONE);
        this.indexEt.setVisibility(View.GONE);
    }


}//end ArrayActivity

