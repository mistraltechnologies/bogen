package com.mistraltech.bogen.bench.minimal_class_if;

import com.mistraltech.bogen.bench.model.WidgetD;
import com.mistraltech.bogen.bench.model.matchers.WidgetDMatcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestMinimalClass {
    @Test
    public void test() {
        WidgetD widgetD = WidgetDBuilder.aWidgetD().build();
        assertThat(widgetD, is(WidgetDMatcher.aWidgetDThat()));
    }
}
