package com.onestep.server.controller.quiz;

import com.onestep.server.entity.Quiz;
import com.onestep.server.entity.quiz.QuizDTO;
import com.onestep.server.entity.quiz.QuizRequestDTO;
import com.onestep.server.service.quiz.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QuizController {

    private final QuizService quizService;
    @PostMapping(value = "/quiz/create")
    public String writeQuiz(@RequestBody QuizRequestDTO quizRequestDTO){
         quizService.writeQuiz(quizRequestDTO);
        return quizRequestDTO.getUser_id()+"님이 작성한 퀴즈가 등록되었습니다.";
    }
}
