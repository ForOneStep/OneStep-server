package com.onestep.server.service.quiz;

import com.onestep.server.entity.Quiz;
import com.onestep.server.entity.User;
import com.onestep.server.entity.quiz.QuizDTO;
import com.onestep.server.entity.quiz.QuizRequestDTO;
import com.onestep.server.repository.IQuizRepository;
import com.onestep.server.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class QuizService {
    private final IQuizRepository iQuizRepository;
    private final IUserRepository iUserRepository;
    public Quiz writeQuiz(QuizRequestDTO quizRequestDTO){
        Optional<User> optionalUser = iUserRepository.findById(quizRequestDTO.getUser_id());
        User user = optionalUser.get();

        Date date = new Date();
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setUser(user);
        quizDTO.setQuiz_txt(quizRequestDTO.getQuiz_txt());
        quizDTO.setAnswer1(quizRequestDTO.getAnswer1());
        quizDTO.setAnswer2(quizRequestDTO.getAnswer2());
        quizDTO.setAnswer3(quizRequestDTO.getAnswer3());
        quizDTO.setAnswer4(quizRequestDTO.getAnswer4());
        quizDTO.setQuiz_ans(quizRequestDTO.getQuiz_ans());
        quizDTO.setWrite_date(date);

        Quiz addQuiz = iQuizRepository.save(quizDTO.toEntity());
        return addQuiz;
    }
}
