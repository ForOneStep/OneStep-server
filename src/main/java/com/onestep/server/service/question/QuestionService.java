package com.onestep.server.service.question;

import com.onestep.server.entity.GroupQuestion;
import com.onestep.server.entity.Question;
import com.onestep.server.entity.question.ShowQuestionDto;
import com.onestep.server.repository.IFamilyRepository;
import com.onestep.server.repository.IGroupQuestionRepository;
import com.onestep.server.repository.IQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {
    private final IQuestionRepository iQuestionRepository;
    private final IGroupQuestionRepository iGroupQuestionRepository;

    public ShowQuestionDto showTodaysQuestion(int groupNumber){

        Date date = new Date();
        LocalTime now = LocalTime.now();

        if(now.getHour()<6){
            Date dDate = new Date();
            date = new Date(dDate.getTime()+(1000*60*60*24*-1));
        }

        Optional<GroupQuestion> optionalGroupQuestion = iGroupQuestionRepository.findGroupQuestionByDate(date, groupNumber);

        if(optionalGroupQuestion.isPresent()) {
            GroupQuestion groupQuestion = optionalGroupQuestion.get();
            return makeDtoByGroupQuestion(groupQuestion);
        }

        Optional<Question> optionalQuestion = iQuestionRepository.findQuestionByDate(date);

        if(optionalQuestion.isPresent()) {

            Question question = optionalQuestion.get();
            return makeDtoByQuestion(question);
        }


        throw new IllegalStateException("잘못된 요청입니다.");
    }

    public ShowQuestionDto showQuestion(long questionId){


        Optional<GroupQuestion> optionalGroupQuestion = iGroupQuestionRepository.findById(questionId);

        if(optionalGroupQuestion.isPresent()) {
            GroupQuestion groupQuestion = optionalGroupQuestion.get();
            return makeDtoByGroupQuestion(groupQuestion);
        }

        Optional<Question> optionalQuestion = iQuestionRepository.findById(questionId);

        if(optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            return makeDtoByQuestion(question);
        }


        throw new IllegalStateException("잘못된 요청입니다.");
    }

    private ShowQuestionDto makeDtoByGroupQuestion(GroupQuestion groupQuestion){
        ShowQuestionDto dto = new ShowQuestionDto();
        dto.setQuestion_id(groupQuestion.getQuestion_id());
        dto.setQuestion_txt(groupQuestion.getQuestion_txt());
        dto.setDate(groupQuestion.getQuestion_date());
        return dto;
    }

    private ShowQuestionDto makeDtoByQuestion(Question question){

        ShowQuestionDto dto = new ShowQuestionDto();
        dto.setQuestion_id(question.getQuestion_id());
        dto.setQuestion_txt(question.getQuestion_txt());
        dto.setDate(question.getQuestion_date());

        return dto;
    }
}
