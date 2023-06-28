package com.example.untiled1.global.utils;

import com.example.untiled1.global.base.BasePageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Slf4j
public class PageUtils {
    private PageUtils() {
        throw new UnsupportedOperationException();
    }

    public static Pageable toPageable(BasePageRequest pageReq) {
        return PageRequest.of(pageReq.getPage(), pageReq.getPageSize());
    }
}

