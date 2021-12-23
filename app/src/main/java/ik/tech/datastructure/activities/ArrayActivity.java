package ik.tech.datastructure.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ik.tech.datastructure.R;
import ik.tech.datastructure.models.CodeModel;



public class ArrayActivity extends AppCompatActivity {


    private static final String TAG ="Array Activity" ;
    private static FirebaseDatabase database;
    static {
        try {
            database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
        }catch (Exception e){
            Log.w(TAG,e.getMessage());
        }
    }
    private DatabaseReference codes;




    private FirebaseAuth auth;
    private FirebaseUser user;



    private String codeName , language ;
    private final String ds = "array";
    private String codeId, code="code is not available";
    private String[] spinnerArray = null;
    private ArrayAdapter<String> spinnerArrayAdapter = null;
    private Spinner spinner;
    private EditText codeEt;
    private TextView codeTv, insertTv, deleteTv, sortTv, searchTv,algoTv,cppTv,javaTv,pythonTv;
    private Button editCodeBt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array);


        Intent in = getIntent();

        codes = database.getReference("codes");

        auth=FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        codeName="";
        language="java";

        codeTv = (TextView) findViewById(R.id.codeTv);

        algoTv = (TextView) findViewById(R.id.algoTv);
        cppTv = (TextView) findViewById(R.id.cppTv);
        javaTv = (TextView) findViewById(R.id.javaTv);
        pythonTv = (TextView) findViewById(R.id.pythonTv);



        insertTv = (TextView) findViewById(R.id.insertTv);
        deleteTv = (TextView) findViewById(R.id.deleteTv);
        sortTv = (TextView) findViewById(R.id.peakTv);
        searchTv = (TextView) findViewById(R.id.searchTv);


        editCodeBt=(Button) findViewById(R.id.editCodeBt);

        codeEt = (EditText) findViewById(R.id.codeEt);

        spinner = (Spinner) findViewById(R.id.spinner);
        try{
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                codeName = spinner.getSelectedItem().toString();
                setCodeTvText();
               }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ArrayActivity.this, "nothing is selected", Toast.LENGTH_SHORT).show();
            }
        });
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }//end onCreate

    @Override
    protected void onStart() {
        super.onStart();

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


    //show code

    public void javaCodeHandler(View view) {
        handleLang(this.javaTv, "java");


    }//end javaCodeHandler

    public void cppCodeHandler(View view) {
        handleLang(this.cppTv, "c++");

    }//end cCodeHandler

    public void pythonCodeHandler(View view) {
        handleLang(this.pythonTv, "python");
    }//end pythonCodeHandler

    public void algoHandler(View view) {
        handleLang(this.algoTv, "algo");
    }//end algoHandler

    public void handleLang(TextView tv, String lang) {
        this.javaTv.setTextColor(getResources().getColor(R.color.black));
        this.cppTv.setTextColor(getResources().getColor(R.color.black));
        this.pythonTv.setTextColor(getResources().getColor(R.color.black));
        this.algoTv.setTextColor(getResources().getColor(R.color.black));
        tv.setTextColor(getResources().getColor(R.color.blue));
        this.language = lang;
        setCodeTvText();

    }//end setTextColor


    public void insertHandler(View view) {
        setSpinnerData(R.array.insertion_choice_array);
        setTextColor(insertTv);
    }//end insertHandler

    public void deleteHandler(View view) {
        setSpinnerData(R.array.deletion_choice);
        setTextColor(deleteTv);

    }//end deleteHandler

    public void sortHandler(View view) {
        setSpinnerData(R.array.sort_choice);
        setTextColor(sortTv);


    }//end sortHandler

    public void searchHandler(View view) {
        setSpinnerData(R.array.search_choice);
        setTextColor(searchTv);
    }//end searchHandler


    public void setTextColor(TextView tv) {
        insertTv.setTextColor(getResources().getColor(R.color.black));
        deleteTv.setTextColor(getResources().getColor(R.color.black));
        sortTv.setTextColor(getResources().getColor(R.color.black));
        searchTv.setTextColor(getResources().getColor(R.color.black));
        tv.setTextColor(getResources().getColor(R.color.blue));
    }//end setTextColor

    public void setSpinnerData(int choice) {
        try {
            spinnerArray = getResources().getStringArray(choice);
            spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
            spinner.setAdapter(spinnerArrayAdapter);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//end setSpinnerData



    // database related

    public void setCodeTvText(){
        Toast.makeText(this, "current code : "+ language+" " + ds + " " +codeName, Toast.LENGTH_SHORT).show();
        try {
        String codeNameWithoutSpace= this.codeName.replaceAll("\\s","");
        codeId=(this.language+ this.ds + codeNameWithoutSpace).trim();
        DatabaseReference code=codes.child(codeId);
        code.keepSynced(true);



            code.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String codeFromDb = snapshot.child("code").getValue(String.class);
                        codeTv.setText(codeFromDb);
                        codeEt.setText(codeFromDb);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e(TAG, error.getMessage());
                    Toast.makeText(ArrayActivity.this, "no such code exist " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){ Log.e(TAG, e.getMessage()); }
       // setRecycler(codeId);
    }//end setCodeTvText

    public void updateCode(View view) {
        try {
        code = codeEt.getText().toString();

        CodeModel codeModel = new CodeModel(codeId,code);

            codes.child(codeId).setValue(codeModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    setCodeTvText();
                    Log.e(TAG, "value is set to database ");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ArrayActivity.this, "value is not set to database", Toast.LENGTH_SHORT);
                    Log.e(TAG, e.getMessage());
                }
            });
        }catch (Exception e){ Log.e(TAG, e.getMessage());}


    }//end updateCode

}//end ArrayActivity

