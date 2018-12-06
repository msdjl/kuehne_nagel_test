package com.kuehne_nagel.test;

import com.kuehne_nagel.app.Application;
import com.kuehne_nagel.app.props.Props;
import com.kuehne_nagel.test.util.TestDynamicPropsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import static com.kuehne_nagel.app.xml.XMLIO.getXMLString;
import static com.kuehne_nagel.app.xml.XMLIO.loadXML;
import static com.kuehne_nagel.app.xml.XMLProcessor.processPlaceholders;
import static org.junit.jupiter.api.Assertions.assertEquals;

class E2ETest extends TestBase {
    @Test
    @DisplayName("e2e with real files")
    void e2e() throws Exception {
        File propsFile = getResourceFile("example.properties");
        File inputFile = getResourceFile("input_plain.xml");
        File exampleOutputFile = getResourceFile("output_plain.xml");

        File outputFile = new File(new Date().getTime() + ".xml");
        outputFile.deleteOnExit();

        String[] params = {
                "-p", getRelativePath(propsFile),
                "-i", getRelativePath(inputFile),
                "-o", getRelativePath(outputFile)
        };

        Application.main(params);

        String actualResult = getXMLString(loadXML(outputFile));
        String expectedResult = getXMLString(loadXML(exampleOutputFile));

        assertEquals(expectedResult, actualResult);
    }

    String getRelativePath(File file) {
        final int rootLength = new File("").getAbsolutePath().length();
        final String absFileName = file.getAbsolutePath();
        return absFileName.substring(rootLength + 1);
    }
}
