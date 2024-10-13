package org.sec.jwtsecurityproject.adminPanel.controller;

import lombok.RequiredArgsConstructor;
import org.sec.jwtsecurityproject.adminPanel.model.AdminPanel;
import org.sec.jwtsecurityproject.adminPanel.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adminPanel")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/getAllPosition")
    public ResponseEntity<List<AdminPanel>> getAllPosition() {
        return ResponseEntity.ok(adminService.findAll());
    }

    @GetMapping("/getPositionById{id}")
    public ResponseEntity<AdminPanel> getPositionById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.findById(id));
    }

    @PostMapping("/newAddPosition")
    public ResponseEntity<AdminPanel> addPosition(@RequestBody AdminPanel adminPanel) {
        return ResponseEntity.ok(adminService.addNewPosition(adminPanel));
    }

    @DeleteMapping("/deletePosition/{id}")
    public ResponseEntity<String> deletePosition(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.deleteById(id));
    }

    @PutMapping("/updatePosition/{id}")
    public ResponseEntity<AdminPanel> updatePosition(@PathVariable("id") Long id,
                                                     @RequestBody AdminPanel adminPanel) {
        return ResponseEntity.ok(adminService.updateById(
                    id,
                    adminPanel.getName(),
                    adminPanel.getPrice(),
                    adminPanel.getDataAdded(),
                    adminPanel.getDescription()
                ));
    }
}
