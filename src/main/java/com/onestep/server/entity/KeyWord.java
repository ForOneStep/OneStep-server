package com.onestep.server.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class KeyWord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long keywordId;

    private String keyword;
}
