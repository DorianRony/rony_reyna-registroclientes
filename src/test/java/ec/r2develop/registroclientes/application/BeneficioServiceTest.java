package ec.r2develop.registroclientes.application;

import ec.r2develop.registroclientes.interfaces.adapters.BeneficioJson;
import ec.r2develop.registroclientes.interfaces.adapters.BeneficioJsonLoader;
import ec.r2develop.registroclientes.interfaces.adapters.BeneficioXmlLoader;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class BeneficioServiceTest {

    @Mock
    private BeneficioJsonLoader beneficioJsonLoader;

    @Mock
    private BeneficioXmlLoader beneficioXmlLoader;

    @InjectMocks
    private BeneficioServicesImpl beneficioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cargarBeneficiosDesdeJson() throws IOException {
        List<BeneficioJson> beneficioJsons = beneficioService.cargarBeneficiosJson();
        assertNotNull(beneficioJsons);
        assertNotEquals(0,beneficioJsons.size());
    }

    @Test
    void cargarBeneficiosDesdeXml() throws JAXBException {
        List<String> beneficioXml = beneficioService.cargarBeneficiosXml();
        assertNotNull(beneficioXml);
        assertNotEquals(0,beneficioXml.size());
    }
}