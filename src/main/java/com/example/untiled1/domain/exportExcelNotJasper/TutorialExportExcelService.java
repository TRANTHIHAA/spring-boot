package com.example.untiled1.domain.exportExcelNotJasper;

import com.example.untiled1.domain.tutorial.TutorialDTORepository;
import com.example.untiled1.domain.tutorial.dto.TutorialDTO;
import com.example.untiled1.domain.tutorial.request.TutorialRq;
import com.example.untiled1.domain.tutorial.response.TutorialRp;
import com.example.untiled1.global.base.BaseMessages;
import com.example.untiled1.global.constants.Common;
import com.example.untiled1.global.utils.DateUtils;
import com.example.untiled1.global.utils.JsonUtils;
import com.example.untiled1.global.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TutorialExportExcelService {
    private final TutorialDTORepository repository;
    private final BaseExportExcelService baseExcelService;
    private final BaseMessages baseMessages;

    public InputStreamResource exportExcel(TutorialRp request) {
//        request.setIsFetchAll(true);
        List<String> header = new ArrayList<>();
       List<TutorialRp> data = this.repository.searchByTitle(request);
        Collections.addAll(header
                , baseMessages.getMessage(BaseMessages.Tutorial.HEADER_REPORT_EXCEL)
                        .split(Pattern.quote(Common.Separator.COMMA)));

        List<TutorialDTO> excelList = data.stream().map(this::toReportDto).collect(Collectors.toList());

        ExcelDetail excelDetail = new ExcelDetail();
        excelDetail.setHeaders(header);
        excelDetail.setTList(excelList);
        excelDetail.setTClass(TutorialDTO.class);
        excelDetail.setSheetName(baseMessages.getMessage(BaseMessages.Tutorial.SHEET_NAME));

        return baseExcelService.export(excelDetail);
    }

    public TutorialDTO toReportDto(TutorialRp entity) {
       TutorialDTO tutorialDTO = new TutorialDTO();
        tutorialDTO.setId(entity.getId());
        tutorialDTO.setDescription(entity.getDescription());
        tutorialDTO.setStatus(entity.getStatus());
        tutorialDTO.setTitle(entity.getTitle());

        return tutorialDTO;
    }
    public HttpHeaders getHeader()  {
        String fileName = baseMessages.getMessage(BaseMessages.Tutorial.FILE_NAME) + "_"
                + DateUtils.dateToString(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS);
        return this.baseExcelService.getHeader(StringUtils.toFileName(fileName));
    }
}
