package ec.r2develop.registroclientes.interfaces.dtos;

import lombok.Builder;
import lombok.Data;

/**
 * Clase que representa la respuesta de un cliente en el sistema.
 * Contiene información sobre el nombre, correo electrónico, número de teléfono,
 * grupo y beneficio asociados a un cliente.
 * Utiliza las anotaciones de Lombok para generar automáticamente los métodos
 * getters, setters, constructores y otros métodos útiles.
 *
 * @see lombok.Data
 * @see lombok.Builder
 * author Rony Reyna
 * @since 17/08/2023
 * @version 1.0
 */
@Data
@Builder
public class ClienteResponse {
    private String nombre;
    private String email;
    private String telefono;
    private String grupo;
    private String beneficio;
}
