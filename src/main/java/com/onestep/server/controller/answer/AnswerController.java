package com.onestep.server.controller.answer;


import com.onestep.server.entity.Answer;
import com.onestep.server.service.answer.AnswerService;
import com.onestep.server.service.image.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final S3Uploader s3Uploader;


    //답변 작성
    @PostMapping(value = "/answer/create/{question_id}/{user_id}", consumes = {MediaType.APPLICATION_JSON_VALUE, "multipart/form-data"})
    public Answer writeAnswer(@PathVariable Long question_id,@PathVariable String user_id, @RequestPart(value = "answerTxt") String answerTxt, @RequestPart(value = "img") MultipartFile img){
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
        return answer;
    }
    //답변 읽기
    @GetMapping("/answer/read/{question_id}/{family_id}")
    public List<Answer> getAnswer(@PathVariable Long question_id,@PathVariable String family_id){
        List<Answer> answers = answerService.readAnswer(question_id,family_id);

        return answers;
    }
}
