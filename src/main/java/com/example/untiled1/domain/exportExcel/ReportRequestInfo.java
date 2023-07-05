package com.example.untiled1.domain.exportExcel;

import com.example.untiled1.global.base.ConditionalBaseEo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Data
public class ReportRequestInfo extends ConditionalBaseEo<ReportRequestInfo> {
    private String reportId;

    private String reportCode;

    private String reportTemplateFile;


    private String jasperParam;

//    @ApiModelProperty(value = "Chuỗi tham số jasper report")
//    private String dataType;
//
//    @ApiModelProperty(value = "Chuỗi tham số jasper report")
//    private String dataTypes;
//
//    @ApiModelProperty(value = "Chuỗi tham số jasper report")
//    private Integer maxRow;
//
//    @ApiModelProperty(value = "Chuỗi tham số jasper report")
//    private Integer minRow;
//
//    @ApiModelProperty(value = "Chuỗi tham số jasper report")
//    private Integer maxCell;
//
//    @ApiModelProperty(value = "Chuỗi tham số jasper report")
//    private Integer minCell;
//
//    @ApiModelProperty(value = "Chuỗi tham số jasper report")
//    private String importExcelPr;
//
//    @ApiModelProperty(value = "Chuỗi tham số jasper report")
//    private String deleteCol;
//
//    @ApiModelProperty(value = "Chuỗi tham số jasper report")
//    private String deleteRow;
//
//    @ApiModelProperty(value = "mã đơn vị ca")
//    private String maDvCa;
//
//    @ApiModelProperty(value = "id cấu hình báo cáo")
//    private String nhomBC;
//
//    @ApiModelProperty(value = "id cấu hình báo cáo")
//    private String idCHBA;
//
//    @ApiModelProperty(value = "id cấu hình báo cáo")
//    private String tenBC;
//
//    @ApiModelProperty(value = "id cấu hình báo cáo")
//    private String loaiBC;
//
//    @ApiModelProperty(value = "id cấu hình báo cáo")
//    private String namBC;
//
//    @ApiModelProperty(value = "id cấu hình báo cáo")
//    private String chuKy;
}
