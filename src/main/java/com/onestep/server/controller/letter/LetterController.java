package com.onestep.server.controller.letter;

import com.onestep.server.entity.Letter;
import com.onestep.server.service.letter.LetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LetterController {
    private final LetterService letterService;

    //익명 편지 작성
    @PostMapping(value = "/letter/write")
    public Letter writeLetter(@RequestBody Letter letter){
        letterService.writeLetter(letter);
        return letter;
    }

    //내가 작성한 편지 확인
    @GetMapping(value = "/letter/byUser/{userId}")
    public List<Letter> findLetterByWriterId(@PathVariable String userId){
        return letterService.findLetterByWriterId(userId);
    }

    //가족 아이디로 편지 목록 확인
    @GetMapping(value = "/letter/byFamily/{familyId}")
    public List<Letter> findLetterByFamilyId(@PathVariable String familyId){
        return letterService.findLetterByFamilyId(familyId);
    }
}
