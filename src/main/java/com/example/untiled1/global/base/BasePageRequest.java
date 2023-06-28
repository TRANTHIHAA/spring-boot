package com.example.untiled1.global.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasePageRequest {
    private int page = 0;
    private int pageSize = 10;
    private Boolean isFetchAll;

    public void setIsFetchAll(Boolean isFetchAll) {
        if (Boolean.TRUE.equals(isFetchAll)) {
            this.page = 0;
            this.pageSize = Integer.MAX_VALUE;
        }
    }
}
