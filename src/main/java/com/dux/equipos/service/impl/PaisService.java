package com.dux.equipos.service.impl;

import com.dux.equipos.persistence.entity.Pais;
import com.dux.equipos.persistence.repository.PaisRepository;
import com.dux.equipos.service.IPaisService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@AllArgsConstructor
public class PaisService implements IPaisService {

    private final PaisRepository paisRepository;

    private final String MENSAJE_SOLICITUD_INVALIDA = "La solicitud es inválida";

    @Override
    public Pais obtenerPorNombre(String nombre) {
        Assert.isTrue(!(nombre == null || nombre.isBlank()), MENSAJE_SOLICITUD_INVALIDA);
        Pais pais = paisRepository.findByNombreIgnoreCase(nombre).orElse(null);
        if (pais == null) pais = guardar(Pais.builder().nombre(nombre).build());
        return pais;
    }

    @Override
    public Pais guardar(Pais pais) {
        return paisRepository.save(pais);
    }
}
