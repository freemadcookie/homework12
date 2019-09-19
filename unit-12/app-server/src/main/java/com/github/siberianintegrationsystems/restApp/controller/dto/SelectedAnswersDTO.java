package com.github.siberianintegrationsystems.restApp.controller.dto;

import java.util.List;

public class SelectedAnswersDTO {
    private String id;
    private Boolean isSelected;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setSelected(Boolean isSelected) {
        isSelected = isSelected;
    }

}