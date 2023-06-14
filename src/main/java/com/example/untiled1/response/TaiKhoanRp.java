package com.example.untiled1.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaiKhoanRp {
    private Long id;
    private Long tinhTrang;
    private String tenTaiKhoan;
    private String hoTen;
    private String email;
    private Long phongBan;
    private String tenPhongBan;
    private String matKhau;
    private String ngayTao;
    private String nguoiTao;
    private String ngaySua;
    private String nguoiSua;
}
