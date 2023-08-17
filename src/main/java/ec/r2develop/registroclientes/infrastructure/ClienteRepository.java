package ec.r2develop.registroclientes.infrastructure;

import ec.r2develop.registroclientes.domain.Cliente;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Esta interfaz define métodos de repositorio para la entidad Cliente,
 * lo que permite interactuar con la capa de datos y realizar operaciones
 * de persistencia y recuperación en la base de datos.
 * Utiliza Spring Data JPA para la implementación de métodos de acceso a datos.
 *
 * Adicionalmente, proporciona una consulta personalizada para buscar beneficios
 * asociados a un grupo específico de clientes.
 *
 * Esta interfaz forma parte de la capa de infraestructura del sistema.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see org.springframework.data.jpa.repository.JpaSpecificationExecutor
 * @see org.springframework.data.repository.CrudRepository
 * @author Rony Reyna
 * @version 1.0
 * @since 17/08/2023
 */
@Repository
public interface ClienteRepository  extends CrudRepository<Cliente, Integer>, JpaSpecificationExecutor<Cliente> {

    /**
     * Realiza una consulta personalizada para obtener una lista de beneficios
     * asociados a un grupo específico de clientes.
     *
     * @param grupo El grupo para el cual se buscan los beneficios.
     * @return Una lista de cadenas que representan los beneficios asociados al grupo.
     */
    @Query("select distinct c.beneficio from Cliente c where c.grupo = ?1")
    List<String> findBeneficios(String grupo);
}
