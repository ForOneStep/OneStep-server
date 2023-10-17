package com.onestep.server.controller.question;

import com.onestep.server.entity.Question;
import com.onestep.server.service.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    //질문 확인
    @GetMapping(value = "/question/{questionId}")
    public Question showQuestion(@PathVariable Long questionId){
        return questionService.showQuestion(questionId);
    }
}
