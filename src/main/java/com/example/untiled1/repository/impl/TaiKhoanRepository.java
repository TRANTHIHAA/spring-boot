package com.example.untiled1.repository.impl;

import com.example.untiled1.request.TutorialRq;
import com.example.untiled1.response.TaiKhoanRp;
import com.example.untiled1.response.TutorialRp;
import com.example.untiled1.service.BaseRepositoryImpl;
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
public class TaiKhoanRepository extends BaseRepositoryImpl<TaiKhoanRp> {


    public TaiKhoanRp createUser(TaiKhoanRp taiKhoanRp) {
        return  excuteResultSetUsingSp(
                TaiKhoanRp.class,
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
        ).get(0);
    }

    public TaiKhoanRp deleteById(Long id) {
        return excuteResultSetUsingSp(
                TaiKhoanRp.class,
                true,
                1,
                "aaa_tai_khoan.user_delete"
                , id
                ,null
        ).get(0);
    }

    public TaiKhoanRp updateById(Long id, TaiKhoanRp taiKhoanRp) {
        return excuteResultSetUsingSp(
                TaiKhoanRp.class,
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
        ).get(0);
    }

    public TaiKhoanRp getById(Long id) {
        return  excuteResultSetUsingSp(
                TaiKhoanRp.class,
                true,
                1,
                "aaa_tai_khoan.find_user_by_id"
                , id
                ,null
        ).get(0);
    }

    public List<TaiKhoanRp> getAll() {
        return  excuteResultSetUsingSp(
                TaiKhoanRp.class,
                true,
                1,
                "aaa_tai_khoan.search_all_tai_khoan"
                , (Object) null
        );
    }

}
