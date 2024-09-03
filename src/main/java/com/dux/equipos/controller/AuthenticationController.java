package com.dux.equipos.controller;

import com.dux.equipos.controller.dto.AuthLoginRequest;
import com.dux.equipos.controller.dto.AuthResponse;
import com.dux.equipos.service.impl.UserDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserDetailService userDetailService;

    @Operation(summary = "Actualiza nuevo equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas",
                    content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthLoginRequest userRequest) {
        return new ResponseEntity<>(this.userDetailService.login(userRequest), HttpStatus.OK);
    }

}
