package com.example.untiled1.service;

import com.example.untiled1.repository.impl.TaiKhoanRepository;
import com.example.untiled1.response.TaiKhoanRp;
import com.example.untiled1.response.TutorialRp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TaiKhoanService {

    @Autowired
    TaiKhoanRepository repository;

    public List<TaiKhoanRp> searchAll() throws SQLException {
        List<TaiKhoanRp> rs = repository.getAll();
        return rs;
    }
//
//
    public TaiKhoanRp searchById(Long id) throws SQLException {
        TaiKhoanRp record = repository.getById(id);
        return record;
    }
//
//
//
    public TaiKhoanRp updateById(Long id, TaiKhoanRp taiKhoanRp) throws SQLException {
        TaiKhoanRp rs = repository.updateById(id,taiKhoanRp);
        return rs;
    }

    public TaiKhoanRp createUser(TaiKhoanRp taiKhoanRp) throws SQLException {
        TaiKhoanRp record = repository.createUser(taiKhoanRp);
        return record;
    }

    public TaiKhoanRp deleteById(Long id) throws SQLException {
        TaiKhoanRp rs = repository.deleteById(id);
        return rs;
    }

}
