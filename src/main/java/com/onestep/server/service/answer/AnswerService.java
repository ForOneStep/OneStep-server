package com.onestep.server.service.answer;

import com.onestep.server.entity.Answer;
import com.onestep.server.entity.Question;
import com.onestep.server.entity.User;
import com.onestep.server.entity.answer.AnswerDTO;
import com.onestep.server.repository.IAnswerRepository;
import com.onestep.server.repository.IQuestionRepository;
import com.onestep.server.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AnswerService {
    private final IAnswerRepository iAnswerRepository;
    private final IUserRepository iUserRepository;
    private final IQuestionRepository iQuestionRepository;

    // 답변 작성
    public Answer writeAnswer(Long questionId, String userId,String answerTxt, String answerImg){
        Optional<User> optionalUser= iUserRepository.findById(userId);
        User user=optionalUser.get();
        Optional<Question> optionalQuestion = iQuestionRepository.findById(questionId);
        Question question=optionalQuestion.get();

        Date date = new Date();
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setQuestion(question);
        answerDTO.setUser(user);
        answerDTO.setAnswer_txt(answerTxt);
        answerDTO.setAnswer_date(date);

        //답변 저장
        Answer addAnswer = iAnswerRepository.save(answerDTO.toEntity());
        if(answerImg != "") {
            addAnswer.setAnswer_img(answerImg);
        }

        return addAnswer;
    }

//    //답변 읽기
//    public List<Answer> readAnswer(Long questionId,String familyId){
//        List<Answer> answers = iAnswerRepository.findAnswerByFamId(questionId,familyId);
//        return answers;
//    }
}
