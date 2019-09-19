package com.github.siberianintegrationsystems.restApp.entity;

import javax.persistence.*;

@Entity
public class Selected_Answer extends BaseEntity{
    @JoinColumn(name = "answer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Answer answer;

    @JoinColumn(name = "session_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Session session;

    public Selected_Answer(Session session, Answer answer) {
        this.answer = answer;
        this.session = session;
    }
}
