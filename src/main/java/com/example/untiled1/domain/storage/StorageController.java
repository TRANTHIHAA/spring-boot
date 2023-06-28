package com.example.untiled1.domain.storage;

import com.example.untiled1.global.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class StorageController {
    @Autowired
    private Storage storage;

    @ResponseStatus
    @PostMapping("/import-excel")
    public ResponseEntity<?> importExcels(@ModelAttribute MultipartFile partFile) throws SQLException, IOException {
        storage.uploadToPublicBucket(Objects.requireNonNull(partFile.getOriginalFilename()).replace(".xlsx", "")+".xlsx", partFile.getInputStream());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
