package com.kuehne_nagel.app.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public class XMLIO {
    public static Element loadXML(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        document.getDocumentElement().normalize();
        return document.getDocumentElement();
    }

    public static void saveXML(Element element, File file) throws TransformerException {
        transform(element, new StreamResult(file));
    }

    public static void printXML(Element element) throws TransformerException {
        transform(element, new StreamResult(System.out));
    }

    public static String getXMLString(Element element) throws TransformerException {
        StringWriter buffer = new StringWriter();
        transform(element, new StreamResult(buffer));
        return buffer.toString();
    }

    private static void transform(Element element, StreamResult streamResult) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        DOMSource source = new DOMSource(element);
        transformer.transform(source, streamResult);
    }
}
