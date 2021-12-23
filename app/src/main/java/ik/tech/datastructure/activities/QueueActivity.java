package ik.tech.datastructure.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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



public class QueueActivity extends AppCompatActivity {


    private static final String TAG ="Queue Activity" ;
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
    private final String ds = "queue";
    private String codeId, code="code is not available";

    private EditText codeEt;
    private TextView codeTv, insertTv, deleteTv, frontTv,algoTv,cppTv,javaTv,pythonTv;
    private Button editCodeBt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);


        Intent in = getIntent();

        codes = database.getReference("codes");

        auth=FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        codeName="insert";
        language="algo";

        codeTv = (TextView) findViewById(R.id.codeTv);

        algoTv = (TextView) findViewById(R.id.algoTv);
        cppTv = (TextView) findViewById(R.id.cppTv);
        javaTv = (TextView) findViewById(R.id.javaTv);
        pythonTv = (TextView) findViewById(R.id.pythonTv);



        insertTv = (TextView) findViewById(R.id.insertTv);
        deleteTv = (TextView) findViewById(R.id.deleteTv);
        frontTv = (TextView) findViewById(R.id.frontTv);


        editCodeBt=(Button) findViewById(R.id.editCodeBt);

        codeEt = (EditText) findViewById(R.id.codeEt);

        setCodeTvText();

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

        setTextColor(insertTv,"insert");
    }//end insertHandler

    public void deleteHandler(View view) {

        setTextColor(deleteTv,"delete");

    }//end deleteHandler

    public void frontHandler(View view) {

        setTextColor(frontTv,"front");


    }//end sortHandler



    public void setTextColor(TextView tv,String codeName) {
        insertTv.setTextColor(getResources().getColor(R.color.black));
        deleteTv.setTextColor(getResources().getColor(R.color.black));
        frontTv.setTextColor(getResources().getColor(R.color.black));
        tv.setTextColor(getResources().getColor(R.color.blue));
        this.codeName = codeName;
        setCodeTvText();
    }//end setTextColor



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
                    Toast.makeText(QueueActivity.this, "no such code exist " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(QueueActivity.this, "value is not set to database", Toast.LENGTH_SHORT);
                    Log.e(TAG, e.getMessage());
                }
            });
        }catch (Exception e){ Log.e(TAG, e.getMessage());}


    }//end updateCode

}//end ArrayActivity

