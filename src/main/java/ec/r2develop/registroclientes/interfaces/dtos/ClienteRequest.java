package ec.r2develop.registroclientes.interfaces.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Clase que representa la solicitud para crear un nuevo cliente en el sistema.
 * Contiene información necesaria para crear un cliente, como nombre, correo electrónico,
 * número de teléfono y beneficio.
 * Utiliza anotaciones de validación de Bean Validation para garantizar que los campos
 * requeridos no estén vacíos y no sean nulos.
 *
 * @see jakarta.validation.constraints.NotEmpty
 * @see jakarta.validation.constraints.NotNull
 * @see lombok.Data
 * author Rony Reyna
 * @since 17/08/2023
 * @version 1.0
 */
@Data
public class ClienteRequest {
    @NotNull
    @NotEmpty
    private String nombre;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String numeroTelefono;
    @NotNull
    @NotEmpty
    private String beneficio;
}
