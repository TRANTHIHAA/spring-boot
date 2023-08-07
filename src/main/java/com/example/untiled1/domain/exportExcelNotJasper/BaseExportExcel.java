package com.example.untiled1.domain.exportExcelNotJasper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseExportExcel {
    @Builder.Default
    private String nameHeaderColumZero = "STT";
    @Builder.Default
    private String valueCellNull = "";
    @Builder.Default
    private String simpleDateFormatStr = "dd-MM-yyyy";
    @Builder.Default
    private PrintOrientation printOrientation = PrintOrientation.LANDSCAPE;
    @Builder.Default
    private String fontName = "Times New Roman";
    @Builder.Default
    private short fontSize = (short) 11;
    @Builder.Default
    private Boolean isSetBoldHeader = true;
    @Builder.Default
    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.CENTER;
    @Builder.Default
    private VerticalAlignment verticalAlignment = VerticalAlignment.CENTER;
    @Builder.Default
    private Boolean isWrapText = false;
    @Builder.Default
    private BorderStyle borderHeaderTop = BorderStyle.THIN;
    @Builder.Default
    private BorderStyle borderHeaderLeft = BorderStyle.THIN;
    @Builder.Default
    private BorderStyle borderHeaderBottom = BorderStyle.THIN;
    @Builder.Default
    private BorderStyle borderHeaderRight = BorderStyle.THIN;
    @Builder.Default
    private short fillForegroundColorHeader = IndexedColors.LIGHT_GREEN.getIndex();
    @Builder.Default
    private FillPatternType fillPatternHeader = FillPatternType.SOLID_FOREGROUND;
    @Builder.Default
    private String errorBadRequest = "tList NOT NULL, header NOT NULL AND NOT EMPTY, tClass NOT NULL";
    @Builder.Default
    private String headerNotEqualField = "HEADER NOT EQUAL FIELD !!!";
    @Builder.Default
    private String errorExport = "ERROR EXPORT !!!";
    @Builder.Default
    private Boolean isAccessible = true;
    @Builder.Default
    private Set<String> fieldIgnoreSet = new HashSet<>();
    @Builder.Default
    private String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
}
