package com.dux.equipos.service;

import com.dux.equipos.persistence.entity.Liga;

public interface ILigaService {
    Liga obtenerPorNombre(String nombre);
    Liga guardar(Liga liga);
}
