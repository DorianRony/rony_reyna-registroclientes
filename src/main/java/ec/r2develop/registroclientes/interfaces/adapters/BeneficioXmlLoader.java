package ec.r2develop.registroclientes.interfaces.adapters;

import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class BeneficioXmlLoader {
    private List<String> getInputStreamFromUrl() {
        try {
            InputStream inputStream = BeneficioJsonLoader.class.getClassLoader().getResourceAsStream("archivos/th_formato.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

            Element rootElement = document.getDocumentElement();
            NodeList beneficiosList = rootElement.getElementsByTagName("beneficio");

            List<String> beneficios = new ArrayList<>();

            for (int i = 0; i < beneficiosList.getLength(); i++) {
                Node beneficioNode = beneficiosList.item(i);
                if (beneficioNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beneficioElement = (Element) beneficioNode;
                    beneficios.add(beneficioElement.getTextContent());
                }
            }
            return beneficios;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<String> cargarDesdeXml() throws JAXBException {
        return getInputStreamFromUrl();
    }
}
