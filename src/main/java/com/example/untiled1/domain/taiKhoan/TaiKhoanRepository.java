package com.example.untiled1.domain.taiKhoan;

import com.example.untiled1.domain.taiKhoan.response.TaiKhoanRp;
import com.example.untiled1.global.base.BaseRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public void deleteById(Long id) {
         excuteResultSetUsingSp(
                TaiKhoanRp.class,
                true,
                1,
                "aaa_tai_khoan.user_delete"
                , id
                ,null
        );
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
