package com.dux.equipos.controller;

import com.dux.equipos.controller.dto.CrearEquipoRequest;
import com.dux.equipos.controller.dto.EquipoResponse;
import com.dux.equipos.service.IEquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/equipos")
@AllArgsConstructor
public class EquipoRestController {

    private final IEquipoService equipoService;

    @Operation(summary = "Devuelve la lista de todos los equipos de fútbol registrados.")
    @ApiResponse(responseCode = "200", description = "Ok",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = EquipoResponse.class)) })
    @GetMapping
    public ResponseEntity<List<EquipoResponse>> obtenerTodos() {
        return new ResponseEntity<>(equipoService.obtenerTodos(), HttpStatus.OK);
    }

    @Operation(summary = "Devuelve la información del equipo correspondiente al ID proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EquipoResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado") })
    @GetMapping("/{id}")
    public ResponseEntity<EquipoResponse> obtenerPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(equipoService.obtenerPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Devuelve la lista de equipos cuyos nombres contienen el valor proporcionado en el parámetro de búsqueda.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EquipoResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Book not found") })
    @GetMapping("/buscar")
    public ResponseEntity<List<EquipoResponse>> obtenerTodosPorNombre(@RequestParam String nombre) {
        return new ResponseEntity<>(equipoService.obtenerPorNombre(nombre), HttpStatus.OK);
    }

    @Operation(summary = "Crea un equipo nuevo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EquipoResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "La solicitud es inválida")
    })
    @PostMapping
    public ResponseEntity<EquipoResponse> guardar(@RequestBody CrearEquipoRequest equipoRequest) {
        return new ResponseEntity<>(equipoService.guardar(equipoRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualiza nuevo equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EquipoResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "La solicitud es inválida"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EquipoResponse> actualizar(@PathVariable("id") Long id, @RequestBody CrearEquipoRequest equipoRequest) {
        return new ResponseEntity<>(equipoService.actualizar(id, equipoRequest), HttpStatus.OK);
    }

    @Operation(summary = "Elimina equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable("id") Long id) {
        equipoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
