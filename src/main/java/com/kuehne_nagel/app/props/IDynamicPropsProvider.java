package com.kuehne_nagel.app.props;

import com.kuehne_nagel.app.exceptions.CantFindDynamicPropertyException;

public interface IDynamicPropsProvider {
    String get(String property) throws CantFindDynamicPropertyException;
}
