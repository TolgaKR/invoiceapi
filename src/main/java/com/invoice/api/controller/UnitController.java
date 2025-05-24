package com.invoice.api.controller;

import com.invoice.api.model.Product;
import com.invoice.api.model.Unit;
import com.invoice.api.service.ProductService;
import com.invoice.api.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/units")
@CrossOrigin(origins = "http://localhost:5173")
public class UnitController {

    private final UnitService unitService;


    @Autowired
    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }


    @GetMapping
    public ResponseEntity<List<Unit>>  getAll()
    {
        List<Unit> units = unitService.getAllUnits();
        return new ResponseEntity<>(units, HttpStatus.OK);
    }

}
