package com.mistraltech.bogen.bench.property_types_if;

import com.mistraltech.bogen.bench.model.WidgetC;
import com.mistraltech.bog.core.annotation.Builds;
import org.junit.Test;

import java.util.function.Supplier;

import static com.mistraltech.bogen.bench.model.matchers.WidgetCMatcher.aWidgetCThat;
import static com.mistraltech.bog.core.picker.SingleValuePicker.singleValuePicker;
import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;
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

    @Test
    public void testOverriddenDefaults() {
        WidgetC widgetC = builderOf(WidgetCBuilderWithDefaults.class)
                .build();

        assertThat(widgetC, is(aWidgetCThat()
                .hasA("A")
                .hasB((byte) 1)
                .hasC('C')
                .hasD(2.0D)
                .hasF(3.0F)
                .hasI(4)
                .hasS((short) 5)));
    }

    @Builds(WidgetC.class)
    interface WidgetCBuilderWithDefaults extends WidgetCBuilder {
        default Supplier<String> getDefaultA() {
            return singleValuePicker("A");
        }

        default Supplier<Byte> getDefaultB() {
            return singleValuePicker((byte) 1);
        }

        default Supplier<Character> getDefaultC() {
            return singleValuePicker('C');
        }

        default Supplier<Double> getDefaultD() {
            return singleValuePicker(2.0D);
        }

        default Supplier<Float> getDefaultF() {
            return singleValuePicker(3.0F);
        }

        default Supplier<Integer> getDefaultI() {
            return singleValuePicker(4);
        }

        default Supplier<Short> getDefaultS() {
            return singleValuePicker((short) 5);
        }
    }
}
