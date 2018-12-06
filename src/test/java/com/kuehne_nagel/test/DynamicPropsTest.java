package com.kuehne_nagel.test;

import com.kuehne_nagel.app.exceptions.CantFindDynamicPropertyException;
import com.kuehne_nagel.test.util.TestDynamicPropsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.kuehne_nagel.app.props.DynamicProps.getDynamicValue;
import static com.kuehne_nagel.app.props.DynamicProps.isDynamicProp;
import static org.junit.jupiter.api.Assertions.*;

class DynamicPropsTest extends TestBase {
    @Test
    @DisplayName("isDynamicProp returns true for a string with triangle brackets")
    void isDynamicPropWithValueInBrackets() {
        assertTrue(isDynamicProp("<prop>"));
    }

    @Test
    @DisplayName("isDynamicProp returns false for a string without triangle brackets")
    void isDynamicPropWithValueWithoutBrackets() {
        assertAll(
                () -> assertFalse(isDynamicProp("p<r>op")),
                () -> assertFalse(isDynamicProp("<prop")),
                () -> assertFalse(isDynamicProp("prop>"))
        );
    }

    @Test
    @DisplayName("isDynamicProp returns false for an empty value")
    void isDynamicPropWithEmptyValue() {
        assertAll(
                () -> assertFalse(isDynamicProp("")),
                () -> assertFalse(isDynamicProp("<>"))
        );
    }

    @Test
    @DisplayName("getDynamicValue returns value for an implemented property")
    void getDynamicValueWithImplementedProp() throws CantFindDynamicPropertyException {
        assertEquals("dyn_param_time", getDynamicValue("<CURRENT_DATETIME>", new TestDynamicPropsProvider()));
    }

    @Test
    @DisplayName("getDynamicValue throws an exception in case of unimplemented property")
    void getDynamicValueWithUnimplementedProp() {
        assertThrows(CantFindDynamicPropertyException.class, () -> {
            getDynamicValue("<unimplemented property>", new TestDynamicPropsProvider());
        });
    }
}
