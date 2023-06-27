package com.example.untiled1.service;


import com.example.untiled1.repository.impl.PhongBanRepository;
import com.example.untiled1.response.PhongBanRp;
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
public class PhongBanService {

    @Autowired
    PhongBanRepository repository;

    public List<PhongBanRp> searchAll() throws SQLException {
        List<PhongBanRp> rs = repository.getAll1();
        return rs;
    }

}
