package com.example.untiled1.service;


import com.example.untiled1.repository.impl.TutorialDTORepository;
import com.example.untiled1.request.TutorialRq;
import com.example.untiled1.response.TutorialRp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TutorialService {

    @Autowired
    TutorialDTORepository repository;

    public List<TutorialRp> searchAll() throws SQLException {
        ResultSet rs = repository.getAll1();
        if (rs == null) {
            throw new RuntimeException("ko có dl");
        }
        List<TutorialRp> records=new ArrayList<TutorialRp>();
        int i=1;
        while(rs.next()){
            TutorialRp arr = new TutorialRp();
//             arr = rs.getObject(i,TutorialRp.class);
            arr.setId(rs.getLong("ID"));
            arr.setDescription(rs.getString("DESCRIPTION"));
            arr.setTitle(rs.getString("TITLE"));
            arr.setStatus(rs.getString("STATUS"));
            records.add(arr);
            i++;
        }
        return records;
    }


    public TutorialRp searchById(Long id) throws SQLException {
        ResultSet rs = repository.getById(id);
        if (rs == null) {
            throw new RuntimeException("ko có dl");
        }
       TutorialRp record = new TutorialRp();
        while(rs.next()){
            record.setId(rs.getLong("ID"));
            record.setDescription(rs.getString("DESCRIPTION"));
            record.setTitle(rs.getString("TITLE"));
            record.setStatus(rs.getString("STATUS"));
        }
        return record;
    }

    public List<TutorialRp> searchByTitle(TutorialRp tutorialRp) throws SQLException {
        ResultSet rs = repository.searchByTitle(tutorialRp);
        if (rs == null) {
            throw new RuntimeException("ko có dl");
        }
        List<TutorialRp> records=new ArrayList<TutorialRp>();
        while(rs.next()){
            TutorialRp arr = new TutorialRp();
//             arr = rs.getObject(i,TutorialRp.class);
            arr.setId(rs.getLong("ID"));
            arr.setDescription(rs.getString("DESCRIPTION"));
            arr.setTitle(rs.getString("TITLE"));
            arr.setStatus(rs.getString("STATUS"));
            records.add(arr);
        }
        return records;
    }


    public TutorialRp updateById(Long id, TutorialRp tutorialRp) throws SQLException {
        ResultSet rs = repository.updateById(id,tutorialRp);
        if (rs == null) {
            throw new RuntimeException("ko có dl");
        }
        TutorialRp record = new TutorialRp();
        while(rs.next()){
            record.setId(rs.getLong("ID"));
            record.setDescription(rs.getString("DESCRIPTION"));
            record.setTitle(rs.getString("TITLE"));
            record.setStatus(rs.getString("STATUS"));
        }
        return record;
    }

    public TutorialRp createById(TutorialRp tutorialRp) throws SQLException {
        ResultSet rs = repository.createById(tutorialRp);
        if (rs == null) {
            throw new RuntimeException("ko có dl");
        }
        TutorialRp record = new TutorialRp();
        while(rs.next()){
            record.setId(rs.getLong("ID"));
            record.setDescription(rs.getString("DESCRIPTION"));
            record.setTitle(rs.getString("TITLE"));
            record.setStatus(rs.getString("STATUS"));
        }
        return record;
    }

    public void deleteById(Long id) throws SQLException {
        ResultSet rs = repository.deleteById(id);
        if (rs == null) {
            throw new RuntimeException("ko có dl");
        }

    }

    public void deleteAll() throws SQLException {
        ResultSet rs = repository.deleteAll();
        if (rs == null) {
            throw new RuntimeException("ko có dl");
        }

    }




}
