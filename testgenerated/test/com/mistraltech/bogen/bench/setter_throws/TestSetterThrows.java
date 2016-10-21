package com.mistraltech.bogen.bench.setter_throws;

import com.mistraltech.bogen.bench.model.WidgetE;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.mistraltech.bogen.bench.model.matchers.WidgetEMatcher.aWidgetEThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.mistraltech.bogen.bench.setter_throws.WidgetEBuilder.aWidgetE;

public class TestSetterThrows {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testException() {
        thrown.expect(RuntimeException.class);
        thrown.expectCause(isA(NumberFormatException.class));

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
