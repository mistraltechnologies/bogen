package com.mistraltech.bogen.bench.basic_generic;

import com.mistraltech.bogen.bench.model.WidgetB;
import com.mistraltech.bogen.bench.model.matchers.WidgetBMatcher;
import org.junit.Test;

import static com.mistraltech.bogen.bench.basic_generic.WidgetBBuilder.aWidgetBFrom;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBasicGeneric {
    @Test
    public void test() {
        WidgetBBuilder<String> widgetBBuilder = WidgetBBuilder.<String>aWidgetB().withProp1("prop1").withProp2("prop2");
        WidgetB<String> widgetB1 = widgetBBuilder.build();
        WidgetB<String> widgetB2 = aWidgetBFrom(widgetB1).build();

        assertThat(widgetB1, is(WidgetBMatcher.<String>aWidgetBThat().hasProp1("prop1").hasProp2("prop2")));
        assertThat(widgetB2, is(WidgetBMatcher.<String>aWidgetBThat().hasProp1("prop1").hasProp2("prop2")));
    }
}
