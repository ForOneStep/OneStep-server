package com.onestep.server.entity.gpt;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatRequestDTO {
    private String model;
    private List<MessageDTO> messages;
    private int n;
    private double temperature;

    public ChatRequestDTO(String model, String prompt) {
        this.model = model;

        this.messages = new ArrayList<>();
        this.messages.add(new MessageDTO("user", prompt));

        this.n = 1;
        this.temperature = 0.5;
    }
}
