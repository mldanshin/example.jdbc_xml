package com.example.testing.converters.xml;

import com.example.testing.models.TestDto;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.stereotype.Service;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

@Service
public class XmlConverter {
    public String convertToXml(List<TestDto> dto) {
        try (StringWriter writer = new StringWriter()) {
            ListTestModelConverter object = new ListTestModelConverter();
            object.addAll(dto);

            JAXBContext context = JAXBContext.newInstance(ListTestModelConverter.class, TestModelConverter.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(object, writer);
            return writer.toString();
        } catch (IOException | JAXBException e) {
            return "";
        }
    }

    public String convertToXml(List<TestDto> dto, ModifierXslt modifier) {
        try (StringWriter writer = new StringWriter()) {
            String xmlString = convertToXml(dto);

            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(modifier.getReader());
            Transformer transformer = factory.newTransformer(xslt);
            Source xml = new StreamSource(new StringReader(xmlString));
            transformer.transform(xml, new StreamResult(writer));
            return writer.toString();
        } catch (TransformerException | IOException e) {
            return "";
        }
    }
}
