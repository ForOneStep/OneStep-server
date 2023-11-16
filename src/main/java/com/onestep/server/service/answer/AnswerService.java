package com.onestep.server.service.answer;

import com.onestep.server.entity.*;
import com.onestep.server.entity.answer.AnswerDTO;
import com.onestep.server.entity.answer.AnswerReturnDTO;
import com.onestep.server.entity.answer.AnswerWithValidDTO;
import com.onestep.server.entity.like.LikeAnswerClass;
import com.onestep.server.entity.like.LikeAnswerDTO;
import com.onestep.server.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private final IGroupQuestionRepository iGroupQuestionRepository;
    private final IFamilyRepository iFamilyRepository;
    private final ILikeAnswerRepository iLikeAnswerRepository;

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
    //답변 읽기
    public AnswerWithValidDTO readAnswer(Long questionId, String familyId){
        AnswerWithValidDTO dto = new AnswerWithValidDTO();

        Optional<Family> optionalFamily = iFamilyRepository.findById(familyId);
        Family family = optionalFamily.get();

        Optional<Question> optionalQuestion = iQuestionRepository.findById(questionId);
        List<Answer> answers;
        if(optionalQuestion.isPresent()){
            Question question =optionalQuestion.get();
            answers = iAnswerRepository.findAnswerByQuestionIdAndFamId(question,family);
            dto.setCanRead(answers.size() == family.getHead_count());
            dto.setAnswers( makeDto(answers));
            return dto;
        }
        Optional<GroupQuestion> optionalGroupQuestion = iGroupQuestionRepository.findById(questionId);
        if(optionalGroupQuestion.isPresent()){
            GroupQuestion question = optionalGroupQuestion.get();
            answers = iAnswerRepository.findAnswerByGroupQuestionIdAndFamId(question,family);
            dto.setCanRead(answers.size() == family.getHead_count());
            dto.setAnswers( makeDto(answers));
            return dto;
        }

        throw new IllegalStateException("답변을 읽어올 수 없습니다.");
    }

    private List<AnswerReturnDTO> makeDto(List<Answer> answers){
        List<AnswerReturnDTO> answerReturnsDTO = new ArrayList<>();

        for(Answer re : answers){
            AnswerReturnDTO answerReturnDTO = new AnswerReturnDTO();
            answerReturnDTO.setAnswer_id(re.getAnswer_id());
            if(re.getQuestion()!=null) answerReturnDTO.setQuestion_id(re.getQuestion().getQuestion_id());
            if(re.getGroupQuestion()!=null) answerReturnDTO.setQuestion_id(re.getGroupQuestion().getQuestion_id());
            answerReturnDTO.setUser_id(re.getUser().getUser_id());
            answerReturnDTO.setUser_nickname(re.getUser().getUser_nickname());
            answerReturnDTO.setProfile_path(re.getUser().getProfile_path());
            answerReturnDTO.setAnswer_txt(re.getAnswer_txt());
            answerReturnDTO.setAnswer_img(re.getAnswer_img());
            answerReturnDTO.setWrite_date(re.getWrite_date());
            answerReturnDTO.setLike(re.getLike());

            answerReturnsDTO.add(answerReturnDTO);
        }
        return answerReturnsDTO;
    }

    //답변 수정
    public Answer update(Long answerId,String updateTxt,String answerImg){
        Optional<Answer> optionalAnswer = iAnswerRepository.findById(answerId);
        Answer updateAnswer = new Answer();

        Date date = new Date();
        updateAnswer.setAnswer_id(answerId);
        updateAnswer.setQuestion(optionalAnswer.get().getQuestion());
        updateAnswer.setUser(optionalAnswer.get().getUser());
        updateAnswer.setAnswer_txt(updateTxt);
        updateAnswer.setWrite_date(date);
        if(answerImg != "") {
            updateAnswer.setAnswer_img(answerImg);
        }
        //답변 저장
        return iAnswerRepository.save(updateAnswer);
    }

    //좋아요
    public String likeAnswer(Long answerId, String userId){
        Optional<Answer> optionalAnswer = iAnswerRepository.findById(answerId);
        Answer answer = optionalAnswer.get();
        Optional<User> optionalUser = iUserRepository.findById(userId);
        User user = optionalUser.get();
        LikeAnswerClass likeAnswerClass = new LikeAnswerClass();
        likeAnswerClass.setAnswerId(answerId);
        likeAnswerClass.setUserId(userId);
        Optional<LikeAnswer> optionalLike = iLikeAnswerRepository.findById(likeAnswerClass);

        if(optionalLike.isEmpty()){
            LikeAnswerDTO likeAnswerDTO = new LikeAnswerDTO();
            likeAnswerDTO.setLikeId(likeAnswerClass);
            likeAnswerDTO.setAnswer(answer);
            likeAnswerDTO.setUser(user);
            iLikeAnswerRepository.save(likeAnswerDTO.toEntity());
            return answerId+"번 답변에 대한 "+userId+"의 좋아요 추가 완료";
        }else{
            iLikeAnswerRepository.deleteById(likeAnswerClass);
            return answerId+"번 답변에 대한 "+userId+"의 좋아요 삭제 완료";
        }

    }
}
