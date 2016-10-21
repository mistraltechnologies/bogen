package com.mistraltech.bogen.bench.basic_extensible_if;

import com.mistraltech.bogen.bench.model.WidgetA;
import org.junit.Test;

import static com.mistraltech.bogen.bench.basic_extensible.WidgetABuilder.aWidgetAFrom;
import static com.mistraltech.bogen.bench.basic_extensible_if.WidgetABuilder.aWidgetA;
import static com.mistraltech.bogen.bench.model.matchers.WidgetAMatcher.aWidgetAThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBasicExtensible {
    @Test
    public void test() {
        WidgetABuilder<?, WidgetA> widgetABuilder = aWidgetA().withProp1("prop1").withProp2("prop2");
        WidgetA widgetA1 = widgetABuilder.build();
        WidgetA widgetA2 = aWidgetAFrom(widgetA1).build();

        assertThat(widgetA1, is(aWidgetAThat().hasProp1("prop1").hasProp2("prop2")));
        assertThat(widgetA2, is(aWidgetAThat().hasProp1("prop1").hasProp2("prop2")));
    }
}
