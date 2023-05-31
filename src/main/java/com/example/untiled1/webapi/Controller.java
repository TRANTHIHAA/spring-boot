package com.example.untiled1.webapi;

import com.example.untiled1.request.TutorialRq;
import com.example.untiled1.response.TutorialRp;
import com.example.untiled1.service.TutorialService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.sql.SQLException;
import java.util.List;
@CrossOrigin
@RestController
public class Controller {

    @Autowired
    private TutorialService tutorialService;

    @GetMapping("/search")
    public ResponseEntity<List<TutorialRp>> search() throws SQLException {
        return ResponseEntity.ok(tutorialService.searchAll());
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<TutorialRp> searchById( @PathVariable Long id) throws SQLException {
        return ResponseEntity.ok(tutorialService.searchById(id));
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<TutorialRp> updateById( @PathVariable Long id, @RequestBody TutorialRp tutorialRp) throws SQLException {
        return ResponseEntity.ok(tutorialService.updateById(id,tutorialRp));
    }

    @DeleteMapping("/tutorials/{id}")
    public void deleteById( @PathVariable Long id) throws SQLException {
        tutorialService.deleteById(id);
    }

    @DeleteMapping("/tutorials")
    public void deleteAll() throws SQLException {
        tutorialService.deleteAll();
    }

    @PostMapping("/tutorials")
    public ResponseEntity<TutorialRp> createById( @RequestBody TutorialRp tutorialRp) throws SQLException {
        return ResponseEntity.ok(tutorialService.createById(tutorialRp));
    }

    @GetMapping("/tutorials")
    public ResponseEntity<List<TutorialRp>> searchByTitle( @ModelAttribute TutorialRp tutorialRp) throws SQLException {
        return ResponseEntity.ok(tutorialService.searchByTitle(tutorialRp));
    }
}
