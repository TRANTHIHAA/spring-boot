package com.example.untiled1.domain.phongBan.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class PhongBanRp {
    private BigDecimal id;
    private BigDecimal trangThai;
    private String tenPhongBan;
    private Date ngayTao;
    private String nguoiTao;
    private Date ngaySua;
    private String nguoiSua;
}
