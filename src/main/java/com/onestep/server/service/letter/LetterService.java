package com.onestep.server.service.letter;

import com.onestep.server.entity.Family;
import com.onestep.server.entity.Letter;
import com.onestep.server.entity.User;
import com.onestep.server.entity.letter.LetterListDTO;
import com.onestep.server.entity.letter.WriteLetterDto;
import com.onestep.server.repository.IFamilyRepository;
import com.onestep.server.repository.ILetterRepository;
import com.onestep.server.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
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
public class LetterService {
    private final ILetterRepository iLetterRepository;
    private final IUserRepository iUserRepository;
    private final IFamilyRepository iFamilyRepository;

    @Scheduled(cron = "0 0 6 ? * FRI", zone = "Asia/Seoul") //매주 금요일 오전 6시마다
    public void changeLetterState(){
        iLetterRepository.changeLetterState();
    }

    //익명 쪽지 작성
    public void writeLetter(WriteLetterDto letterDto){
        Optional<User> optionalUser = iUserRepository.findById(letterDto.getWriter_id());
        if(optionalUser.isEmpty()) throw new IllegalArgumentException("아이디를 찾을 수 없습니다.");
        Letter letter = new Letter();
        letter.setLetter_id(null);
        letter.setLetter_txt(letterDto.getLetter_txt());
        letter.setLetter_title(letterDto.getLetter_title());
        letter.setWrite_date(new Date());
        letter.setUser(optionalUser.get());
        iLetterRepository.save(letter);
    }

    //내가 작성한 쪽지 확인
    public List<LetterListDTO> findLetterByWriterId(String userId){
        Optional<User> optionalUser = iUserRepository.findById(userId);
        User user = optionalUser.get();
        List<Letter> originLetterList = iLetterRepository.findLetterByWriterId(user);

        return makeReturnLetterList(originLetterList);
    }

    //가족 아이디로 쪽지 목록 확인
    public List<LetterListDTO> findLetterByFamilyId(String familyId){
        Optional<Family> optionalFamily = iFamilyRepository.findById(familyId);
        Family family = optionalFamily.get();

        //우리 가족이 작성한 쪽지 가져오기
        List<Letter> originLetterList = iLetterRepository.findLetterByFamilyId(family);
        return makeReturnLetterList(originLetterList);
    }

    //가족 아이디로 이번주 공개된 쪽지 목록 확인
    public List<LetterListDTO> findWeeklyLetterByFamilyId(String familyId){
        Optional<Family> optionalFamily = iFamilyRepository.findById(familyId);
        Family family = optionalFamily.get();

        //우리 가족이 작성한 쪽지 가져오기
        List<Letter> originLetterList = iLetterRepository.findWeeklyLetterByFamilyId(family);
        return makeReturnLetterList(originLetterList);
    }

    public List<LetterListDTO> makeReturnLetterList(List<Letter> originLetterList){
        List<LetterListDTO> letterList = new ArrayList<>();

        for (Letter l : originLetterList) {
            LetterListDTO letterListDTO = new LetterListDTO();
            User writer = l.getUser();

            letterListDTO.setLetter_id(l.getLetter_id());
            letterListDTO.setWriter_id(writer.getUser_id());
            letterListDTO.setFamily_id(writer.getFamily().getFam_id());
            letterListDTO.setLetter_txt(l.getLetter_txt());
            letterListDTO.setLetter_title(l.getLetter_title());
            letterListDTO.setWrite_date(l.getWrite_date());
            letterListDTO.setLetter_state(l.getLetter_state());

            letterList.add(letterListDTO);

        }
        return letterList;
    }
}
