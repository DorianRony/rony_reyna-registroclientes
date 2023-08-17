package ec.r2develop.registroclientes.application;

import ec.r2develop.registroclientes.domain.Cliente;
import ec.r2develop.registroclientes.infrastructure.ClienteRepository;
import ec.r2develop.registroclientes.interfaces.dtos.ClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación concreta de la interfaz ClienteService que proporciona
 * operaciones para crear, buscar beneficios y buscar todos los clientes.
 * Utiliza instancias de ClienteRepository para interactuar con la capa de datos.
 * Esta clase está anotada como un servicio de Spring.
 *
 * @author Rony Reyna
 * @version 1.0
 * @since 17/08/2023
 */
@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    /**
     * Constructor que inyecta una instancia de ClienteRepository.
     *
     * @param clienteRepository Instancia de ClienteRepository utilizada para interactuar con la capa de datos.
     */
    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente crearCliente(String nombre, String correoElectronico, String numeroTelefono, String grupo, String beneficio) {
        Cliente nuevoCliente = Cliente.builder()
                .nombre(nombre)
                .email(correoElectronico)
                .telefono(numeroTelefono)
                .grupo(grupo)
                .beneficio(beneficio)
                .build();
        return clienteRepository.save(nuevoCliente);
    }

    @Override
    public List<String> buscarBeneficios(String grupo) {
        return clienteRepository.findBeneficios(grupo);
    }

    @Override
    public List<ClienteResponse> buscarTodos() {
        List<Cliente> all = (List<Cliente>) clienteRepository.findAll();
        List<ClienteResponse> clienteResponses = new ArrayList<>();
        all.forEach(c -> {
            clienteResponses.add(build(c));
        });
        return clienteResponses;
    }

    /**
     * Construye y devuelve una instancia de ClienteResponse a partir de un objeto Cliente.
     *
     * @param cliente El objeto Cliente del cual se construirá la respuesta.
     * @return Una instancia de ClienteResponse con la información del cliente.
     */
    private ClienteResponse build(Cliente cliente){
        return ClienteResponse.builder()
                .nombre(cliente.getNombre())
                .email(cliente.getEmail())
                .telefono(cliente.getTelefono())
                .beneficio(cliente.getBeneficio())
                .grupo(cliente.getGrupo())
                .build();
    }
}
