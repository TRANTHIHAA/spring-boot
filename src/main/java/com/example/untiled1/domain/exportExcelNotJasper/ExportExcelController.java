package com.example.untiled1.domain.exportExcelNotJasper;

import com.example.untiled1.domain.tutorial.response.TutorialRp;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@CrossOrigin
@RestController
@RequestMapping("/report-no-jasper")
public class ExportExcelController {
    @Autowired
    private TutorialExportExcelService services;

    @GetMapping("/view-report")
    @ResponseStatus
//    @CrossOrigin(exposedHeaders = "Content-Disposition")
    public ResponseEntity<InputStreamResource> report(TutorialRp request)  {
        return ResponseEntity.ok().headers(services.getHeader()).body(services.exportExcel(request));
    }
}
