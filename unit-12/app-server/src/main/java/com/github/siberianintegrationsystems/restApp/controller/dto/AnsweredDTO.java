package com.github.siberianintegrationsystems.restApp.controller.dto;

import java.util.List;

public class AnsweredDTO {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SelectedAnswersDTO> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<SelectedAnswersDTO> answersList) {
        this.answersList = answersList;
    }

    private List<SelectedAnswersDTO> answersList;
}
