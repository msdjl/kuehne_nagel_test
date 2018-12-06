package com.kuehne_nagel.test;

import com.kuehne_nagel.app.props.Props;
import com.kuehne_nagel.test.util.TestDynamicPropsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;

import static com.kuehne_nagel.app.xml.XMLIO.getXMLString;
import static com.kuehne_nagel.app.xml.XMLIO.loadXML;
import static com.kuehne_nagel.app.xml.XMLProcessor.processPlaceholders;
import static org.junit.jupiter.api.Assertions.assertEquals;

class XMLProcessorTest {
    Props props;
    ClassLoader classLoader;

    XMLProcessorTest() throws IOException {
        classLoader = getClass().getClassLoader();
        File propsFile = new File(classLoader.getResource("example.properties").getFile());
        props = new Props(propsFile, new TestDynamicPropsProvider());
    }

    @Test
    @DisplayName("process xml without placeholders")
    void withoutPlaceholders() throws Exception {
        String initial = "<a>b</a>";
        processAndCompare(initial, initial);
    }

    @Test
    @DisplayName("process xml with placeholders")
    void withPlaceholder() throws Exception {
        String initial = "<TRNNAM>${TRNNAM}</TRNNAM>";
        String expected = "<TRNNAM>UC_STOCK_LEVEL</TRNNAM>";
        processAndCompare(initial, expected);
    }

    @Test
    @DisplayName("process xml with nested placeholders")
    void withNestedPlaceholder() throws Exception {
        String initial = "<parent><TRNNAM>${TRNNAM}</TRNNAM></parent>";
        String expected = "<parent><TRNNAM>UC_STOCK_LEVEL</TRNNAM></parent>";
        processAndCompare(initial, expected);
    }

    @Test
    @DisplayName("process complex xml")
    void complexXML() throws Exception {
        File inputFile = new File(classLoader.getResource("input.xml").getFile());
        Element root = loadXML(inputFile);
        processPlaceholders(root, props);
        String actualResult = getXMLString(root);

        File outputFile = new File(classLoader.getResource("output.xml").getFile());
        String expectedResult = getXMLString(loadXML(outputFile));

        assertEquals(expectedResult, actualResult);
    }

    void processAndCompare(String initial, String expectedResult) throws Exception {
        Element root = loadXML(initial);
        processPlaceholders(root, props);
        String actualResult = getXMLString(root);
        assertEquals(expectedResult, actualResult);
    }
}
