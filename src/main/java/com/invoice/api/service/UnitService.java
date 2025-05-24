package com.invoice.api.service;

import com.invoice.api.model.Unit;
import com.invoice.api.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitService {

    private final UnitRepository unitRepository;

    @Autowired
    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    public Optional<Unit> getUnitById(Long id) {
        return unitRepository.findById(id);
    }

    public Unit createUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    public Unit updateUnit(Long id, Unit updatedUnit) {
        Optional<Unit> existingUnit = unitRepository.findById(id);
        if (existingUnit.isPresent()) {
            updatedUnit.setId(id);
            return unitRepository.save(updatedUnit);
        }
        return null;
    }

    public void deleteUnit(Long id) {
        unitRepository.deleteById(id);
    }
}