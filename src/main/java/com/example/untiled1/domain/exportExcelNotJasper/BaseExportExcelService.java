package com.example.untiled1.domain.exportExcelNotJasper;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface BaseExportExcelService {
    HttpHeaders getHeader(String fileName, String... mediaType);

    InputStreamResource export(BaseExportExcel base, ExcelDetail excelDetail);

    InputStreamResource export(ExcelDetail excelDetail);

    InputStreamResource export(BaseExportExcel base, List<ExcelDetail> excelDetails);

    InputStreamResource export(List<ExcelDetail> excelDetails);
}

