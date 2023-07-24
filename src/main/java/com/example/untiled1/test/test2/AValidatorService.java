package com.example.untiled1.test.test2;

import com.example.untiled1.domain.tutorial.request.TutorialRq;
import com.example.untiled1.domain.tutorial.response.TutorialRp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("AValidatorService")
@RequiredArgsConstructor
@Slf4j
public class AValidatorService implements ValidationService{
    @Override
    public boolean isValid(TutorialRp data) {
        System.out.println("đã vào đến đây!");
        return true;
    }

    @Override
    public boolean isValid(TutorialRq data) {
        return true;
    }
}
