package com.mistraltech.bogen.bench.property_types;

import com.mistraltech.bogen.bench.model.WidgetC;
import org.junit.Test;

import static com.mistraltech.bogen.bench.model.matchers.WidgetCMatcher.aWidgetCThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestPropertyTypes {
    @Test
    public void test() {
        WidgetC widgetC = WidgetCBuilder.aWidgetC()
                .withA("a")
                .withB((byte) 123)
                .withC('A')
                .withD(1.23D)
                .withF(3.21F)
                .withI(12345)
                .withS((short) 1234)
                .build();

        assertThat(widgetC, is(aWidgetCThat()
                .hasA("a")
                .hasB((byte) 123)
                .hasC('A')
                .hasD(1.23D)
                .hasF(3.21F)
                .hasI(12345)
                .hasS((short) 1234)));
    }

    @Test
    public void testDefaults() {
        WidgetC widgetC = WidgetCBuilder.aWidgetC()
                .build();

        assertThat(widgetC, is(aWidgetCThat()
                .hasA((String) null)
                .hasB((byte) 0)
                .hasC('\0')
                .hasD(0.0D)
                .hasF(0.0F)
                .hasI(0)
                .hasS((short) 0)));
    }
}
