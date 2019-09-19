package com.github.siberianintegrationsystems.restApp.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Session extends BaseEntity{

    @Column
    private String name;

    @Column
    String fio;

    @Column
    private LocalDateTime insertDate;

    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }

    @Column
    Double percent;

    @Column
    private Long defaultPageSize;

    public String getName() {
        return name;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Long getDefaultPageSize() {
        return defaultPageSize;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefaultPageSize(Long defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }
}
