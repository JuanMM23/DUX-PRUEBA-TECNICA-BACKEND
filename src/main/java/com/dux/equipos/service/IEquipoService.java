package com.dux.equipos.service;

import com.dux.equipos.controller.dto.EquipoResponse;

import java.util.List;

public interface IEquipoService {
    List<EquipoResponse> obtenerTodos();
    EquipoResponse obtenerPorId(Long id);
    List<EquipoResponse> obtenerPorNombre(String nombre);
    void eliminar(Long id);
}
