package com.example.untiled1.test.test2;

import com.example.untiled1.domain.tutorial.request.TutorialRq;
import com.example.untiled1.domain.tutorial.response.TutorialRp;

public interface ValidationService {
    boolean isValid(TutorialRp data);

    boolean isValid(TutorialRq data);
}

