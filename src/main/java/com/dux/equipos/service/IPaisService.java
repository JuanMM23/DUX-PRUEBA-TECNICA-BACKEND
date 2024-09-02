package com.dux.equipos.service;

import com.dux.equipos.persistence.entity.Pais;

public interface IPaisService {

    Pais obtenerPorNombre(String nombre);
    Pais guardar(Pais pais);
}
