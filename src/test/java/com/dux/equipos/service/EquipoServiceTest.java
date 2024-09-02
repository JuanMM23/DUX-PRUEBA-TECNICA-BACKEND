package com.dux.equipos.service;

import com.dux.equipos.controller.dto.CrearEquipoRequest;
import com.dux.equipos.controller.dto.EquipoResponse;
import com.dux.equipos.persistence.entity.Equipo;
import com.dux.equipos.persistence.entity.Liga;
import com.dux.equipos.persistence.entity.Pais;
import com.dux.equipos.persistence.repository.EquipoRepository;
import com.dux.equipos.service.impl.EquipoService;
import com.dux.equipos.service.impl.LigaService;
import com.dux.equipos.service.impl.PaisService;
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

    @Mock
    private PaisService paisService;
    @Mock
    private LigaService ligaService;

    private final String MENSAJE_SOLICITUD_INVALIDA = "La solicitud es invalida";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private static Equipo equipo1;
    private static Equipo equipo2;
    private static Pais pais;
    private static Liga liga;

    @BeforeAll
    public static void setUpBefore() {
        liga = Liga.builder().id(1L).nombre("Liga Test").build();
        pais = Pais.builder().id(1L).nombre("Pais Test").build();
        equipo1 = Equipo.builder().id(1L).nombre("test1").liga(liga).pais(pais).build();
        equipo2 = Equipo.builder().id(2L).nombre("test2").liga(liga).pais(pais).build();
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

    @Test
    public void guardar_conEquipoValido_devuelveEquipoGuardado() {
        CrearEquipoRequest crearEquipoRequest = new CrearEquipoRequest("test1", "Liga Test", "Pais Test");

        Equipo equipo = Equipo.builder().id(1L).nombre(crearEquipoRequest.nombre()).liga(liga).pais(pais).build();

        when(equipoRepository.save(any())).thenReturn(equipo);
        when(paisService.obtenerPorNombre(crearEquipoRequest.pais())).thenReturn(pais);
        when(ligaService.obtenerPorNombre(crearEquipoRequest.liga())).thenReturn(liga);

        EquipoResponse equipoResponse = equipoService.guardar(crearEquipoRequest);

        assertThat(equipoResponse.id()).isEqualTo(equipo.getId());
        verify(equipoRepository, times(1)).save(any());
        verify(paisService, times(1)).obtenerPorNombre(crearEquipoRequest.pais());
        verify(ligaService, times(1)).obtenerPorNombre(crearEquipoRequest.liga());
    }

    @Test
    public void guardar_conNombreDeEquipoNull_lanzaExcepcion() {
        CrearEquipoRequest crearEquipoRequest = new CrearEquipoRequest(null, "Liga Test", "Pais Test");

        assertThatThrownBy(() -> equipoService.guardar(crearEquipoRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MENSAJE_SOLICITUD_INVALIDA);
    }

    @Test
    public void guardar_conNombreDeEquipoVacio_lanzaExcepcion() {
        CrearEquipoRequest crearEquipoRequest = new CrearEquipoRequest("", "Liga Test", "Pais Test");

        assertThatThrownBy(() -> equipoService.guardar(crearEquipoRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MENSAJE_SOLICITUD_INVALIDA);
    }

    @Test
    public void guardar_conNombreDeEquipoExistente_lanzaExcepcion() {
        CrearEquipoRequest crearEquipoRequest = new CrearEquipoRequest("test1", "Liga Test", "Pais Test");

        when(equipoRepository.existsByNombreIgnoreCase(crearEquipoRequest.nombre())).thenReturn(true);

        assertThatThrownBy(() -> equipoService.guardar(crearEquipoRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MENSAJE_SOLICITUD_INVALIDA);
    }

    @Test
    public void guardar_conEquipoValidoYNuevaLiga_devuelveEquipoGuardadoConNuevaLiga() {
        CrearEquipoRequest crearEquipoRequest = new CrearEquipoRequest("test1", "Liga Nueva", "Pais Test");

        Liga ligaNueva = Liga.builder().nombre(crearEquipoRequest.liga()).build();
        Equipo equipo = Equipo.builder().id(1L).nombre(crearEquipoRequest.nombre()).liga(ligaNueva).pais(pais).build();

        when(equipoRepository.save(any())).thenReturn(equipo);
        when(paisService.obtenerPorNombre(crearEquipoRequest.pais())).thenReturn(pais);
        when(ligaService.obtenerPorNombre(crearEquipoRequest.liga())).thenReturn(ligaNueva);

        EquipoResponse equipoResponse = equipoService.guardar(crearEquipoRequest);

        assertThat(equipoResponse.id()).isEqualTo(equipo.getId());
        verify(equipoRepository, times(1)).save(any());
        verify(paisService, times(1)).obtenerPorNombre(crearEquipoRequest.pais());
        verify(ligaService, times(1)).obtenerPorNombre(crearEquipoRequest.liga());
    }

    @Test
    public void guardar_conEquipoValidoYNuevoPais_devuelveEquipoGuardadoConNuevoPais() {
        CrearEquipoRequest crearEquipoRequest = new CrearEquipoRequest("test1", "Liga Test", "Pais Nuevo");

        Pais paisNuevo = Pais.builder().nombre(crearEquipoRequest.pais()).build();
        Equipo equipo = Equipo.builder().id(1L).nombre(crearEquipoRequest.nombre()).liga(liga).pais(paisNuevo).build();

        when(equipoRepository.save(any())).thenReturn(equipo);
        when(paisService.obtenerPorNombre(crearEquipoRequest.pais())).thenReturn(paisNuevo);
        when(ligaService.obtenerPorNombre(crearEquipoRequest.liga())).thenReturn(liga);

        EquipoResponse equipoResponse = equipoService.guardar(crearEquipoRequest);

        assertThat(equipoResponse.id()).isEqualTo(equipo.getId());
        verify(equipoRepository, times(1)).save(any());
        verify(paisService, times(1)).obtenerPorNombre(crearEquipoRequest.pais());
        verify(ligaService, times(1)).obtenerPorNombre(crearEquipoRequest.liga());
    }

    @Test
    public void actualizar_conEquipoNuevoValido_devuelveEquipoActualizado() {
        CrearEquipoRequest crearEquipoRequest = new CrearEquipoRequest("testActualiza", "Liga Test", "Pais Nuevo");
        Long idEquipoAActualizar = 1L;
        Equipo equipoActualizado = Equipo.builder().id(idEquipoAActualizar).nombre(crearEquipoRequest.nombre()).liga(liga).pais(pais).build();

        when(equipoRepository.findById(idEquipoAActualizar)).thenReturn(Optional.ofNullable(equipo1));
        when(equipoRepository.existsByNombreIgnoreCase(crearEquipoRequest.nombre())).thenReturn(false);
        when(paisService.obtenerPorNombre(crearEquipoRequest.pais())).thenReturn(pais);
        when(ligaService.obtenerPorNombre(crearEquipoRequest.liga())).thenReturn(liga);
        when(equipoRepository.save(equipo1)).thenReturn(equipoActualizado);

        EquipoResponse equipoActualizadoResponse = equipoService.actualizar(idEquipoAActualizar, crearEquipoRequest);

        assertThat(equipoActualizadoResponse.nombre()).isEqualTo(crearEquipoRequest.nombre());
        verify(equipoRepository, times(1)).save(any());
        verify(paisService, times(1)).obtenerPorNombre(crearEquipoRequest.pais());
        verify(ligaService, times(1)).obtenerPorNombre(crearEquipoRequest.liga());
    }

    @Test
    public void actualizar_conNombreDeEquipoNull_lanzaExcepcion() {
        Long idEquipoAActualizar = 1L;
        CrearEquipoRequest crearEquipoRequest = new CrearEquipoRequest(null, "Liga Test", "Pais Test");
        when(equipoRepository.findById(idEquipoAActualizar)).thenReturn(Optional.ofNullable(equipo1));
        assertThatThrownBy(() -> equipoService.actualizar(idEquipoAActualizar, crearEquipoRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MENSAJE_SOLICITUD_INVALIDA);
    }

    @Test
    public void actualizar_conNombreDeEquipoVacio_lanzaExcepcion() {
        Long idEquipoAActualizar = 1L;
        CrearEquipoRequest crearEquipoRequest = new CrearEquipoRequest("", "Liga Test", "Pais Test");
        when(equipoRepository.findById(idEquipoAActualizar)).thenReturn(Optional.ofNullable(equipo1));
        assertThatThrownBy(() -> equipoService.actualizar(idEquipoAActualizar, crearEquipoRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MENSAJE_SOLICITUD_INVALIDA);
    }

    @Test
    public void actualizar_conNombreDeEquipoExistentePeroDistintoAlNombreEquipoAActualizar_lanzaExcepcion() {
        Long idEquipoAActualizar = 1L;
        CrearEquipoRequest crearEquipoRequest = new CrearEquipoRequest("test2", "Liga Test", "Pais Test");
        when(equipoRepository.findById(idEquipoAActualizar)).thenReturn(Optional.ofNullable(equipo1));
        when(equipoRepository.existsByNombreIgnoreCase(crearEquipoRequest.nombre())).thenReturn(true);

        assertThatThrownBy(() -> equipoService.actualizar(idEquipoAActualizar, crearEquipoRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MENSAJE_SOLICITUD_INVALIDA);
    }

    @Test
    public void actualizar_conNombreEquipoIgualAlNombreEquipoAActualizar_devuelveEquipoActualizado() {
        CrearEquipoRequest crearEquipoRequest = new CrearEquipoRequest("test1", "Liga Test2", "Pais Nuevo");
        Long idEquipoAActualizar = 1L;
        Equipo equipoActualizado = Equipo.builder().id(idEquipoAActualizar).nombre(crearEquipoRequest.nombre()).liga(liga).pais(pais).build();

        when(equipoRepository.findById(idEquipoAActualizar)).thenReturn(Optional.ofNullable(equipo1));
        when(equipoRepository.existsByNombreIgnoreCase(crearEquipoRequest.nombre())).thenReturn(false);
        when(paisService.obtenerPorNombre(crearEquipoRequest.pais())).thenReturn(pais);
        when(ligaService.obtenerPorNombre(crearEquipoRequest.liga())).thenReturn(liga);
        when(equipoRepository.save(equipo1)).thenReturn(equipoActualizado);

        EquipoResponse equipoActualizadoResponse = equipoService.actualizar(idEquipoAActualizar, crearEquipoRequest);

        assertThat(equipoActualizadoResponse.nombre()).isEqualTo(crearEquipoRequest.nombre());
        verify(equipoRepository, times(1)).save(any());
        verify(paisService, times(1)).obtenerPorNombre(crearEquipoRequest.pais());
        verify(ligaService, times(1)).obtenerPorNombre(crearEquipoRequest.liga());
    }

    @Test
    public void actualizar_conEquipoValidoYNuevaLiga_devuelveEquipoGuardadoConNuevaLiga() {

        Long idEquipoAActualizar = 1L;
        CrearEquipoRequest crearEquipoRequest = new CrearEquipoRequest("test1", "Liga Nueva", "Pais Test");

        Liga ligaNueva = Liga.builder().nombre(crearEquipoRequest.liga()).build();
        Equipo equipo = Equipo.builder().id(1L).nombre(crearEquipoRequest.nombre()).liga(ligaNueva).pais(pais).build();

        when(equipoRepository.findById(idEquipoAActualizar)).thenReturn(Optional.ofNullable(equipo1));
        when(equipoRepository.save(any())).thenReturn(equipo);
        when(paisService.obtenerPorNombre(crearEquipoRequest.pais())).thenReturn(pais);
        when(ligaService.obtenerPorNombre(crearEquipoRequest.liga())).thenReturn(ligaNueva);

        EquipoResponse equipoResponse = equipoService.actualizar(idEquipoAActualizar, crearEquipoRequest);

        assertThat(equipoResponse.id()).isEqualTo(equipo.getId());
        verify(equipoRepository, times(1)).save(any());
        verify(paisService, times(1)).obtenerPorNombre(crearEquipoRequest.pais());
        verify(ligaService, times(1)).obtenerPorNombre(crearEquipoRequest.liga());
    }

    @Test
    public void actualizar_conEquipoValidoYNuevoPais_devuelveEquipoGuardadoConNuevoPais() {
        Long idEquipoAActualizar = 1L;
        CrearEquipoRequest crearEquipoRequest = new CrearEquipoRequest("test1", "Liga Test", "Pais Nuevo");

        Pais paisNuevo = Pais.builder().nombre(crearEquipoRequest.pais()).build();
        Equipo equipo = Equipo.builder().id(1L).nombre(crearEquipoRequest.nombre()).liga(liga).pais(paisNuevo).build();

        when(equipoRepository.findById(idEquipoAActualizar)).thenReturn(Optional.ofNullable(equipo1));
        when(equipoRepository.save(any())).thenReturn(equipo);
        when(paisService.obtenerPorNombre(crearEquipoRequest.pais())).thenReturn(paisNuevo);
        when(ligaService.obtenerPorNombre(crearEquipoRequest.liga())).thenReturn(liga);

        EquipoResponse equipoResponse = equipoService.actualizar(idEquipoAActualizar, crearEquipoRequest);

        assertThat(equipoResponse.id()).isEqualTo(equipo.getId());
        verify(equipoRepository, times(1)).save(any());
        verify(paisService, times(1)).obtenerPorNombre(crearEquipoRequest.pais());
        verify(ligaService, times(1)).obtenerPorNombre(crearEquipoRequest.liga());
    }
}
