package com.example.untiled1.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class TaiKhoanRp {
    private BigDecimal id;
    private BigDecimal tinhTrang;
    private String tenTaiKhoan;
    private String hoTen;
    private String email;
    private BigDecimal phongBan;
    private String tenPhongBan;
    private String matKhau;
    private Date ngayTao;
    private String nguoiTao;
    private Date ngaySua;
    private String nguoiSua;
}
