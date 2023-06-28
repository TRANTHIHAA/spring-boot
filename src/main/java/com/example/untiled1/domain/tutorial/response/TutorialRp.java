package com.example.untiled1.domain.tutorial.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TutorialRp {
    private BigDecimal id;
    private String description;
    private String title;
    private String status;
    private String status1;

}
