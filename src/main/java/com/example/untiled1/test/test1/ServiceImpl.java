package com.example.untiled1.test.test1;

import com.example.untiled1.global.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceImpl {
    private MapperService getMapperService(String type) {
        MapperService mapperService;
        try {
            String tienTo = "A";
            mapperService =  BeanUtils.getBean(tienTo + "MapperServiceImpl");
        } catch (Exception e) {
            return null;
        }
        Object data = mapperService.toDto(type);
        return mapperService;
    }
}
