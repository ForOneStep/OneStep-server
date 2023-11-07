package com.onestep.server.service.question;

import com.onestep.server.entity.Question;
import com.onestep.server.repository.IFamilyRepository;
import com.onestep.server.repository.IQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {
    private final IQuestionRepository iQuestionRepository;
    private final IFamilyRepository iFamilyRepository;


    public Question showQuestion(Long questionId){
        Optional<Question> optionalQuestion = iQuestionRepository.findById(questionId);
        return optionalQuestion.get();
    }

    public List<Question> showQuestionList(Long questionId){
        return iQuestionRepository.findListById(questionId);
    }
}
