package com.kuehne_nagel.test.util;

import com.kuehne_nagel.app.exceptions.CantFindDynamicPropertyException;
import com.kuehne_nagel.app.props.IDynamicPropsProvider;

public class TestDynamicPropsProvider implements IDynamicPropsProvider {
    public String get(String property) throws CantFindDynamicPropertyException {
        switch (property) {
            case "CURRENT_DATETIME": return "dyn_param_time";
            case "REQUEST_ID": return "dyn_param_request";
            case "UUID": return "dyn_param_uuid";
        }
        throw new CantFindDynamicPropertyException(property);
    }
}
