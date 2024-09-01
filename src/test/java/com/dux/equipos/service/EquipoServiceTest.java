package com.dux.equipos.service;

import com.dux.equipos.controller.dto.EquipoResponse;
import com.dux.equipos.persistence.entity.Equipo;
import com.dux.equipos.persistence.entity.Liga;
import com.dux.equipos.persistence.entity.Pais;
import com.dux.equipos.persistence.repository.EquipoRepository;
import com.dux.equipos.service.impl.EquipoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class EquipoServiceTest {

    @InjectMocks
    private EquipoService equipoService;

    @Mock
    private EquipoRepository equipoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private static Equipo equipo1;
    private static Equipo equipo2;

    @BeforeAll
    public static void setUpBefore() {
        Liga liga = Liga.builder().id(1L).nombre("Liga Test").build();
        Pais pais = Pais.builder().id(1L).nombre("Pais Test").build();
        equipo1 = Equipo.builder().id(1L).nombre("test1").liga(liga).pais(pais).build();
        equipo2 = Equipo.builder().id(1L).nombre("test2").liga(liga).pais(pais).build();
    }

    @Test
    public void obtenerTodos_conListaDeEquipos_devuelveListaDeEquipos() {

        when(equipoRepository.findAll()).thenReturn(List.of(equipo1, equipo1));

        List<EquipoResponse> equipos = equipoService.obtenerTodos();

        verify(equipoRepository, times(1)).findAll();
        assertThat(equipos.size()).isEqualTo(2);
    }

    @Test
    public void buscarPorId_conEquipoExistente_devuelveEquipoExistente() {

        when(equipoRepository.findById(equipo1.getId())).thenReturn(Optional.ofNullable(equipo1));

        EquipoResponse equipo = equipoService.obtenerPorId(equipo1.getId());

        verify(equipoRepository, times(1)).findById(equipo1.getId());
        assertThat(equipo).isNotNull();
    }

    @Test
    public void buscarPorId_conEquipoInexistente_lanzaExcepcion() {

        when(equipoRepository.findById(equipo1.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> equipoService.obtenerPorId(equipo1.getId()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Equipo no encontrado");
    }

    @Test
    public void buscarPorNombre_conEquipoExistenteConNombreExactoBuscado_devuelveEquipoConNombreBuscado() {
        when(equipoRepository.findByNombreContainingIgnoreCase(equipo1.getNombre())).thenReturn(List.of(equipo1));

        List<EquipoResponse> equipos = equipoService.obtenerPorNombre(equipo1.getNombre());

        verify(equipoRepository, times(1)).findByNombreContainingIgnoreCase(equipo1.getNombre());
        assertThat(equipos.size()).isEqualTo(1);
    }

    @Test
    public void buscarPorNombre_conNombreQueCoincideBuscado_devuelveEquipoConNombreBuscado() {
        when(equipoRepository.findByNombreContainingIgnoreCase("test")).thenReturn(List.of(equipo1, equipo2));

        List<EquipoResponse> equipos = equipoService.obtenerPorNombre("test");

        verify(equipoRepository, times(1)).findByNombreContainingIgnoreCase("test");
        assertThat(equipos.size()).isEqualTo(2);
    }

    @Test
    public void buscarPorNombre_conNombreQueNoCoincide_devuelveListaVacia() {
        when(equipoRepository.findByNombreContainingIgnoreCase(equipo1.getNombre())).thenReturn(List.of());

        List<EquipoResponse> equipos = equipoService.obtenerPorNombre("1");

        verify(equipoRepository, times(1)).findByNombreContainingIgnoreCase("1");
        assertThat(equipos.size()).isEqualTo(0);
    }

    @Test
    public void eliminar_conIdDeEquipoExistente_eliminaEquipo() {
        when(equipoRepository.findById(equipo1.getId())).thenReturn(Optional.ofNullable(equipo1));

        equipoService.eliminar(equipo1.getId());

        verify(equipoRepository, times(1)).deleteById(equipo1.getId());
    }

    @Test
    public void eliminar_conIdDeEquipoInexistente_lanzaExcepcion() {
        when(equipoRepository.findById(-1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> equipoService.eliminar(-1L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Equipo no encontrado");
    }
}
