package com.kuehne_nagel.app.props;

import com.kuehne_nagel.app.exceptions.CantFindDynamicPropertyException;
import com.kuehne_nagel.app.exceptions.CantFindPropertyException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props {
    private Properties props = new Properties();

    public Props(File file) throws IOException {
        loadProperties(file);
    }

    public String getProperty(String name) throws CantFindDynamicPropertyException, CantFindPropertyException {
        String nameInQuotes = addQuotes(name);
        String rawPropertyValue = props.getProperty(nameInQuotes);

        if (rawPropertyValue == null)
            throw new CantFindPropertyException(name);

        String propValue = removeQuotes(rawPropertyValue);
        if (DynamicProps.isDynamicProp(propValue))
            return DynamicProps.getDynamicValue(propValue);
        return propValue;
    }

    private String addQuotes(String str) {
        return "\"" + str + "\"";
    }

    private String removeQuotes(String str) {
        if (str.startsWith("\"") && str.endsWith("\""))
            return str.substring(1, str.length() - 1);
        return str;
    }

    private void loadProperties(File file) throws IOException {
        InputStream input = new FileInputStream(file);
        props.load(input);
    }
}
