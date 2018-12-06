package com.kuehne_nagel.test;

import com.kuehne_nagel.app.exceptions.CantFindDynamicPropertyException;
import com.kuehne_nagel.app.exceptions.CantFindPropertyException;
import com.kuehne_nagel.app.props.Props;
import com.kuehne_nagel.test.util.TestDynamicPropsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PropsTest extends TestBase {
    Props props;

    PropsTest() throws IOException {
        File propsFile = getResourceFile("example.properties");
        props = new Props(propsFile, new TestDynamicPropsProvider());
    }

    @Test
    @DisplayName("getProperty can return plain values")
    void getPropertyReturnsValues() {
        assertAll(
                () -> assertEquals("UC_STOCK_LEVEL", props.getProperty("TRNNAM")),
                () -> assertEquals("", props.getProperty("EMPTY"))
        );
    }

    @Test
    @DisplayName("getProperty can return dynamic values")
    void getPropertyReturnsDynamicValues() throws CantFindPropertyException, CantFindDynamicPropertyException {
        assertEquals("dyn_param_time", props.getProperty("CURRENT_DATETIME"));
    }

    @Test
    @DisplayName("getProperty throws an exception in case of nonexistent property")
    void getPropertyTCanThrowException() {
        assertThrows(CantFindPropertyException.class, () -> {
            props.getProperty("nonexistent_property");
        });
    }
}
