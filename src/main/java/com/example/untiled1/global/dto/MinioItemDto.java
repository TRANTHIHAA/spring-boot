package com.example.untiled1.global.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MinioItemDto {
    private String objectName;
    private long size;
}
