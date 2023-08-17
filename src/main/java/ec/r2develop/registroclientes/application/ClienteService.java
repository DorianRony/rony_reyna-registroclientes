package ec.r2develop.registroclientes.application;

import ec.r2develop.registroclientes.domain.Cliente;
import ec.r2develop.registroclientes.interfaces.dtos.ClienteResponse;

import java.util.List;

/**
 * Esta interfaz define los métodos relacionados con la gestión de clientes y beneficios.
 * Proporciona operaciones para crear un cliente, buscar beneficios por grupo y buscar todos los clientes.
 * Los métodos especificados ofrecen funcionalidad para interactuar con los datos de los clientes y sus beneficios.
 *
 * @author Rony Reyna
 * @version 1.0
 * @since 17/08/2023
 */
public interface ClienteService {

    /**
     * Crea y registra un nuevo cliente con la información proporcionada.
     *
     * @param nombre El nombre del cliente.
     * @param correoElectronico El correo electrónico del cliente.
     * @param numeroTelefono El número de teléfono del cliente.
     * @param grupo El grupo al que pertenece el cliente.
     * @param beneficio El beneficio asociado al cliente.
     * @return Una instancia del objeto Cliente que representa al cliente creado.
     */
    Cliente crearCliente(String nombre, String correoElectronico, String numeroTelefono, String grupo,String beneficio);

    /**
     * Busca y devuelve una lista de beneficios asociados a un grupo específico.
     *
     * @param grupo El grupo para el cual se buscan los beneficios.
     * @return Una lista de cadenas que representan los beneficios asociados al grupo.
     */
    List<String> buscarBeneficios(String grupo);

    /**
     * Busca y devuelve una lista de respuestas de clientes que representan a todos los clientes registrados.
     *
     * @return Una lista de objetos ClienteResponse que contienen información sobre todos los clientes registrados.
     */
    List<ClienteResponse> buscarTodos();
}
