package ec.r2develop.registroclientes.interfaces.controllers;

import ec.r2develop.registroclientes.application.BeneficioServices;
import ec.r2develop.registroclientes.application.ClienteService;
import ec.r2develop.registroclientes.domain.Cliente;
import ec.r2develop.registroclientes.interfaces.adapters.BeneficioJson;
import ec.r2develop.registroclientes.interfaces.dtos.ClienteRequest;
import ec.r2develop.registroclientes.interfaces.dtos.ClienteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador que maneja las operaciones relacionadas con los clientes.
 * Proporciona endpoints para crear clientes y buscar todos los clientes registrados.
 * Utiliza instancias de ClienteService y BeneficioServices para interactuar con la lógica de la aplicación.
 * Esta clase está anotada como un controlador de Spring y proporciona documentación Swagger para sus endpoints.
 *
 * @see org.springframework.web.bind.annotation.RestController
 * @see io.swagger.v3.oas.annotations.Operation
 * @see io.swagger.v3.oas.annotations.media.Content
 * @see io.swagger.v3.oas.annotations.responses.ApiResponse
 * @see io.swagger.v3.oas.annotations.tags.Tag
 * @see jakarta.validation.Valid
 * @see lombok.extern.log4j.Log4j2
 * @see org.springframework.beans.factory.annotation.Autowired
 * @see org.springframework.http.HttpStatus
 * @see org.springframework.http.ResponseEntity
 * @see org.springframework.web.bind.annotation.RequestMapping
 * @see org.springframework.web.bind.annotation.PostMapping
 * @see org.springframework.web.bind.annotation.GetMapping
 * @see org.springframework.web.bind.annotation.RequestBody
 * @see org.springframework.web.bind.annotation.RestController
 * @see ec.r2develop.registroclientes.application.ClienteService
 * @see ec.r2develop.registroclientes.application.BeneficioServices
 * @see ec.r2develop.registroclientes.domain.Cliente
 * @see ec.r2develop.registroclientes.interfaces.adapters.BeneficioJson
 * @see ec.r2develop.registroclientes.interfaces.dtos.ClienteRequest
 * @see ec.r2develop.registroclientes.interfaces.dtos.ClienteResponse
 * author Rony Reyna
 * @since 17/08/2023
 * @version 1.0
 */
@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "The Client Company API")
@ApiResponse(responseCode = "200", description = "Default Response")
@ApiResponse(responseCode = "401", description = "Unauthorized",
        content = @Content(mediaType = "application/json"))
@ApiResponse(responseCode = "500", description = "Internal Error",
        content = @Content(mediaType = "application/json"))
@Log4j2
public class ClienteController {
    private final ClienteService clienteService;
    private final BeneficioServices beneficioServices;

    private List<BeneficioJson> beneficioSkJsons;
    private List<String> beneficioThXml;

    @PostConstruct
    private void init() {
        beneficioSkJsons = beneficioServices.cargarBeneficiosJson();
        beneficioThXml = beneficioServices.cargarBeneficiosXml();
    }

    @Autowired
    public ClienteController(ClienteService clienteService, BeneficioServices beneficioServices) {
        this.clienteService = clienteService;
        this.beneficioServices = beneficioServices;
    }

    @PostMapping("/crearCliente")
    @Operation(summary = "Crea cliente.")
    public ResponseEntity<String> crearCliente(@Valid @RequestBody ClienteRequest clienteRequest) {
        Cliente nuevoCliente;

        if (clienteRequest.getBeneficio().equals("TH")) {
            List<String> beneficiosUsados = clienteService.buscarBeneficios(clienteRequest.getBeneficio());
            List<String> beneficiosDisponibles = beneficioThXml.stream().filter(b -> !beneficiosUsados.contains(b)).toList();
            if(beneficiosDisponibles.isEmpty()){
                return new ResponseEntity<>("La lista th_formato ya no tiene beneficios disponibles",HttpStatus.NOT_ACCEPTABLE);
            }
            nuevoCliente = clienteService.crearCliente(
                    clienteRequest.getNombre(),
                    clienteRequest.getEmail(),
                    clienteRequest.getNumeroTelefono(),
                    clienteRequest.getBeneficio(), beneficiosDisponibles.get(0)
            );
        } else if (clienteRequest.getBeneficio().equals("SK")){
            List<String> beneficiosUsados = clienteService.buscarBeneficios(clienteRequest.getBeneficio());
            List<String> beneficiosDisponibles = beneficioSkJsons.stream().map(BeneficioJson::getBeneficio).filter(b -> !beneficiosUsados.contains(b)).toList();
            if(beneficiosDisponibles.isEmpty()){
                return new ResponseEntity<>("La lista sk_formato ya no tiene beneficios disponibles",HttpStatus.NOT_ACCEPTABLE);
            }

            nuevoCliente = clienteService.crearCliente(
                    clienteRequest.getNombre(),
                    clienteRequest.getEmail(),
                    clienteRequest.getNumeroTelefono(),
                    clienteRequest.getBeneficio(), beneficiosDisponibles.get(0)
            );
        }else{
            return new ResponseEntity<>("EL grupo no existe",HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok("Cliente creado con ID: " + nuevoCliente.getId());
    }

    @GetMapping("/buscarTodos")
    @Operation(summary = "Buscar todos los clientes")
    public ResponseEntity<List<ClienteResponse>> buscarTodos() {
        List<ClienteResponse> beneficios = clienteService.buscarTodos();
        return ResponseEntity.ok(beneficios);
    }
}
