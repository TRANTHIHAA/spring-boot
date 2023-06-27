package com.example.untiled1.repository.impl;

import com.example.untiled1.response.PhongBanRp;
import com.example.untiled1.service.BaseRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Slf4j
@Transactional
public class PhongBanRepository extends BaseRepositoryImpl<PhongBanRp> {



    public List<PhongBanRp> getAll1() {
        return  excuteResultSetUsingSp(
                PhongBanRp.class,
                true,
                1,
                "aaa_tai_khoan.search_all_phong_ban"
                , (Object) null
        );
    }

    public PhongBanRp getById(Long id) {
        return  excuteResultSetUsingSp(
                PhongBanRp.class,
                true,
                1,
                "AAA.find_by_id"
                , id
                ,null
        ).get(0);
    }

}
