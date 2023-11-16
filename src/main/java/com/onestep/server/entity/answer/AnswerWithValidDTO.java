package com.onestep.server.entity.answer;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerWithValidDTO {
    private boolean canRead;
    private List<AnswerReturnDTO> answers;
}
