package ik.tech.datastructure;

import android.os.Build;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QuestionModel {
    String id;
    String question;
    String codeId;
    String userId;
    String postedTime;


    public QuestionModel(){}

    public QuestionModel(String id, String question, String codeId, String userId,String postedTime) {
        this.id = id;
        this.question = question;
        this.codeId = codeId;
        this.userId = userId;
        this.postedTime=postedTime;
    }



    public String getPostedTime() {
        return postedTime;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getCodeId() {
        return codeId;
    }

    public String getUserId() {
        return userId;
    }

}
