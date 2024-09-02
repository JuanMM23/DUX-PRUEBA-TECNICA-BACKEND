package com.dux.equipos.service.impl;

import com.dux.equipos.persistence.entity.Liga;
import com.dux.equipos.persistence.repository.LigaRepository;
import com.dux.equipos.service.ILigaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@AllArgsConstructor
public class LigaService implements ILigaService {

    private final LigaRepository ligaRepository;

    private final String MENSAJE_SOLICITUD_INVALIDA = "La solicitud es inválida";

    @Override
    public Liga obtenerPorNombre(String nombre) {
        Assert.isTrue(!(nombre == null || nombre.isBlank()), MENSAJE_SOLICITUD_INVALIDA);
        Liga liga = ligaRepository.findByNombreIgnoreCase(nombre).orElse(null);
        if (liga == null) liga = guardar(Liga.builder().nombre(nombre).build());
        return liga;
    }

    @Override
    public Liga guardar(Liga liga) {
        return ligaRepository.save(liga);
    }
}

