package org.dorian.foroalura.controller;

import jakarta.validation.Valid;
import org.dorian.foroalura.domain.usuario.DatosAutenticacion;
import org.dorian.foroalura.domain.usuario.Usuario;
import org.dorian.foroalura.infra.security.DatosTokenJWT;
import org.dorian.foroalura.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;


    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos) {
        var autenticacionToken = new UsernamePasswordAuthenticationToken(datos.correo_electronico(),datos.contrasena());
        var autenticacion = manager.authenticate(autenticacionToken);

        var tokenJWT = tokenService.generateToken((Usuario) autenticacion.getPrincipal());

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }

}
