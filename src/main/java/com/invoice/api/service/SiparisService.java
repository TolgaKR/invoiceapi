package com.invoice.api.service;



import com.invoice.api.dto.SiparisDto;
import com.invoice.api.model.Product;
import com.invoice.api.model.Siparis;

import com.invoice.api.model.Unit;
import com.invoice.api.repository.ProductRepository;
import com.invoice.api.repository.SiparisRepository;

import com.invoice.api.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

import java.util.Optional;



@Service
public class SiparisService {

    private final SiparisRepository siparisRepository;
    private final ProductRepository productRepository;
    private final UnitRepository unitRepository;

    @Autowired
    public SiparisService(SiparisRepository siparisRepository,
                          ProductRepository productRepository,
                          UnitRepository unitRepository) {
        this.siparisRepository = siparisRepository;
        this.productRepository = productRepository;
        this.unitRepository = unitRepository;
    }

    public List<Siparis> getAllSiparis() {
        return siparisRepository.findAll();
    }

    public Optional<Siparis> getSiparisById(Long id) {
        return siparisRepository.findById(id);
    }

    public Siparis createSiparis(Siparis siparis) {
        return siparisRepository.save(siparis);
    }

    public void deleteSiparis(Long id) {
        siparisRepository.deleteById(id);
    }

    // ✅ DTO'dan Sipariş oluştur
    public Siparis createSiparisFromDto(SiparisDto dto) {
        Product product = productRepository.findById(dto.getProduct_id())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Unit unit = unitRepository.findById(dto.getUnit_id())
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        Siparis siparis = new Siparis();
        siparis.setQuantity(dto.getQuantity());
        siparis.setUnitprice(dto.getUnitprice());
        siparis.setTotalprice(dto.getTotalprice());
        siparis.setProduct(product);
        siparis.setUnit(unit);

        return siparisRepository.save(siparis);
    }


    public Siparis updateSiparisFromDto(Long id, SiparisDto dto) {
        Siparis existing = siparisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Siparis not found"));

        Product product = productRepository.findById(dto.getProduct_id())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Unit unit = unitRepository.findById(dto.getUnit_id())
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        existing.setQuantity(dto.getQuantity());
        existing.setUnitprice(dto.getUnitprice());
        existing.setTotalprice(dto.getTotalprice());
        existing.setProduct(product);


        return siparisRepository.save(existing);
    }
}
