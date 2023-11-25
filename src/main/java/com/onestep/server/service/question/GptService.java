package com.onestep.server.service.question;

import com.onestep.server.entity.GroupQuestion;
import com.onestep.server.entity.KeyWord;
import com.onestep.server.entity.gpt.ChatRequestDTO;
import com.onestep.server.entity.gpt.ChatResponseDTO;
import com.onestep.server.entity.question.SaveGroupQuestionDto;
import com.onestep.server.entity.question.SaveQuestionDto;
import com.onestep.server.repository.IGroupQuestionRepository;
import com.onestep.server.repository.IKeyWordRepository;
import com.onestep.server.repository.IQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class GptService {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.key}")
    private String openaiApiKey;

    private final IKeyWordRepository iKeyWordRepository;
    private final IGroupQuestionRepository iGroupQuestionRepository;
    private final IQuestionRepository iQuestionRepository;
    private HttpEntity<ChatRequestDTO> getHttpEntity(ChatRequestDTO chatRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + openaiApiKey);

        return new HttpEntity<>(chatRequest, headers);
    }

    public String getKeyword(){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        Long randomNum = (long) (random.nextInt(70) + 1);
        Optional<KeyWord> optionalKeyWord = iKeyWordRepository.findById(randomNum);
        return optionalKeyWord.get().getKeyword();
    }
    @Scheduled(cron = "0 0 6 ? * TUE,THU,FRI,SUN", zone = "Asia/Seoul") //화 목 금 일
    public void getGroupQuestions() {
        Date date = new Date();

        List<GroupQuestion> newQuestionList = new ArrayList<>();

        String randomWord = getKeyword();

        // 나쁨
        String query1 = "평소에 대화를 잘 하지 않는 가족이 서로에게 궁금해 할법한 질문을 1개 추천해줘.";
        query1 += "\n질문의 조건은 다음과 같아.";
        query1 += "\n1.이 가족은 자신의 가족이 서로 친밀하지 않다고 느껴.";
        query1 += "\n2.가족의 관계에 도움이 될만한 질문이어야해";
        query1 += "\n3.다음 키워드와 관련된 질문이어야돼: ";
        query1 += randomWord;
        query1 += "\n답변 형식은 '오늘의 질문: {오늘의 질문}' 이 형식으로 적어줘";

        // Create a request
        ChatRequestDTO request1 = new ChatRequestDTO(model, query1);

        // Call the API
        RestTemplate restTemplate1 = new RestTemplate();
        ChatResponseDTO response1 = restTemplate1.postForObject(apiUrl, getHttpEntity(request1), ChatResponseDTO.class);

        if (response1 == null || response1.getChoices() == null || response1.getChoices().isEmpty()) {
            throw new RuntimeException();
        }

        String state1 = response1.getChoices().get(0).getMessage().getContent().substring(8);
        SaveGroupQuestionDto dto = new SaveGroupQuestionDto();
        dto.setQuestion_date(date);
        dto.setQuestion_txt(state1);
        dto.setGroup_number(1);
        newQuestionList.add(dto.toEntity());

        // 보통
        String query2 = "일반적인 가족이 서로에게 궁금해 할법한 질문을 1개 추천해줘.";
        query2 += "\n질문의 조건은 다음과 같아.";
        query2 += "\n1.이 가족은 자신의 가족 관계가 평험하다고 느껴.";
        query2 += "\n2.가족의 관계에 도움이 될만한 질문이어야해";
        query2 += "\n3.다음 키워드와 관련된 질문이어야돼: ";
        query2 += randomWord;
        query2 += "\n답변 형식은 '오늘의 질문: {오늘의 질문}' 이 형식으로 적어줘";

        // Create a request
        ChatRequestDTO request2 = new ChatRequestDTO(model, query2);

        // Call the API
        RestTemplate restTemplate2 = new RestTemplate();
        ChatResponseDTO response2 = restTemplate2.postForObject(apiUrl, getHttpEntity(request2), ChatResponseDTO.class);

        if (response2 == null || response2.getChoices() == null || response2.getChoices().isEmpty()) {
            throw new RuntimeException();
        }

        String state2 = response2.getChoices().get(0).getMessage().getContent().substring(8);
        SaveGroupQuestionDto dto2 = new SaveGroupQuestionDto();
        dto2.setQuestion_date(date);
        dto2.setQuestion_txt(state2);
        dto2.setGroup_number(2);
        newQuestionList.add(dto2.toEntity());

        // 좋음
        String query3 = "화목한 가족이 서로에게 궁금해 할법한 질문을 1개 추천해줘.";
        query3 += "\n질문의 조건은 다음과 같아.";
        query3 += "\n1.이 가족은 자신의 가족이 화목하다고 느껴.";
        query3 += "\n2.화목함을 유지하는데 도움이 되는 질문이어야해";
        query3 += "\n3.다음 키워드와 관련된 질문이어야돼: ";
        query3 += randomWord;
        query3 += "\n답변 형식은 '오늘의 질문: {오늘의 질문}' 이 형식으로 적어줘";

        // Create a request
        ChatRequestDTO request3 = new ChatRequestDTO(model, query3);

        // Call the API
        RestTemplate restTemplate3 = new RestTemplate();
        ChatResponseDTO response3 = restTemplate3.postForObject(apiUrl, getHttpEntity(request3), ChatResponseDTO.class);

        if (response3 == null || response3.getChoices() == null || response3.getChoices().isEmpty()) {
            throw new RuntimeException();
        }

        String state3 = response3.getChoices().get(0).getMessage().getContent().substring(8);
        SaveGroupQuestionDto dto3 = new SaveGroupQuestionDto();
        dto3.setQuestion_date(date);
        dto3.setQuestion_txt(state3);
        dto3.setGroup_number(3);
        newQuestionList.add(dto3.toEntity());

        iGroupQuestionRepository.saveAll(newQuestionList);
    }

    @Scheduled(cron = "0 0 6 ? * MON,WED,SAT", zone = "Asia/Seoul") //월, 수,  토
    public void getQuestions() {
        Date date = new Date();

        // 공통
        SaveQuestionDto questionDto;
        String query4 = "나를 잘 표현할 수 있는 질문을 1개 추천해줘.";
        query4 += "\n질문의 조건은 다음과 같아.";
        query4 += "\n1.내가 어떤 사람인지 표현할 수 있을 만한 질문이어야해";
        query4 += "\n2.장난스럽고 유쾌한 질문어어도 괜찮아.";
        query4 += "\n3.다음 키워드와 관련된 질문이어야돼: ";
        query4 += getKeyword();
        query4 += "\n답변 형식은 '오늘의 질문: {오늘의 질문}' 이 형식으로 적어줘";

        // Create a request
        ChatRequestDTO request4 = new ChatRequestDTO(model, query4);

        // Call the API
        RestTemplate restTemplate4 = new RestTemplate();
        ChatResponseDTO response4 = restTemplate4.postForObject(apiUrl, getHttpEntity(request4), ChatResponseDTO.class);

        if (response4 == null || response4.getChoices() == null || response4.getChoices().isEmpty()) {
            throw new RuntimeException();
        }

        String state4 = response4.getChoices().get(0).getMessage().getContent().substring(8);

        questionDto = new SaveQuestionDto();
        questionDto.setQuestion_date(date);
        questionDto.setQuestion_txt(state4);

        iQuestionRepository.save(questionDto.toEntity());
    }
}
