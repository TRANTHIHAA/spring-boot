package com.example.untiled1.global.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
public class BasePageResponse<T> {
    private List<T> items;

    private long total;

    public BasePageResponse(Page<T> page) {
        this.items = page.getContent();
        this.total = page.getTotalElements();
    }

    public BasePageResponse(List<T> list) {
        this.items = list;
        this.total = list.size();
    }
}
