package com.onestep.server.service.letter;

import com.onestep.server.entity.Letter;
import com.onestep.server.repository.ILetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class LetterService {
    private final ILetterRepository iLetterRepository;

    //익명편지 작성
    public void writeLetter(Letter letter){
        iLetterRepository.save(letter);
    }

    //내가 작성한 편지 확인
    public List<Letter> findLetterByWriterId(String userId){
        return iLetterRepository.findLetterByWriterId(userId);
    }

    //가족 아이디로 편지 목록 확인
    public List<Letter> findLetterByFamilyId(String familyId){
        //우리 가족이 작성한 편지 가져오기
        return iLetterRepository.findLetterByFamilyId(familyId);
    }
}
