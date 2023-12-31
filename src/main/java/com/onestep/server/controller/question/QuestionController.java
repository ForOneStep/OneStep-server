package com.onestep.server.controller.question;

import com.onestep.server.entity.question.AnsweredQuestionDto;
import com.onestep.server.entity.question.ShowQuestionDto;
import com.onestep.server.service.question.QuestionService;
import com.onestep.server.service.question.QuestionWithAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionWithAnswerService questionWithAnswerService;

    //오늘의 질문 확인
    @GetMapping(value = "/question/daily/{groupNumber}")
    public ShowQuestionDto showTodaysQuestion(@PathVariable int groupNumber){
        return questionService.showTodaysQuestion( groupNumber);
    }

    //단일 질문 확인
    @GetMapping(value = "/question/{questionId}")
    public ShowQuestionDto showQuestion(@PathVariable long questionId){
        return questionService.showQuestion( questionId);
    }

    //우리 가족이 대답한 질문 목록확인
    @GetMapping(value = "/question/list/{familyId}")
    public List<AnsweredQuestionDto> showQuestionList(@PathVariable String familyId) {
         return questionWithAnswerService.getAnsweredQuestionList(familyId);
    }
}
