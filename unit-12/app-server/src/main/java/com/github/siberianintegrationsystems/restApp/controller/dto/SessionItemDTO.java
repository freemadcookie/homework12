package com.github.siberianintegrationsystems.restApp.controller.dto;

import com.github.siberianintegrationsystems.restApp.entity.Question;
import com.github.siberianintegrationsystems.restApp.entity.Session;

import java.time.LocalDateTime;

public class SessionItemDTO extends JournalItemDTO {
    private String name;
    private String result;
    private LocalDateTime insertDate;

    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public SessionItemDTO(Session session) {
        this.insertDate = session.getInsertDate();
        this.name = session.getFio();
        this.result = Double.toString(session.getPercent());
    }
}
