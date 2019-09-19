package com.github.siberianintegrationsystems.restApp.controller;

import com.github.siberianintegrationsystems.restApp.controller.dto.QuestionsItemDTO;
import com.github.siberianintegrationsystems.restApp.controller.dto.SessionEntityDTO;
import com.github.siberianintegrationsystems.restApp.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/session")
public class SessionRestController {

    private final QuestionService questionService;

    public SessionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("questions-new")
    public List<QuestionsItemDTO> getNewQuestions(){
        return questionService.getQuestionsForSession();
    }

    @PostMapping("")
    public String create(@RequestBody SessionEntityDTO dto) {
        return questionService.createSession(dto);
    }
}
