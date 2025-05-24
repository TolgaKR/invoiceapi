package com.invoice.api.controller;

import com.invoice.api.dto.SiparisDto;
import com.invoice.api.model.Siparis;
import com.invoice.api.service.SiparisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/siparisler")
@CrossOrigin(origins = "http://localhost:5173")
public class SiparisController {

    @Autowired
    private SiparisService siparisService;

    @PostMapping
    public ResponseEntity<?> createSiparis(@RequestBody SiparisDto dto) {
        try {
            Siparis saved = siparisService.createSiparisFromDto(dto);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSiparisById(@PathVariable Long id) {
        Optional<Siparis> siparis = siparisService.getSiparisById(id);
        if (siparis.isPresent()) {
            return ResponseEntity.ok(siparis.get());
        }
        return null;
    }

    @GetMapping
    public List<Siparis> getAllSiparisler() {
        return siparisService.getAllSiparis();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSiparis(@PathVariable Long id) {
        siparisService.deleteSiparis(id);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Siparis> updateSiparis(@PathVariable Long id, @RequestBody SiparisDto dto) {
        Siparis updated = siparisService.updateSiparisFromDto(id, dto);
        return ResponseEntity.ok(updated);
    }




}