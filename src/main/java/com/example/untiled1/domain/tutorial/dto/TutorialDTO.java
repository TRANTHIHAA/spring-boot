package com.example.untiled1.domain.tutorial.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TutorialDTO {
    private BigDecimal id;
    private String description;
    private String title;
    private String status;
}
