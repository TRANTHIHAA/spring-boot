package com.example.untiled1.domain.taiKhoan;

import com.example.untiled1.domain.taiKhoan.response.TaiKhoanRp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

    public void deleteById(Long id) throws SQLException {
         repository.deleteById(id);
    }


}
