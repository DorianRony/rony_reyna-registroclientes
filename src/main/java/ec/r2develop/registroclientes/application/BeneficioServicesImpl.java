package ec.r2develop.registroclientes.application;

import ec.r2develop.registroclientes.interfaces.adapters.BeneficioJson;
import ec.r2develop.registroclientes.interfaces.adapters.BeneficioJsonLoader;
import ec.r2develop.registroclientes.interfaces.adapters.BeneficioXmlLoader;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Implementación concreta de la interfaz BeneficioServices que proporciona
 * métodos para cargar beneficios en formatos JSON y XML.
 * Utiliza instancias de BeneficioJsonLoader y BeneficioXmlLoader para
 * cargar los beneficios en los formatos correspondientes.
 * Esta clase está anotada como un servicio de Spring.
 *
 * @author Rony Reyna
 * @version 1.0
 * @since 17/08/2023
 */
@Service
public class BeneficioServicesImpl implements BeneficioServices{
    private final BeneficioJsonLoader beneficioJsonLoader;
    private final BeneficioXmlLoader beneficioXmlLoader;

    /**
     * Constructor que inyecta instancias de BeneficioJsonLoader y BeneficioXmlLoader.
     *
     * @param beneficioJsonLoader Instancia de BeneficioJsonLoader utilizada para cargar beneficios en formato JSON.
     * @param beneficioXmlLoader Instancia de BeneficioXmlLoader utilizada para cargar beneficios en formato XML.
     */
    @Autowired
    public BeneficioServicesImpl(BeneficioJsonLoader beneficioJsonLoader, BeneficioXmlLoader beneficioXmlLoader) {
        this.beneficioJsonLoader = beneficioJsonLoader;
        this.beneficioXmlLoader = beneficioXmlLoader;
    }

    /**
     * Carga y devuelve una lista de beneficios en formato JSON.
     *
     * @return Una lista de objetos BeneficioJson que representan beneficios cargados desde JSON.
     * @throws RuntimeException Si ocurre un error durante la carga desde JSON.
     */
    @Override
    public List<BeneficioJson> cargarBeneficiosJson() {
        try {
            return beneficioJsonLoader.cargarDesdeJson();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Carga y devuelve una lista de beneficios en formato XML.
     *
     * @return Una lista de cadenas que representan beneficios cargados desde XML.
     * @throws RuntimeException Si ocurre un error durante la carga desde XML.
     */
    @Override
    public List<String> cargarBeneficiosXml() {
        try {
            return beneficioXmlLoader.cargarDesdeXml();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
