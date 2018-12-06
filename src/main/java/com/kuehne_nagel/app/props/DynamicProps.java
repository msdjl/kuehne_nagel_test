package com.kuehne_nagel.app.props;

import com.kuehne_nagel.app.exceptions.CantFindDynamicPropertyException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicProps {
    private static Pattern pattern = Pattern.compile("<(.+)>");

    public static boolean isDynamicProp(String prop) {
        return DynamicProps.pattern.matcher(prop).matches();
    }

    public static String getDynamicValue(String prop, IDynamicPropsProvider propsProvider) throws CantFindDynamicPropertyException {
        Matcher matcher = pattern.matcher(prop);
        matcher.matches();
        return propsProvider.get(matcher.group(1));
    }
}
