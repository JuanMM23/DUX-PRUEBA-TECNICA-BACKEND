package com.dux.equipos.persistence.repository;

import com.dux.equipos.persistence.entity.Liga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LigaRepository extends JpaRepository<Liga, Long> {
    Optional<Liga> findByNombreIgnoreCase(String nombre);
}
