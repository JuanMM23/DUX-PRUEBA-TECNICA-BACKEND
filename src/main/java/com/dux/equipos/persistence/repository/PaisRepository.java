package com.dux.equipos.persistence.repository;

import com.dux.equipos.persistence.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaisRepository extends JpaRepository<Pais, Long> {
    Optional<Pais> findByNombreIgnoreCase(String nombre);
}
