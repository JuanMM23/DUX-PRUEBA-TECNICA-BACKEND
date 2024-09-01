package com.dux.equipos.service.impl;

import com.dux.equipos.controller.dto.EquipoResponse;
import com.dux.equipos.persistence.entity.Equipo;
import com.dux.equipos.persistence.repository.EquipoRepository;
import com.dux.equipos.service.IEquipoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EquipoService implements IEquipoService {

    private EquipoRepository equipoRepository;

    @Override
    public List<EquipoResponse> obtenerTodos() {
        List<Equipo> equipos = equipoRepository.findAll();
        return mapearEquiposAEquiposResponse(equipos);
    }

    @Override
    public EquipoResponse obtenerPorId(Long id) {
        Equipo equipo = equipoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Equipo no encontrado"));
        return obtenerEquipoResponse(equipo);
    }

    @Override
    public List<EquipoResponse> obtenerPorNombre(String nombre) {
        List<Equipo> equipos = equipoRepository.findByNombreContainingIgnoreCase(nombre);
        return mapearEquiposAEquiposResponse(equipos);
    }

    @Override
    public void eliminar(Long id) {
        Equipo equipo = equipoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Equipo no encontrado"));
        equipoRepository.deleteById(equipo.getId());
    }

    private List<EquipoResponse> mapearEquiposAEquiposResponse(List<Equipo> equipos) {
        return equipos.stream().map(this::obtenerEquipoResponse).collect(Collectors.toList());
    }

    private EquipoResponse obtenerEquipoResponse(Equipo equipo) {
        return new EquipoResponse(equipo.getId(), equipo.getNombre(), equipo.getLiga().getNombre(), equipo.getPais().getNombre());
    }
}
