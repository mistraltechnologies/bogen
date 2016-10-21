package com.mistraltech.bogen.bench.basic_subclass;

import com.mistraltech.bogen.bench.model.SubWidgetA;
import org.junit.Test;

import static com.mistraltech.bogen.bench.basic_subclass.SubWidgetABuilder.aSubWidgetA;
import static com.mistraltech.bogen.bench.basic_subclass.SubWidgetABuilder.aSubWidgetAFrom;
import static com.mistraltech.bogen.bench.model.matchers.SubWidgetAMatcher.aSubWidgetAThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBasicSubclass {
    @Test
    public void test() {
        SubWidgetA subWidgetA = aSubWidgetA()
                .withProp1("prop1")
                .withProp2("prop2")
                .withProp3("prop3")
                .build();

        SubWidgetA subWidgetA1 = aSubWidgetAFrom(subWidgetA).build();

        assertThat(subWidgetA, is(aSubWidgetAThat().hasProp1("prop1").hasProp2("prop2").hasProp3("prop3")));
        assertThat(subWidgetA1, is(aSubWidgetAThat().hasProp1("prop1").hasProp2("prop2").hasProp3("prop3")));
    }
}
