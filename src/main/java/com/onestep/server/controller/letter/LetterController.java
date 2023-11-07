package com.onestep.server.controller.letter;

import com.onestep.server.entity.Letter;
import com.onestep.server.entity.letter.LetterListDTO;
import com.onestep.server.service.letter.LetterService;
import com.onestep.server.service.question.GptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LetterController {
    private final LetterService letterService;
    private final GptService gptService;

    //익명 쪽지 작성
    @PostMapping(value = "/letter/write")
    public Letter writeLetter(@RequestBody Letter letter){
        letterService.writeLetter(letter);
        return letter;
    }

    //내가 작성한 쪽지 확인
    @GetMapping(value = "/letter/byUser/{userId}")
    public List<LetterListDTO> findLetterByWriterId(@PathVariable String userId){
        gptService.getQuestions();
        return letterService.findLetterByWriterId(userId);
    }

    //가족 아이디로 쪽지 목록 확인
    @GetMapping(value = "/letter/byFamily/{familyId}")
    public List<LetterListDTO> findLetterByFamilyId(@PathVariable String familyId){
        return letterService.findLetterByFamilyId(familyId);
    }

    //가족 아이디로 이번주 공개된 쪽지 확인
    @GetMapping(value = "/letter/weekly/{familyId}")
    public List<LetterListDTO> findWeeklyLetterByFamilyId(@PathVariable String familyId){
        return letterService.findWeeklyLetterByFamilyId(familyId);
    }
}
