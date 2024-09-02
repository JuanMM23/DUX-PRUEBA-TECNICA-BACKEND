package com.dux.equipos.service.impl;

import com.dux.equipos.controller.dto.CrearEquipoRequest;
import com.dux.equipos.controller.dto.EquipoResponse;
import com.dux.equipos.persistence.entity.Equipo;
import com.dux.equipos.persistence.repository.EquipoRepository;
import com.dux.equipos.service.IEquipoService;
import com.dux.equipos.service.ILigaService;
import com.dux.equipos.service.IPaisService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EquipoService implements IEquipoService {

    private ILigaService ligaService;
    private IPaisService paisService;
    private EquipoRepository equipoRepository;

    private final String MENSAJE_SOLICITUD_INVALIDA = "La solicitud es invalida";
    private final String MENSAJE_EQUIPO_NO_ENCONTRADO = "Equipo no encontrado";


    @Override
    public List<EquipoResponse> obtenerTodos() {
        List<Equipo> equipos = equipoRepository.findAll();
        return mapearEquiposAEquiposResponse(equipos);
    }

    @Override
    public EquipoResponse obtenerPorId(Long id) {
        Equipo equipo = obtenerEquipo(id);
        return obtenerEquipoResponse(equipo);
    }

    @Override
    public List<EquipoResponse> obtenerPorNombre(String nombre) {
        List<Equipo> equipos = equipoRepository.findByNombreContainingIgnoreCase(nombre);
        return mapearEquiposAEquiposResponse(equipos);
    }

    @Override
    public void eliminar(Long id) {
        Equipo equipo = obtenerEquipo(id);
        equipoRepository.deleteById(equipo.getId());
    }

    @Override
    public EquipoResponse guardar(CrearEquipoRequest equipoRequest) {
        validarNombreEquipo(equipoRequest.nombre());
        validarExistenciaEquipoConNombre(equipoRequest.nombre());
        Equipo equipo = mapearCrearEquipoRequestAEquipo(equipoRequest);
        Equipo equipoPersistido = equipoRepository.save(equipo);
        return obtenerEquipoResponse(equipoPersistido);
    }

    @Override
    public EquipoResponse actualizar(Long id, CrearEquipoRequest equipoRequest) {
        Equipo equipo = obtenerEquipo(id);
        validarNombreEquipo(equipoRequest.nombre());
        if (!(equipo.getNombre().equals(equipoRequest.nombre()))) {
            validarExistenciaEquipoConNombre(equipoRequest.nombre());
        }

        Equipo equipoParaActualizar = mapearCrearEquipoRequestAEquipo(equipoRequest);

        actualizarEquipo(equipo, equipoParaActualizar);

        return obtenerEquipoResponse(equipoRepository.save(equipo));

    }

    private static void actualizarEquipo(Equipo equipo, Equipo equipoParaActualizar) {
        equipo.setNombre(equipoParaActualizar.getNombre());
        equipo.setLiga(equipoParaActualizar.getLiga());
        equipo.setPais(equipoParaActualizar.getPais());
    }

    private Equipo obtenerEquipo(Long id) {
        return equipoRepository.findById(id).orElseThrow(() -> new NoSuchElementException(MENSAJE_EQUIPO_NO_ENCONTRADO));
    }

    private void validarNombreEquipo(String nombre) {
        Assert.isTrue(!(nombre == null || nombre.isBlank()), MENSAJE_SOLICITUD_INVALIDA);
    }

    private void validarExistenciaEquipoConNombre(String nombre) {
        Assert.isTrue(!(equipoRepository.existsByNombreIgnoreCase(nombre)), MENSAJE_SOLICITUD_INVALIDA);

    }

    private Equipo mapearCrearEquipoRequestAEquipo(CrearEquipoRequest equipoRequest) {
        return Equipo.builder()
                .nombre(equipoRequest.nombre())
                .pais(paisService.obtenerPorNombre(equipoRequest.pais()))
                .liga(ligaService.obtenerPorNombre(equipoRequest.liga()))
                .build();
    }

    private List<EquipoResponse> mapearEquiposAEquiposResponse(List<Equipo> equipos) {
        return equipos.stream().map(this::obtenerEquipoResponse).collect(Collectors.toList());
    }

    private EquipoResponse obtenerEquipoResponse(Equipo equipo) {
        return new EquipoResponse(equipo.getId(), equipo.getNombre(), equipo.getLiga().getNombre(), equipo.getPais().getNombre());
    }
}
