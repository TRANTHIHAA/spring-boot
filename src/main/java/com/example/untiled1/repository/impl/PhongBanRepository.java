package com.example.untiled1.repository.impl;

import com.example.untiled1.request.TutorialRq;
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
public class PhongBanRepository {

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

    public Map<String, Object> searchUserByName(TutorialRq searchTerm) {
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.NVARCHAR));

        return jdbcTemplate.call(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement cs = con.prepareCall("{call search_users_proc(?,?,?,?,?)}");
                cs.setLong(  1, searchTerm.getIdRq());
                cs.setString(2, searchTerm.getTitleRq());
                cs.setString(3, searchTerm.getDescriptionRq());
                cs.setString(4, searchTerm.getStatusRq());
                cs.registerOutParameter(5, Types.REF_CURSOR);
                cs.execute();
                ResultSet rs = (ResultSet) cs.getObject(5);
                return cs;
            }
        }, parameters);
    }



    public static String convertObject2Json(Object obj) {
        ObjectMapper objMapper = new ObjectMapper();
        String strResult = "";
        try {

            // get Oraganisation object as a json string
            strResult = objMapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strResult;
    }

    public ResultSet getAll1() {
        return (ResultSet) excuteResultSetUsingSp(
                true,
                1,
                "pkg_tai_khoan.search_all_phong_ban"
                , (Object) null
        );
    }

    public ResultSet searchByTitle(TutorialRp tutorialRp) {
        return (ResultSet) excuteResultSetUsingSp(
                true,
                1,
                "pkg_tai_khoan.search_all_phong_ban"
                ,tutorialRp.getTitle()
                ,(Object) null
        );
    }

    public ResultSet getById(Long id) {
        return (ResultSet) excuteResultSetUsingSp(
                true,
                1,
                "AAA.find_by_id"
                , id
                ,null
        );
    }

    public ResultSet updateById(Long id, TutorialRp tutorialRp) {
        return (ResultSet) excuteResultSetUsingSp(
                true,
                1,
                "AAA.update_by_id"
                , id
                ,tutorialRp.getTitle()
                ,tutorialRp.getStatus()
                ,tutorialRp.getDescription()
                ,null
        );
    }

    public ResultSet createById(TutorialRp tutorialRp) {
        return (ResultSet) excuteResultSetUsingSp(
                true,
                1,
                "AAA.create_tutorials"
                ,tutorialRp.getTitle()
                ,tutorialRp.getStatus()
                ,tutorialRp.getDescription()
                ,null
        );
    }

    public ResultSet deleteById(Long id) {
        return (ResultSet) excuteResultSetUsingSp(
                true,
                1,
                "AAA.tutorials_delete"
                , id
                ,null
        );
    }

    public ResultSet deleteAll() {
        return (ResultSet) excuteResultSetUsingSp(
                true,
                1,
                "AAA.tutorials_delete_all"
                ,(Object) null
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
