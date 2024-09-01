package com.dux.equipos.controller;

import com.dux.equipos.controller.dto.EquipoResponse;
import com.dux.equipos.service.IEquipoService;
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

    @GetMapping
    public ResponseEntity<List<EquipoResponse>> obtenerTodos() {
        return new ResponseEntity<>(equipoService.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipoResponse> obtenerPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(equipoService.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EquipoResponse>> obtenerTodosPorNombre(@RequestParam String nombre) {
        return new ResponseEntity<>(equipoService.obtenerPorNombre(nombre), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable("id") Long id) {
        equipoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
