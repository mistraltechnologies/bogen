package com.mistraltech.bogen.bench.setter_throws_if;

import com.mistraltech.bogen.bench.model.WidgetE;
import org.junit.Test;

import static com.mistraltech.bogen.bench.model.matchers.WidgetEMatcher.aWidgetEThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.mistraltech.bogen.bench.setter_throws_if.WidgetEBuilder.aWidgetE;

public class TestSetterThrows {
    @Test(expected = NumberFormatException.class)
    public void testException() {
        aWidgetE()
                .withProp1("prop1")
                .withProp2("prop2")
                .build();
    }

    @Test
    public void testWithoutException() {
        WidgetE widgetE = aWidgetE()
                .withProp1("10")
                .withProp2("20")
                .build();

        assertThat(widgetE, is(aWidgetEThat()
                .hasProp1("10")
                .hasProp2("20")));
    }
}
