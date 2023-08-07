package com.example.untiled1.domain.exportExcelNotJasper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class BaseExportExcelServiceImpl implements BaseExportExcelService {

    @Override
    public HttpHeaders getHeader(String fileName, String... mediaType) {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.valueOf(mediaType.length > 0 ?
                mediaType[0] :
                new BaseExportExcel().getContentType()));
        header.setContentDisposition(ContentDisposition
                .attachment()
                .filename(URLEncoder.encode(fileName, StandardCharsets.UTF_8))
                .build());
        return header;
    }

    @Override
    public InputStreamResource export(List<ExcelDetail> excelDetails) {
        return this.export(BaseExportExcel.builder().build(), excelDetails);
    }

    @Override
    public InputStreamResource export(BaseExportExcel base, ExcelDetail excelDetail) {
        return this.export(base, List.of(excelDetail));
    }

    @Override
    public InputStreamResource export(ExcelDetail excelDetail) {
        return this.export(BaseExportExcel.builder().build(), List.of(excelDetail));
    }

    @Override
    public InputStreamResource export(BaseExportExcel base, List<ExcelDetail> excelDetails) {
        if (excelDetails == null || excelDetails.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, base.getErrorBadRequest());
        }

        String uuid = UUID.randomUUID().toString();
        long startTime = System.currentTimeMillis();
        log.info("Start export excel ID: {}-{}", uuid, startTime);
        boolean isSuccess = true;

        try (var os = new ByteArrayOutputStream();
             var workbook = new SXSSFWorkbook()) {

            for (ExcelDetail ed : excelDetails) {

                var sheet = workbook.createSheet(ed.getSheetName());

                var headerFont = workbook.createFont();
                headerFont.setFontName(base.getFontName());
                headerFont.setFontHeightInPoints(base.getFontSize());
                headerFont.setBold(base.getIsSetBoldHeader());

                var headerStyle = xssfCellStyle(headerFont, workbook, base);
                headerStyle.setFillForegroundColor(base.getFillForegroundColorHeader());
                headerStyle.setFillPattern(base.getFillPatternHeader());
                headerStyle.setAlignment(HorizontalAlignment.CENTER);

                var normalFont = workbook.createFont();
                normalFont.setFontName(base.getFontName());
                normalFont.setFontHeightInPoints(base.getFontSize());

                var normalStyle = xssfCellStyle(normalFont, workbook, base);

                int firstRowInTable = 0;

                this.validate(base, ed.getHeaders(), ed.getTList(), ed.getTClass());

                var row = sheet.createRow(firstRowInTable);
                row.createCell(0).setCellStyle(headerStyle);

                row.getCell(0).setCellValue(base.getNameHeaderColumZero());

                AtomicInteger rowHeader = new AtomicInteger(1);
                for (String s : ed.getHeaders()) {
                    row.createCell(rowHeader.get()).setCellStyle(headerStyle);
                    row.getCell(rowHeader.get()).setCellValue(s);
                    rowHeader.getAndIncrement();
                }

                Field[] fields = ed.getTClass().getDeclaredFields();

                int fieldLength = fields.length;
                if (!base.getFieldIgnoreSet().isEmpty()) {
                    fieldLength -= base.getFieldIgnoreSet().size();
                }

                if (ed.getHeaders().isEmpty() || fields.length < 1 || !Objects.equals(ed.getHeaders().size(), fieldLength)) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, base.getHeaderNotEqualField());
                }

                var rowNum = new AtomicInteger(++firstRowInTable);

                setValueRowNormal(sheet, ed.getTList(), rowNum, fields, normalStyle, base.getIsAccessible(), base);

                if (ed.getTList().size() < 1000) {
                    this.setAutoSizeColumn(fields, sheet);
                }
            }

            workbook.write(os);
            return new InputStreamResource(new ByteArrayInputStream(os.toByteArray()));
        } catch (IOException e) {
            isSuccess = false;
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, base.getErrorExport());
        } finally {
            log.info("End export excel {} ID: {}-{}. Time: {} ms", isSuccess, uuid, startTime, (System.currentTimeMillis() - startTime));
        }
    }

    private void setAutoSizeColumn(Field[] fields, SXSSFSheet sheet) {
        sheet.trackAllColumnsForAutoSizing();
        for (int i = 0; i < fields.length + 1; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private static CellStyle xssfCellStyle(Font normalFont, SXSSFWorkbook workbook, BaseExportExcel baseExportExcel) {
        var normalStyle = workbook.createCellStyle();
        normalStyle.setFont(normalFont);
        normalStyle.setAlignment(baseExportExcel.getHorizontalAlignment());
        normalStyle.setVerticalAlignment(baseExportExcel.getVerticalAlignment());
        normalStyle.setWrapText(baseExportExcel.getIsWrapText());
        normalStyle.setBorderTop(baseExportExcel.getBorderHeaderTop());
        normalStyle.setBorderLeft(baseExportExcel.getBorderHeaderLeft());
        normalStyle.setBorderBottom(baseExportExcel.getBorderHeaderBottom());
        normalStyle.setBorderRight(baseExportExcel.getBorderHeaderRight());

        return normalStyle;
    }

    private <T> void setValueRowNormal(SXSSFSheet sheet, List<T> tList, AtomicInteger rowNum, Field[] fields, CellStyle normalStyle, boolean bool, BaseExportExcel baseExportExcel) {
        var autoSTT = new AtomicInteger(1);
        tList.forEach(p -> {
            var rowFill = sheet.createRow(rowNum.getAndIncrement());
            rowFill.createCell(0);
            rowFill.getCell(0).setCellStyle(normalStyle);
            rowFill.getCell(0).setCellValue(autoSTT.get());
            autoSTT.getAndIncrement();
            var cellNormal = new AtomicInteger(1);
            for (var field : fields) {
                try {
                    field.setAccessible(bool);
                    if (getFieldIgnore(baseExportExcel, field)) {
                        continue;
                    }
                    var value = field.get(p);
                    rowFill.createCell(cellNormal.get());

                    setValueCellInRowNormal(value, rowFill, cellNormal, baseExportExcel.getValueCellNull());

                    rowFill.getCell(cellNormal.get()).setCellStyle(normalStyle);
                    cellNormal.getAndIncrement();
                } catch (IllegalAccessException e) {
                    log.info(new Date().toString());
                }
            }
        });
    }

    private boolean getFieldIgnore(BaseExportExcel baseExportExcel, Field field) {
        if (!baseExportExcel.getFieldIgnoreSet().isEmpty()) {
            for (var s : baseExportExcel.getFieldIgnoreSet()) {
                if (field.getName().trim().toUpperCase(Locale.ROOT).equals(s.trim().toUpperCase(Locale.ROOT))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void setValueCellInRowNormal(Object value, SXSSFRow rowFill, AtomicInteger cellNormal, String valueCellNull) {
        if (value == null) {
            rowFill.getCell(cellNormal.get()).setCellValue(valueCellNull);
        } else if (value instanceof Double) {
            rowFill.getCell(cellNormal.get()).setCellValue((Double) value);
        } else if (value instanceof Integer) {
            rowFill.getCell(cellNormal.get()).setCellValue((Integer) value);
        } else if (value instanceof Float) {
            rowFill.getCell(cellNormal.get()).setCellValue((Float) value);
        } else if (value instanceof BigDecimal) {
            rowFill.getCell(cellNormal.get()).setCellValue(((BigDecimal) value).doubleValue());
        } else if (value instanceof Long) {
            rowFill.getCell(cellNormal.get()).setCellValue((Long) value);
        } else {
            rowFill.getCell(cellNormal.get()).setCellValue(value.toString());
        }
    }

    private void validate(BaseExportExcel baseExportExcel, List<String> header, List<?> tList, Class<?> tClass) {
        if (tList == null || header == null || header.isEmpty() || tClass == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, baseExportExcel.getErrorBadRequest());
        }
    }
}
