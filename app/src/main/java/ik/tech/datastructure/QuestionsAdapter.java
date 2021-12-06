package ik.tech.datastructure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsHolder> {
    private Context ctx;
    private FirebaseDatabase db;
    private DatabaseReference questions,answers,users;
    private String userId,codeId;
    private ArrayList<QuestionModel> questionsList;

    public void getQuestions(){
        questions.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snap: snapshot.getChildren()) {
                        if(snap.child("codeId").getValue().toString().equals(codeId)) {
                            QuestionModel quest = snap.getValue(QuestionModel.class);
                            questionsList.add(quest);
                        }
                    }//end foreach
                }//end if
            }//end onDataChange

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }//end getQuestions


    public QuestionsAdapter(Context ctx, String userId, String codeId ){
        this.ctx=ctx;
        this.userId=userId;
        this.codeId=codeId;
        db=FirebaseDatabase.getInstance();
        questions = db.getReference("questions");
        answers = db.getReference("answers");
        users = db.getReference("users");
    }

    @NonNull
    @Override
    public QuestionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li=LayoutInflater.from(ctx);
        View questView = li.inflate(R.layout.questions_layout,parent,false);
        return new QuestionsHolder(questView);
    }

    String name;

    @Override
    public void onBindViewHolder(@NonNull QuestionsHolder holder, int position) {
        for(int i=0;i<questionsList.size();i++){

            QuestionModel quest=questionsList.get(i);
            holder.questionTv.setText(quest.getQuestion());
            holder.sinceTv.setText(quest.getPostedTime());

            users.child(quest.getUserId()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        name=snapshot.getValue(String.class);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            holder.nameTv.setText(name);



            holder.ansBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            holder.questEditBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            holder.questDeleteBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }//end for
    }//end onBindViewHolder

    @Override
    public int getItemCount() {
        return 0;
    }

    public class QuestionsHolder extends RecyclerView.ViewHolder{
    TextView nameTv,sinceTv,questionTv;
    ImageButton ansBt,questDeleteBt,questEditBt;
        public QuestionsHolder(@NonNull View questView) {
            super(questView);
            nameTv = questView.findViewById(R.id.nameTv);
            sinceTv = questView.findViewById(R.id.sinceTv);
            questionTv = questView.findViewById(R.id.questionTv);
            ansBt = questView.findViewById(R.id.ansBt);
            questDeleteBt = questView.findViewById(R.id.questDeleteBt);
            questEditBt = questView.findViewById(R.id.questEditBt);

        }
    }//end QuestionsHolder class
}//end QuestionsAdapter