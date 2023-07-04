package com.example.untiled1.domain.exportExcel;

import com.example.untiled1.global.base.BaseRepositoryImpl;

import com.example.untiled1.global.utils.ReportUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@Slf4j
@Transactional
public class ReportRepository extends BaseRepositoryImpl<ReportRequestInfo> {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    public ReportRequestInfo saveReportRequest(KeySearchListObj objInput, String strToken) {
        return excuteResultSetUsingSp( //
                ReportRequestInfo.class //
                ,true
                ,1
                , "PKG_REPORT.SP_SAVE_REPORT_REQUEST" //
                , objInput.getReportCode()
                , objInput.getFileType()
                , ReportUtil.convertObject2Json(objInput)
                , strToken
                ,null
        ).get(0);
    }

    public ResultSet getReportData(String reportId) {
        return  excuteResultSet(
                true
                ,1
                , "PKG_REPORT.SP_GET_REPORT_DATA"
                , reportId
                ,null
        );
    }
}
