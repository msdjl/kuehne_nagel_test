package com.kuehne_nagel.app.props;

import com.kuehne_nagel.app.exceptions.CantFindDynamicPropertyException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicProps {
    private static Pattern pattern = Pattern.compile("<(.+)>");

    public static boolean isDynamicProp(String prop) {
        return DynamicProps.pattern.matcher(prop).matches();
    }

    public static String getDynamicValue(String prop) throws CantFindDynamicPropertyException {
        Matcher matcher = pattern.matcher(prop);
        matcher.matches();
        switch (matcher.group(1)) {
            case "CURRENT_DATETIME": return getTimestamp();
            case "REQUEST_ID": return getRequestId();
            case "UUID": return getUUID();
        }
        throw new CantFindDynamicPropertyException(prop);
    }

    private static String getTimestamp() {
        return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
    }

    private static String getUUID() {
        return UUID.randomUUID().toString();
    }

    private static String getRequestId() {
        return UUID.randomUUID().toString();
    }
}
