package com.onestep.server.controller.quiz;

import com.onestep.server.entity.quiz.QuizAnswerRequestDTO;
import com.onestep.server.entity.quiz.QuizRequestDTO;
import com.onestep.server.service.quiz.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QuizController {

    private final QuizService quizService;

    //퀴즈 생성
    @PostMapping(value = "/quiz/create")
    public String writeQuiz(@RequestBody QuizRequestDTO quizRequestDTO){
        quizService.writeQuiz(quizRequestDTO);
        return quizRequestDTO.getUser_id()+"님이 작성한 퀴즈가 등록되었습니다.";
    }

    //퀴즈 등록 가능상태 확인
    @GetMapping(value = "/quiz/canQuiz/{family_id}")
    public Boolean canQuiz(@PathVariable String family_id){
        return quizService.canQuiz(family_id);
    }

    //퀴즈 답변
    //0 오답, 1 정답
    @PostMapping(value = "/quiz/answer")
    public String answerQuiz(@RequestBody QuizAnswerRequestDTO quizAnswerRequestDTO){
        quizService.answerQuiz(quizAnswerRequestDTO);
        return quizAnswerRequestDTO.getUser_id()+"님이 작성한 퀴즈 답변이 등록되었습니다.";
    }
}
