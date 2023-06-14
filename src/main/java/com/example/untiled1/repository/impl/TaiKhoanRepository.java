package com.example.untiled1.repository.impl;

import com.example.untiled1.request.TutorialRq;
import com.example.untiled1.response.TaiKhoanRp;
import com.example.untiled1.response.TutorialRp;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
@Transactional
public class TaiKhoanRepository {

    @PersistenceContext
    protected EntityManager pcEntityManagerDefault;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${spring.datasource.url}")
    String oracleUrl;

    @Value("${spring.datasource.password}")
    String oraclePassword;

    @Value("${spring.datasource.username}")
    String oracleUsername;

    /* Calling Stored Procedure using JdbcTemplate */
    public ResultSet createUser(TaiKhoanRp taiKhoanRp) {
        return (ResultSet) excuteResultSetUsingSp(
                true,
                1,
                "aaa_tai_khoan.create_user"
                ,taiKhoanRp.getTenTaiKhoan()
                ,taiKhoanRp.getEmail()
                ,taiKhoanRp.getHoTen()
                ,taiKhoanRp.getMatKhau()
                ,taiKhoanRp.getNguoiTao()
                ,taiKhoanRp.getNguoiSua()
                ,taiKhoanRp.getPhongBan()
                ,taiKhoanRp.getTinhTrang()
                ,null
        );
    }

    public ResultSet deleteById(Long id) {
        return (ResultSet) excuteResultSetUsingSp(
                true,
                1,
                "aaa_tai_khoan.user_delete"
                , id
                ,null
        );
    }

    public ResultSet updateById(Long id, TaiKhoanRp taiKhoanRp) {
        return (ResultSet) excuteResultSetUsingSp(
                true,
                1,
                "aaa_tai_khoan.user_update"
                , id
                ,taiKhoanRp.getTenTaiKhoan()
                ,taiKhoanRp.getTinhTrang()
                ,taiKhoanRp.getHoTen()
                ,taiKhoanRp.getMatKhau()
                ,taiKhoanRp.getPhongBan()
                ,taiKhoanRp.getEmail()
                ,taiKhoanRp.getNguoiSua()
                ,null
        );
    }

    public ResultSet getById(Long id) {
        return (ResultSet) excuteResultSetUsingSp(
                true,
                1,
                "aaa_tai_khoan.find_user_by_id"
                , id
                ,null
        );
    }

    public ResultSet getAll() {
        return (ResultSet) excuteResultSetUsingSp(
                true,
                1,
                "aaa_tai_khoan.search_all_tai_khoan"
                , (Object) null
        );
    }

    public ResultSet excuteResultSetUsingSp(
            boolean isDbOracle,
            int type,
            String procedureName,
            Object... params) {

        OracleConnection cnn = null;
        try {
            OracleDataSource odcDataSource = new OracleDataSource();
            odcDataSource.setURL(oracleUrl);
            odcDataSource.setUser(oracleUsername);
            odcDataSource.setPassword(oraclePassword);
            cnn = (OracleConnection) odcDataSource.getConnection();
        } catch (Exception e) {
            System.out.println("DB connection Exception");
            e.printStackTrace();
        }
        CallableStatement stmt = null;
        ResultSet rs = null;
        int iTotalParam = 0;
        if (params != null) {
            iTotalParam = params.length;
        }

        String sQueryString = StringUtils.leftPad("", isDbOracle && type != 1 ? iTotalParam + 1 : iTotalParam, "?").replace("?", ",?");
        if (sQueryString.startsWith(",")) {
            sQueryString = sQueryString.substring(1);
        }
        sQueryString = String.format("{call %s(%s) }", procedureName, sQueryString);
        System.out.println("222222222222:   "+sQueryString);
        System.out.println("222222222222:   "+iTotalParam);
        try {
            stmt = cnn.prepareCall(sQueryString);

            int i = 0;

            label383:
            while(true) {
                if (i + 1 >= iTotalParam) {
                    switch (type) {
                        case 1:
                        case 2:
                        case 5:
                        case 6:
                        case 7:
                        case 3:
                            if (isDbOracle) {
                                stmt.registerOutParameter(iTotalParam , Types.REF_CURSOR);
                                stmt.execute();
                                rs = (ResultSet)stmt.getObject(iTotalParam);
                            } else {
                                rs = stmt.executeQuery();
                            }
                            break label383;
                        case 4:
                            stmt.execute();
                            break label383;
                        default:
                            break label383;
                    }
                }

                if (params[i] == null) {
                    stmt.setNull(i+1, 0);
                } else if (params[i].getClass().equals(java.util.Date.class)) {
                    stmt.setTimestamp(i+1 , convertUtilDate2SqlTimestamp((java.util.Date)params[i]));
                } else if (params[i].getClass().equals(Date.class)) {
                    stmt.setTimestamp(i+1 ,convertSqlDate2SqlTimestamp((Date)params[i]));
                } else if (params[i].getClass().equals(Timestamp.class)) {
                    stmt.setTimestamp(i +1, (Timestamp)params[i]);
                } else {
                    stmt.setObject(i+1 , params[i]);
                }

                ++i;
            }

            return rs;
        } catch (Exception var13) {
            throw new RuntimeException(var13.getMessage());
        }


    }

    public static Timestamp convertUtilDate2SqlTimestamp(java.util.Date tInput) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(tInput);
        return new Timestamp(cal.getTimeInMillis());
    }


    private int convertClassToOracleTypes(Class<?> clazz) {
        if (clazz == Number.class) {
            return 2;
        } else {
            return clazz == java.util.Date.class ? 91 : 12;
        }
    }
    public static long getTimeEx(Date tInput) {
        return tInput.getTime();
    }
    public static Timestamp convertSqlDate2SqlTimestamp(Date tInput) {
        return new Timestamp(getTimeEx(tInput));
    }
}
