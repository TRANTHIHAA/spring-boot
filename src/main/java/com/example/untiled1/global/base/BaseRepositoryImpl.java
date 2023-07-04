package com.example.untiled1.global.base;

import jakarta.persistence.*;
import jakarta.persistence.PersistenceContext;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BaseRepositoryImpl<T> {

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

    public List<T> mapResultSetToListObject(ResultSet resultSet, Class<T> clazz) throws SQLException {
        List<T> resultList = new ArrayList<>();
        try {
            java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                T object = clazz.getDeclaredConstructor().newInstance();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    String columnName = convertToJv(metaData.getColumnLabel(columnIndex));
                    Object columnValue = resultSet.getObject(columnIndex);
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(object, columnValue);

                }
                resultList.add(object);

            }
            return resultList;
        } catch (Exception e) {
            throw new SQLException("Lỗi chuyển đổi ResultSet thành đối tượng", e);
        }
    }


    public static String convertToJv(String input) {
        String[] words = input.split("_");
        StringBuilder camelCase = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            if (i > 0) {
                char firstChar = Character.toUpperCase(word.charAt(0));
                word = firstChar + word.substring(1);
            }
            camelCase.append(word);
        }
        return camelCase.toString();
    }

    /* Calling Stored Procedure using JdbcTemplate */
    public ResultSet excuteResultSet( boolean isDbOracle,
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
        System.out.println("222222222222:   " + sQueryString);
        System.out.println("222222222222:   " + iTotalParam);
        try {
            stmt = cnn.prepareCall(sQueryString);

            int i = 0;

            label383:
            while (true) {
                if (i + 1 >= iTotalParam) {
                    switch (type) {
                        case 1:
                        case 2:
                        case 5:
                        case 6:
                        case 7:
                        case 3:
                            if (isDbOracle) {
                                stmt.registerOutParameter(iTotalParam, Types.REF_CURSOR);
                                stmt.execute();
                                rs = (ResultSet) stmt.getObject(iTotalParam);
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
                    stmt.setNull(i + 1, 0);
                } else if (params[i].getClass().equals(java.util.Date.class)) {
                    stmt.setTimestamp(i + 1, convertUtilDate2SqlTimestamp((java.util.Date) params[i]));
                } else if (params[i].getClass().equals(java.sql.Date.class)) {
                    stmt.setTimestamp(i + 1, convertSqlDate2SqlTimestamp((java.sql.Date) params[i]));
                } else if (params[i].getClass().equals(Timestamp.class)) {
                    stmt.setTimestamp(i + 1, (Timestamp) params[i]);
                } else {
                    stmt.setObject(i + 1, params[i]);
                }

                ++i;
            }

            return rs;
        } catch (Exception var13) {
            throw new RuntimeException(var13.getMessage());
        }


    }


    public List<T> excuteResultSetUsingSp(
            Class<T> clazz,
            boolean isDbOracle,
            int type,
            String procedureName,
            Object... params)  {

        try {
            ResultSet rs = excuteResultSet(isDbOracle,type,procedureName,params);
            return mapResultSetToListObject(rs, clazz);
        } catch (SQLException e) {
            return null;
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

    public static long getTimeEx(java.sql.Date tInput) {
        return tInput.getTime();
    }

    public static Timestamp convertSqlDate2SqlTimestamp(java.sql.Date tInput) {
        return new Timestamp(getTimeEx(tInput));
    }


}
