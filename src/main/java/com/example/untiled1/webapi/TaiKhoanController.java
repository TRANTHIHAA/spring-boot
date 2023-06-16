package com.example.untiled1.webapi;

import com.example.untiled1.response.PhongBanRp;
import com.example.untiled1.response.TaiKhoanRp;
import com.example.untiled1.response.TutorialRp;
import com.example.untiled1.service.PhongBanService;
import com.example.untiled1.service.TaiKhoanService;
import com.example.untiled1.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class TaiKhoanController {


    @Autowired
    private PhongBanService phongBanService;

    @Autowired
    private TaiKhoanService taiKhoanService;

    @GetMapping("/search")
    public ResponseEntity<List<TaiKhoanRp>> search() throws SQLException {
        return ResponseEntity.ok(taiKhoanService.searchAll());
    }
    @GetMapping("/dmdc-phong-ban/search")
    public ResponseEntity<List<PhongBanRp>> searchTaiKhoan() throws SQLException {
        return ResponseEntity.ok(phongBanService.searchAll());
    }
    @PostMapping(value ="/tao-moi",consumes = { "application/json;charset=UTF-8" })
    public ResponseEntity<TaiKhoanRp> createUser( @RequestBody TaiKhoanRp taiKhoanRp) throws SQLException {
        return ResponseEntity.ok(taiKhoanService.createUser(taiKhoanRp));
    }

    @GetMapping("/users/{id}/edit")
    public ResponseEntity<TaiKhoanRp> searchById( @PathVariable Long id) throws SQLException {
        return ResponseEntity.ok(taiKhoanService.searchById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<TaiKhoanRp> updateUser( @PathVariable Long id, @RequestBody TaiKhoanRp taiKhoanRp) throws SQLException {
        return ResponseEntity.ok(taiKhoanService.updateById(id,taiKhoanRp));
    }

    @DeleteMapping("/users/{id}/delete")
    public ResponseEntity<TaiKhoanRp> deleteUserById( @PathVariable Long id) throws SQLException {
        return ResponseEntity.ok(taiKhoanService.deleteById(id));
    }

}
