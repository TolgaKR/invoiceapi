package com.invoice.api.repository;

import com.invoice.api.model.Siparis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiparisRepository extends JpaRepository<Siparis, Long> {

}