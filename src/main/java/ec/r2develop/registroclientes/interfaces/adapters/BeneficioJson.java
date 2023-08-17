package ec.r2develop.registroclientes.interfaces.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BeneficioJson implements Serializable {
    @JsonProperty("beneficio")
    private String beneficio;
}