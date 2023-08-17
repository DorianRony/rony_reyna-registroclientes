package ec.r2develop.registroclientes.interfaces.adapters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class BeneficioJsonLoader {
    private final ObjectMapper objectMapper;

    @Autowired
    public BeneficioJsonLoader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private List<BeneficioJson> getInputStreamFromUrl() {
        try {
            InputStream inputStream = BeneficioJsonLoader.class.getClassLoader().getResourceAsStream("archivos/sk_formato.json");

            JsonNode rootNode = objectMapper.readTree(inputStream);
            return objectMapper.convertValue(rootNode.get("sk_formato"),
                    new TypeReference<List<BeneficioJson>>() {
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BeneficioJson> cargarDesdeJson() throws IOException {
        return getInputStreamFromUrl();
    }
}
