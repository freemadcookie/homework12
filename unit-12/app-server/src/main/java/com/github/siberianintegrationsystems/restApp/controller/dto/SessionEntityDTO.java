package com.github.siberianintegrationsystems.restApp.controller.dto;

import java.util.List;

public class SessionEntityDTO {
    private String name;
    private List<AnsweredDTO> questionsList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AnsweredDTO> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<AnsweredDTO> questionsList) {
        this.questionsList = questionsList;
    }
}
