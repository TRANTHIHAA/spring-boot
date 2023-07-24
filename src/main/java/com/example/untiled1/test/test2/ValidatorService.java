package com.example.untiled1.test.test2;

import com.example.untiled1.domain.tutorial.response.TutorialRp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValidatorService {
    private final DynamicValidatorService validatorService;

    // có thể set lại giá trị hoặc check nhiều điều kiện, ko nhất thiết trả về boolean
    public boolean validate(TutorialRp data) {
      return   this.validatorService.isValid(data);
    }
}
