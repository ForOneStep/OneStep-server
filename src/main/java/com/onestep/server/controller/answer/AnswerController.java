package com.onestep.server.controller.answer;


import com.onestep.server.entity.Answer;
import com.onestep.server.entity.answer.AnswerDTO;
import com.onestep.server.entity.answer.AnswerReturnDTO;
import com.onestep.server.service.answer.AnswerService;
import com.onestep.server.service.image.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final S3Uploader s3Uploader;


    //답변 작성
    @PostMapping(value = "/answer/create/{question_id}/{user_id}", consumes = {MediaType.APPLICATION_JSON_VALUE, "multipart/form-data"})
    public String writeAnswer(@PathVariable Long question_id,@PathVariable String user_id, @RequestPart(value = "answerTxt") String answerTxt, @RequestPart(value = "img") MultipartFile img){
        Answer answer = null;
        try{
            log.info("imgTest={}",img);
            if(img.isEmpty()) {
                answer = answerService.writeAnswer(question_id,user_id,answerTxt,"");
            }else {
                String url = s3Uploader.upload(img, "answer");
                answer = answerService.writeAnswer(question_id, user_id, answerTxt, url);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return answer.getQuestion().getQuestion_id()+"번 질문에 대한 답변 작성이 완료되었습니다.";
    }
    //답변 읽기
    @GetMapping("/answer/read/{question_id}/{family_id}")
    public List<AnswerReturnDTO> getAnswer(@PathVariable Long question_id,@PathVariable String family_id){
        List<Answer> answers = answerService.readAnswer(question_id,family_id);
        List<AnswerReturnDTO> answerReturnsDTO = new ArrayList<>();

        for(Answer re : answers){
            AnswerReturnDTO answerReturnDTO = new AnswerReturnDTO();
            answerReturnDTO.setAnswer_id(re.getAnswer_id());
            answerReturnDTO.setQuestion_id(re.getQuestion().getQuestion_id());
            answerReturnDTO.setUser_id(re.getUser().getUser_id());
            answerReturnDTO.setUser_nickname(re.getUser().getUser_nickname());
            answerReturnDTO.setProfile_path(re.getUser().getProfile_path());
            answerReturnDTO.setAnswer_txt(re.getAnswer_txt());
            answerReturnDTO.setAnswer_img(re.getAnswer_img());
            answerReturnDTO.setWrite_date(re.getWrite_date());
            answerReturnDTO.setAnswer_liked(re.getAnswer_liked());

            answerReturnsDTO.add(answerReturnDTO);
        }
        return answerReturnsDTO;
    }

    //답변 수정
    @PutMapping("/answer/update/{answerId}")
    public String update(@PathVariable Long answerId, @RequestPart String updateTxt, @RequestPart(value = "img", required = false) MultipartFile img) throws IOException {
        Answer answer = null;
        try{
            log.info("imgTest={}",img);
            if(img.isEmpty()) {
                answer = answerService.update(answerId, updateTxt,"");
            }else {
                String url = s3Uploader.upload(img,"answer");
                answer = answerService.update(answerId, updateTxt, url);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return answer.getQuestion().getQuestion_id()+"번 질문에 대한 답변 수정이 완료되었습니다.";
    }
}
