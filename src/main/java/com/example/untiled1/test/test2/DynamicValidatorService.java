package com.example.untiled1.test.test2;

import com.example.untiled1.domain.tutorial.request.TutorialRq;
import com.example.untiled1.domain.tutorial.response.TutorialRp;
import com.example.untiled1.global.constants.NameAndValueEnum;
import org.flywaydb.core.internal.resource.ResourceName;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class DynamicValidatorService {
    private static final String SERVICE_NAME_SUFFIX = "ValidatorService";
    private final BeanFactory beanFactory;

    public DynamicValidatorService(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public boolean isValid(TutorialRp data) {
        ValidationService service;
        try {
            service = beanFactory.getBean(this.getServiceName("A"), ValidationService.class);
        } catch (Exception e) {
            return false;
        }
//return false;
        return service.isValid(data);
    }

    public boolean isValid(String type, TutorialRq data) {
        ValidationService service;
        try {
            service = beanFactory.getBean(this.getServiceName(type), ValidationService.class);
        } catch (Exception e) {
            return true;
        }
//        return false;
        return service.isValid(data);
    }

    private String getServiceName(String type) {
//        NameAndValueEnum loaiBbQd = NameAndValueEnum.fromValue(type);
//        if (loaiBbQd == null) {
//            throw new NotFoundException(type);
//        }
        return type + SERVICE_NAME_SUFFIX; // -> vd: AValidatorService
    }
}
