package org.dorian.foroalura.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacion(@NotBlank String correo_electronico, @NotBlank String contrasena) {
}
