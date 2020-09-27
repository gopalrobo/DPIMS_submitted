package com.smart.cst.afimail.master.fgd;

public class FGDBean {

    String id;
    String section;
    String question;
    String questionType;
    String options;



    public FGDBean(String id, String section, String question, String questionType, String options) {
        this.id = id;
        this.section = section;
        this.question = question;
        this.questionType = questionType;
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
