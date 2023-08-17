package ec.r2develop.registroclientes.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase representa a un cliente en el sistema de registro de clientes.
 * Los clientes tienen atributos como nombre, correo electrónico, teléfono,
 * tienda, grupo y beneficio. Cada cliente está identificado por un ID único.
 * La clase está anotada como una entidad JPA para su persistencia en la base de datos.
 * Se utiliza el framework Lombok para generar automáticamente métodos como getters,
 * setters, constructores y más.
 *
 * @author
 * @version 1.0
 * @since 17/08/2023
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    /**
     * Identificador único del cliente en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nombre del cliente.
     */
    private String nombre;
    /**
     * Correo electrónico del cliente.
     */
    private String email;
    /**
     * Número de teléfono del cliente.
     */
    private String telefono;
    /**
     * Tienda a la que pertenece el cliente.
     */
    private String tienda;
    /**
     * Grupo al que pertenece el cliente.
     */
    private String grupo;
    /**
     * Beneficio asociado al cliente.
     */
    private String beneficio;
}
