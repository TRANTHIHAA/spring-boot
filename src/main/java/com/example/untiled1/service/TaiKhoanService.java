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
        ResultSet rs = repository.getAll();
        if (rs == null) {
            throw new RuntimeException("ko có dl");
        }
        List<TaiKhoanRp> records=new ArrayList<TaiKhoanRp>();
//        int i=1;
        while(rs.next()){
            TaiKhoanRp arr = new TaiKhoanRp();
//             arr = rs.getObject(i,PhongBanRp.class);
            arr.setId(rs.getLong("ID"));
            arr.setEmail(rs.getString("email"));
            arr.setHoTen(rs.getString("ho_ten"));
            arr.setTenTaiKhoan(rs.getString("ten_taikhoan"));
            arr.setNgayTao(rs.getString("ngay_tao"));
            arr.setNguoiTao(rs.getString("nguoi_tao"));
            arr.setTenPhongBan(rs.getString("ten_phongban"));
            arr.setTinhTrang(rs.getLong("tinh_trang"));
            arr.setNguoiSua(rs.getString("nguoi_sua"));
            arr.setNgaySua(rs.getString("ngay_sua"));
            records.add(arr);
//            i++;
        }
        return records;
    }
//
//
    public TaiKhoanRp searchById(Long id) throws SQLException {
        ResultSet rs = repository.getById(id);
        if (rs == null) {
            throw new RuntimeException("ko có dl");
        }
        TaiKhoanRp record = new TaiKhoanRp();
        while(rs.next()){
            record.setId(rs.getLong("ID"));
            record.setTenTaiKhoan(rs.getString("ten_taikhoan"));
            record.setHoTen(rs.getString("ho_ten"));
            record.setEmail(rs.getString("email"));
            record.setPhongBan(rs.getLong("phongban"));
            record.setMatKhau(rs.getString("mat_khau"));
            record.setTenPhongBan(rs.getString("ten_phongban"));
            record.setTinhTrang(rs.getLong("tinh_trang"));
        }
        return record;
    }
//
//
//
    public TaiKhoanRp updateById(Long id, TaiKhoanRp taiKhoanRp) throws SQLException {
        ResultSet rs = repository.updateById(id,taiKhoanRp);
        if (rs == null) {
            throw new RuntimeException("ko có dl");
        }
        TaiKhoanRp record = new TaiKhoanRp();
        while(rs.next()){
            record.setId(rs.getLong("ID"));
            record.setTenTaiKhoan(rs.getString("ten_taikhoan"));
            record.setHoTen(rs.getString("ho_ten"));
            record.setEmail(rs.getString("email"));
            record.setPhongBan(rs.getLong("phongban"));
            record.setMatKhau(rs.getString("mat_khau"));
            record.setTenPhongBan(rs.getString("ten_phongban"));
            record.setTinhTrang(rs.getLong("tinh_trang"));
        }
        return record;
    }

    public TaiKhoanRp createUser(TaiKhoanRp taiKhoanRp) throws SQLException {
        ResultSet rs = repository.createUser(taiKhoanRp);
        if (rs == null) {
            throw new RuntimeException("ko có dl");
        }
        TaiKhoanRp record = new TaiKhoanRp();
        while(rs.next()){
            record.setId(rs.getLong("ID"));
            record.setEmail(rs.getString("email"));
            record.setHoTen(rs.getString("ho_ten"));
            record.setTenTaiKhoan(rs.getString("ten_taikhoan"));
            record.setNgayTao(rs.getString("ngay_tao"));
            record.setNguoiTao(rs.getString("nguoi_tao"));
            record.setPhongBan(rs.getLong("phongban"));
            record.setTinhTrang(rs.getLong("tinh_trang"));
        }
        return record;
    }

    public TaiKhoanRp deleteById(Long id) throws SQLException {
        ResultSet rs = repository.deleteById(id);
        if (rs == null) {
            throw new RuntimeException("ko có dl");
        }
        TaiKhoanRp record = new TaiKhoanRp();
        while(rs.next()){
            record.setId(rs.getLong("ID"));
            record.setEmail(rs.getString("email"));
            record.setHoTen(rs.getString("ho_ten"));
            record.setTenTaiKhoan(rs.getString("ten_taikhoan"));
            record.setNgayTao(rs.getString("ngay_tao"));
            record.setNguoiTao(rs.getString("nguoi_tao"));
            record.setPhongBan(rs.getLong("phongban"));
            record.setTinhTrang(rs.getLong("tinh_trang"));
        }
        return record;
    }
//
//    public void deleteAll() throws SQLException {
//        ResultSet rs = repository.deleteAll();
//        if (rs == null) {
//            throw new RuntimeException("ko có dl");
//        }
//
//    }




}
