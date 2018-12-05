package com.kuehne_nagel.app.xml;

import com.kuehne_nagel.app.exceptions.CantFindDynamicPropertyException;
import com.kuehne_nagel.app.exceptions.CantFindPropertyException;
import com.kuehne_nagel.app.props.Props;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLProcessor {
    private static Pattern pattern = Pattern.compile("\\$\\{(.+)}");

    public static void processPlaceholders(Node node, Props props) throws CantFindDynamicPropertyException, CantFindPropertyException {
        if (isTextNode(node)) {
            String nodeValue = node.getNodeValue();
            if (isPlaceholder(nodeValue)) {
                String propertyName = getPropertyName(nodeValue);
                String propertyValue = props.getProperty(propertyName);
                node.setNodeValue(propertyValue);
            }
        } else if (node.hasChildNodes()) {
            NodeList nodeList = node.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                processPlaceholders(nodeList.item(i), props);
            }
        }
    }

    private static String getPropertyName(String placeholder) {
        Matcher matcher = pattern.matcher(placeholder);
        matcher.matches();
        return matcher.group(1);
    }

    private static boolean isPlaceholder(String str) {
        return pattern.matcher(str).matches();
    }

    private static boolean isTextNode(Node node) {
        return node.getNodeType() == Node.TEXT_NODE;
    }
}
