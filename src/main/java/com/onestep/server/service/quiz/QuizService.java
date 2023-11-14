package com.onestep.server.service.quiz;

import com.onestep.server.entity.Family;
import com.onestep.server.entity.Quiz;
import com.onestep.server.entity.QuizAnswer;
import com.onestep.server.entity.User;
import com.onestep.server.entity.quiz.*;
import com.onestep.server.repository.IQuizAnswerRepository;
import com.onestep.server.repository.IQuizRepository;
import com.onestep.server.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class QuizService {
    private final IQuizRepository iQuizRepository;
    private final IQuizAnswerRepository iQuizAnswerRepository;
    private final IUserRepository iUserRepository;

    public Quiz writeQuiz(QuizRequestDTO quizRequestDTO){
        Optional<User> optionalUser = iUserRepository.findById(quizRequestDTO.getUser_id());
        User user = optionalUser.get();

        Date date = new Date();
        LocalTime now = LocalTime.now();

        if(now.getHour()<6){
            Date dDate = new Date();
            date = new Date(dDate.getTime()+(1000*60*60*24*-1));
        }
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

        //가족 구성원 QuizAnswer DB에 저장
        Family family = user.getFamily();
        List<User> users = iUserRepository.findUserByFamily(family);

        for(User u : users){
            if(u.getUser_id().equals(user.getUser_id())){
                continue;
            }
            QuizAnswerDTO quizAnswerDTO = new QuizAnswerDTO();
            quizAnswerDTO.setQuiz(addQuiz);
            quizAnswerDTO.setUser(u);
            iQuizAnswerRepository.save(quizAnswerDTO.toEntity());
        }

        return addQuiz;
    }

    //퀴즈 생성 가능 여부
    public Boolean canQuiz(String family_id){
        Boolean canQuiz = true;
        Date writeDate = new Date();
        LocalTime now = LocalTime.now();

        if(now.getHour()<6){
            Date dDate = new Date();
            writeDate = new Date(dDate.getTime()+(1000*60*60*24*-1));
        }
        Optional<Quiz> optionalQuiz = iQuizRepository.findQuizByWriteDate(family_id,writeDate);
        // 같은 날에 퀴즈를 생성 했을 시
        if(optionalQuiz.isPresent()){
            canQuiz = false;
        }

        return canQuiz;
    }

    //퀴즈 답변
    public QuizAnswer answerQuiz(QuizAnswerRequestDTO quizAnswerRequestDTO){
        Optional<QuizAnswer> optionalQuizAnswer = iQuizAnswerRepository.findQuizByUser(quizAnswerRequestDTO.getQuiz_id(),quizAnswerRequestDTO.getUser_id());
        Optional<Quiz> quiz = iQuizRepository.findById(quizAnswerRequestDTO.getQuiz_id());
        Integer answer = quiz.get().getQuiz_ans();

        QuizAnswer quizAnswer = new QuizAnswer();
        quizAnswer.setQuizAnswer_id(optionalQuizAnswer.get().getQuizAnswer_id());
        quizAnswer.setQuiz(optionalQuizAnswer.get().getQuiz());
        quizAnswer.setUser(optionalQuizAnswer.get().getUser());
        quizAnswer.setQuiz_ans(quizAnswerRequestDTO.getQuiz_ans());

        if(quizAnswerRequestDTO.getQuiz_ans().equals(answer)){
            quizAnswer.setQuiz_state(1);
        }else{
            quizAnswer.setQuiz_state(0);
        }

        return iQuizAnswerRepository.save(quizAnswer);
    }

    //퀴즈 확인
    public List<QuizAnswerCheckDTO> checkAnswer(Long quiz_id){
        Optional<Quiz> optionalQuiz = iQuizRepository.findById(quiz_id);
        List<QuizAnswer> quizAnswers = optionalQuiz.get().getQuizAnswers();
        List<QuizAnswerCheckDTO> quizAnswerCheck = new ArrayList<>();
        for(QuizAnswer q : quizAnswers){
            QuizAnswerCheckDTO quizAnswerCheckDTO = new QuizAnswerCheckDTO();

            User answer = q.getUser();
            quizAnswerCheckDTO.setQuizAnswer_id(q.getQuizAnswer_id());
            quizAnswerCheckDTO.setUser_id(answer.getUser_id());
            quizAnswerCheckDTO.setQuiz_ans(q.getQuiz_ans());
            quizAnswerCheckDTO.setQuiz_state(q.getQuiz_state());

            quizAnswerCheck.add(quizAnswerCheckDTO);

        }
        return quizAnswerCheck;
    }
}
