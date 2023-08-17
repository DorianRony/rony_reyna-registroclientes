package ec.r2develop.registroclientes.application;

import ec.r2develop.registroclientes.interfaces.adapters.BeneficioJson;

import java.util.List;

/**
 * Esta interfaz define los métodos para cargar beneficios en diferentes formatos.
 * Los formatos admitidos son JSON y XML.
 * Los métodos proporcionados permiten cargar beneficios en los formatos
 * especificados y devuelven los datos en estructuras de lista.
 *
 * @author Rony Reyna
 * @version 1.0
 * @since 17/08/2023
 */
public interface BeneficioServices {
    /**
     * Carga y devuelve una lista de beneficios en formato JSON.
     *
     * @return Una lista de objetos BeneficioJson que representan beneficios cargados desde JSON.
     */
    List<BeneficioJson> cargarBeneficiosJson();

    /**
     * Carga y devuelve una lista de beneficios en formato XML.
     *
     * @return Una lista de cadenas que representan beneficios cargados desde XML.
     */
    List<String> cargarBeneficiosXml();
}
