package com.example.untiled1.domain.exportExcelNotJasper;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcelDetail {
    private List<String> headers;
    private List<?> tList;
    private Class<?> tClass;
    private String sheetName;
}
