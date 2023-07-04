package com.example.untiled1.domain.exportExcel;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@RestController
@RequestMapping("/report")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
@CrossOrigin
//@SecurityRequirement(name = "Authorization")
//@SecurityRequirement(name = "cApiKey")
@Api(tags = "export excel")
public class ReportController {

    @Autowired
    private ReportService services;

    @Operation(description = "Api lấy báo cáo", summary = "Api lấy báo cáo")
    @ResponseStatus
    @GetMapping("/view-report")
//    @CrossOrigin(exposedHeaders = "Content-Disposition")
    public ResponseEntity<byte[]> viewReport(
            KeySearchListObj objInput) throws Exception {
        return services.viewReport(objInput, "token");
    }
}
