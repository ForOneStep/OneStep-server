package com.onestep.server.service.answer;

import com.onestep.server.entity.*;
import com.onestep.server.entity.answer.AnswerDTO;
import com.onestep.server.entity.answer.AnswerGroupQuestionDTO;
import com.onestep.server.entity.answer.AnswerReturnDTO;
import com.onestep.server.entity.answer.AnswerWithValidDTO;
import com.onestep.server.entity.like.LikeAnswerClass;
import com.onestep.server.entity.like.LikeAnswerDTO;
import com.onestep.server.repository.*;
import com.onestep.server.service.image.S3Uploader;
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
    private final S3Uploader s3Uploader;
    // 답변 작성
    public String writeAnswer(Long questionId, String userId,String answerTxt, String answerImg){
        Optional<User> optionalUser= iUserRepository.findById(userId);
        User user=optionalUser.get();

        Answer addAnswer = null;
        Optional<Question> optionalQuestion = iQuestionRepository.findById(questionId);
        if(optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            Date date = new Date();
            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO.setQuestion(question);
            answerDTO.setUser(user);
            answerDTO.setAnswer_txt(answerTxt);
            answerDTO.setAnswer_date(date);
            addAnswer = iAnswerRepository.save(answerDTO.toEntity());
            //답변 저장
            if(answerImg != "") {
                addAnswer.setAnswer_img(answerImg);
            }

            return addAnswer.getQuestion().getQuestion_id()+"번 질문에 대한 답변 작성이 완료되었습니다.";
        }
        Optional<GroupQuestion> optionalGroupQuestion = iGroupQuestionRepository.findById(questionId);
        if(optionalGroupQuestion.isPresent()){
            GroupQuestion groupQuestion = optionalGroupQuestion.get();
            Date date = new Date();
            AnswerGroupQuestionDTO answerGroupQuestionDTO = new AnswerGroupQuestionDTO();
            answerGroupQuestionDTO.setGroupQuestion(groupQuestion);
            answerGroupQuestionDTO.setUser(user);
            answerGroupQuestionDTO.setAnswer_txt(answerTxt);
            answerGroupQuestionDTO.setAnswer_date(date);
            addAnswer = iAnswerRepository.save(answerGroupQuestionDTO.toEntity());
            //답변 저장
            if(answerImg != "") {
                addAnswer.setAnswer_img(answerImg);
            }
            return addAnswer.getGroupQuestion().getQuestion_id()+"번 질문에 대한 답변 작성이 완료되었습니다.";
        }

        return "답변 작성 실패";
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
        if(optionalAnswer.get().getAnswer_img() == null || optionalAnswer.get().getAnswer_img().isEmpty()){
            if(answerImg != ""){
                // 원래 이미지 없었는데 이미지 있게 추가
                updateAnswer.setAnswer_img(answerImg);
            }
        }else{
            deleteAnswerImg(answerId);
            if(answerImg == ""){
                // 원래 이미지 있었는데 이미지 없게 추가
                updateAnswer.setAnswer_img(null);
            }else{
                // 원래 이미지 있었는데 이미지 있게 추가
                updateAnswer.setAnswer_img(answerImg);
            }
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

    public void deleteAnswerImg(Long answer_id){
        //S3 업로드 된 파일 삭제
        Optional<Answer> findUrl = iAnswerRepository.findById(answer_id);
        String url = findUrl.get().getAnswer_img();
        url = url.replaceAll("https://conteswt-bucket.s3.ap-northeast-2.amazonaws.com/","");
        s3Uploader.delete(url);
    }
}
