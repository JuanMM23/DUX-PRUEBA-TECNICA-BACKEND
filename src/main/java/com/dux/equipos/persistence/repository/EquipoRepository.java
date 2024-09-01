package com.dux.equipos.persistence.repository;

import com.dux.equipos.persistence.entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    List<Equipo> findByNombreContainingIgnoreCase(String nombre);
}
