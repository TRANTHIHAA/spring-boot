package com.example.untiled1.domain.exportExcel;

import com.example.untiled1.global.service.JasperPrintService;
import com.example.untiled1.global.utils.FileEx;
import com.example.untiled1.global.utils.ReportUtil;
import com.example.untiled1.global.utils.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.lowagie.text.BadElementException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReportService
{

    @Autowired
    ReportRepository reportRepo;

//    public JsonNode getDanhMuc(KeySearchListObj objInput) {
//        ArrayNode arrResult = reportRepo.getDanhMuc(objInput.getStrParam());
//        return ReportUtil.getJsonNodeAsPageable(arrResult,objInput.getPage(), objInput.getSize());
//    }
//
//    public JsonNode search(KeySearchListObj objInput) {
//        ArrayNode arrResult = reportRepo.search(objInput);
//        return ReportUtil.getJsonNodeAsPageable(arrResult,
//                objInput.getPage(), objInput.getSize());
//    }

//    public ReportRequestInfo viewReportOffline(KeySearchListObj objInput) throws Exception {
//        ReportRequestInfo reportInfo = reportRepo.saveReportRequest(
//                objInput, "strToken");
//        if (reportInfo == null) {
//            throw new BadRequestException("Không tìm thấy thông tin khai báo cho báo cáo");
//        }
//        return reportInfo;
//    }


    public ResponseEntity<byte[]> viewReport(KeySearchListObj objInput, String strToken) throws Exception {
        JRResultSetDataSource jrRS = null;

        // B1: Luu xuong payload. Nhan ve thong tin tham so bao cao ==> report_request_info
        ReportRequestInfo reportInfo = reportRepo.saveReportRequest(
                objInput, strToken);
        if (reportInfo == null) {
            throw new BadElementException("Không tìm thấy thông tin khai báo cho báo cáo");
        }

        // B3: Chay cau sql tong hop bao cao. Nhan ve 1 ResultSet
        ResultSet rs = reportRepo.getReportData(reportInfo.getReportId());
        if (rs == null) {
            throw new BadElementException("Error lấy dữ liệu báo cáo ");
        }

        // B4: Build tham so bao cao jasper report
        Map<String, Object> mapReportParam;
        if (reportInfo.getStrJasperParam() == null || reportInfo.getStrJasperParam().isEmpty()) {
            mapReportParam = null;
        } else {
            mapReportParam = buildParameterForReport(reportInfo.getStrJasperParam());
        }

        // B5: Tạo file báo cáo
        byte[] arr;
        try {
            jrRS = new JRResultSetDataSource(rs);
            InputStream fonte = FileEx.getFileInputStream("classpath:report/" + reportInfo.getReportTemplateFile(),
                    true,
                    "Không tìm thấy file template report");

            JasperReport report = JasperCompileManager.compileReport(fonte);
            JasperPrint print = JasperFillManager.fillReport(report, mapReportParam, jrRS);
            arr = JasperPrintService.jasperPrintToFile(print, org.apache.commons.lang3.StringUtils.isBlank(objInput.getFileType()) ? "PDF" : objInput.getFileType());
        } catch (Exception e) {
            throw new BadElementException("Không thể xuất file." + e.getMessage());
        } finally {
            // Close
            rs.close();
        }

        // B6: Trả ra kết quả
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment;filename=\"%s_%s.%s\"",
                        reportInfo.getReportCode(),
                        StringUtils.formatDate3(new Date()),
                        objInput.getFileType())
                        )
                .contentLength(arr.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(arr);
    }

    public Map<String, Object> buildParameterForReport(String strParameterValue) {
        if (strParameterValue == null || strParameterValue.isEmpty()) {
            return null;
        }

        Map<String, Object> mapParam = new HashMap<>();
        String[] lines;
        String line;
        String[] arrKeyValue;

        lines = strParameterValue.split("\\r?\\n");
        for (String s : lines) {
            line = s;
            arrKeyValue = line.split("#");
            if (arrKeyValue.length == 1) {
                mapParam.put(arrKeyValue[0], "");
            } else if (arrKeyValue.length >= 2) {
                mapParam.put(arrKeyValue[0], arrKeyValue[1]);
            }
        }

        return mapParam;
    }

}
