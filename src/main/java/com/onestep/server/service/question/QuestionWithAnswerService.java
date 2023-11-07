package com.onestep.server.service.question;

import com.onestep.server.entity.Family;
import com.onestep.server.entity.GroupQuestion;
import com.onestep.server.entity.Question;
import com.onestep.server.entity.question.AnsweredQuestionDto;
import com.onestep.server.repository.IAnswerRepository;
import com.onestep.server.repository.IFamilyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionWithAnswerService {
    private final IAnswerRepository iAnswerRepository;
    private final IFamilyRepository iFamilyRepository;

    public List<AnsweredQuestionDto> getAnsweredQuestionList(String familyId){
        Family fam = iFamilyRepository.findById(familyId).get();
        List<GroupQuestion> groupQuestions = iAnswerRepository.findAnsweredGroupQuestionsByFamId(fam);
        List<Question> questions = iAnswerRepository.findAnsweredQuestionsByFamId(fam);
        List<AnsweredQuestionDto> answeredQuestionDtos = new ArrayList<>();
        questions.forEach(q -> {
            AnsweredQuestionDto dto = new AnsweredQuestionDto();
            dto.setQuestion_id(q.getQuestion_id());
            dto.setQuestion_date(q.getQuestion_date());
            dto.setQuestion_txt(q.getQuestion_txt());
            dto.setIs_group_question(false);
            answeredQuestionDtos.add(dto);
        });
        groupQuestions.forEach(q -> {
            AnsweredQuestionDto dto = new AnsweredQuestionDto();
            dto.setQuestion_id(q.getQuestion_id());
            dto.setQuestion_date(q.getQuestion_date());
            dto.setQuestion_txt(q.getQuestion_txt());
            dto.setIs_group_question(true);
            answeredQuestionDtos.add(dto);
        });


        Collections.sort(answeredQuestionDtos);

        return answeredQuestionDtos;
    }
}
