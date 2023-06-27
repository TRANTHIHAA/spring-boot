package com.example.untiled1.service;


import com.example.untiled1.repository.impl.TutorialDTORepository;
import com.example.untiled1.response.TutorialRp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Slf4j
@Service
public class TutorialService {

    @Autowired
    TutorialDTORepository repository;

    public List<TutorialRp> searchAll() throws SQLException {
        return repository.getAll1();
    }

// cách 2:
//    public List<TutorialRp> searchAllC2() throws SQLException {
//        List<TutorialRp> rs = repository.getAll1();
//        List<TutorialRp> resultList = new ArrayList<>();
//
//        if (rs == null) {
//            throw new RuntimeException("ko có dl");
//        }
//        while(rs.next()){
//            TutorialRp arr = new TutorialRp();
//            arr.setId(BigDecimal.valueOf(rs.getLong("ID")));
//            arr.setDescription(rs.getString("DESCRIPTION"));
//            arr.setTitle(rs.getString("TITLE"));
//            arr.setStatus(rs.getString("STATUS"));
//            resultList.add(arr);
//        }
//        return resultList;
//    }


    public TutorialRp searchById(Long id) throws SQLException {
        TutorialRp rs = repository.getById(id);
        return rs;
    }

    public List<TutorialRp> searchByTitle(TutorialRp tutorialRp) throws SQLException {
        List<TutorialRp> rs = repository.searchByTitle(tutorialRp);
        return rs;
    }


    public TutorialRp updateById(Long id, TutorialRp tutorialRp) throws SQLException {
        TutorialRp rs = repository.updateById(id,tutorialRp);
        if (rs == null) {
            throw new RuntimeException("ko có dl");
        }
        return rs;
    }

    public TutorialRp createById(TutorialRp tutorialRp) throws SQLException {
        TutorialRp rs = repository.createById(tutorialRp);
        if (rs == null) {
            throw new RuntimeException("ko có dl");
        }
        return rs;
    }

    public void deleteById(Long id) throws SQLException {
        TutorialRp rs = repository.deleteById(id);
        if (rs == null) {
            throw new RuntimeException("ko có dl");
        }

    }

    public void deleteAll() throws SQLException {
        TutorialRp rs = repository.deleteAll();
        if (rs == null) {
            throw new RuntimeException("ko có dl");
        }

    }

}
