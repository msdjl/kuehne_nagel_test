package com.kuehne_nagel.app.props;

import com.kuehne_nagel.app.exceptions.CantFindDynamicPropertyException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class DynamicPropsProvider implements IDynamicPropsProvider {
    public String get(String property) throws CantFindDynamicPropertyException {
        switch (property) {
            case "CURRENT_DATETIME": return getTimestamp();
            case "REQUEST_ID": return getRequestId();
            case "UUID": return getUUID();
        }
        throw new CantFindDynamicPropertyException(property);
    }

    private String getTimestamp() {
        return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
    }

    private String getUUID() {
        return UUID.randomUUID().toString();
    }

    private String getRequestId() {
        return UUID.randomUUID().toString();
    }
}
