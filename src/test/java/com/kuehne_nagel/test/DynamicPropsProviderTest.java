package com.kuehne_nagel.test;

import com.kuehne_nagel.app.exceptions.CantFindDynamicPropertyException;
import com.kuehne_nagel.app.props.DynamicPropsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DynamicPropsProviderTest extends TestBase {
    DynamicPropsProvider provider = new DynamicPropsProvider();
    String UUIDRegex = "[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}";

    @Test
    @DisplayName("CURRENT_DATETIME prop")
    void currentDateTimeProp() throws CantFindDynamicPropertyException, ParseException {
        Date expectedDate = new Date();
        Date actualDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(provider.get("CURRENT_DATETIME"));
        assertEquals(expectedDate.getTime(), actualDate.getTime(), 10);
    }

    @Test
    @DisplayName("REQUEST_ID prop")
    void requestIdProp() throws CantFindDynamicPropertyException {
        assertTrue(provider.get("REQUEST_ID").matches(UUIDRegex));
    }

    @Test
    @DisplayName("UUID prop")
    void UUIDProp() throws CantFindDynamicPropertyException {
        assertTrue(provider.get("UUID").matches(UUIDRegex));
    }

    @Test
    @DisplayName("get method throws an exception in case of unimplemented property")
    void getDynamicValueWithUnimplementedProp() {
        assertThrows(CantFindDynamicPropertyException.class, () -> {
            provider.get("something");
        });
    }
}
