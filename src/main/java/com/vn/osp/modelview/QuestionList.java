package com.vn.osp.modelview;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class QuestionList {
    private List<Question> listQuestion;
    private int totalQuestion;
    private int page;
    private int totalPage;
    private String type;
    private String question;
    private String answer;

    public QuestionList() {
    }

    public QuestionList(List<Question> listQuestion, int totalQuestion, int page, int totalPage, String type, String question, String answer) {
        this.listQuestion = listQuestion;
        this.totalQuestion = totalQuestion;
        this.page = page;
        this.totalPage = totalPage;
        this.type = type;
        this.question = question;
        this.answer = answer;
    }

    public List<Question> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<Question> listQuestion) {
        this.listQuestion = listQuestion;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQueryString(){
        String whereString =" " ;
        if(!StringUtils.isBlank(question)){
            whereString = " and bo.question like '%"+question.trim()+"%' ";
        }

        if(!StringUtils.isBlank(answer)){
            whereString = " and bo.answer like '%"+answer.trim()+"%' ";
        }

        if(!StringUtils.isBlank(type)){
            whereString = " and bo.type = "+type;
        }
        return whereString;
    }
}
