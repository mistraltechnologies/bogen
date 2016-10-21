package com.mistraltech.bogen.bench.extensible_generic_subclass_if;

import com.mistraltech.bogen.bench.model.SubWidgetB;
import com.mistraltech.bogen.bench.model.matchers.SubWidgetBMatcher;
import org.junit.Test;

import static com.mistraltech.bogen.bench.extensible_generic_subclass_if.SubWidgetBBuilder.aSubWidgetBFrom;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestExtensibleGenericSubclass {
    @Test
    public void test() {
        SubWidgetB<String> subWidgetB = SubWidgetBBuilder.<String>aSubWidgetB()
                .withProp1("prop1")
                .withProp2("prop2")
                .withProp3("prop3")
                .withProp4("prop4")
                .build();

        SubWidgetB<String> subWidgetB1 = aSubWidgetBFrom(subWidgetB).build();

        assertThat(subWidgetB, is(SubWidgetBMatcher.<String>aSubWidgetBThat()
                .hasProp1("prop1").hasProp2("prop2").hasProp3("prop3").hasProp4("prop4")));
        assertThat(subWidgetB1, is(SubWidgetBMatcher.<String>aSubWidgetBThat()
                .hasProp1("prop1").hasProp2("prop2").hasProp3("prop3").hasProp4("prop4")));
    }

    @Test
    public void testWithDefaults() {
        SubWidgetBBuilder.SubWidgetBBuilderType<String> subWidgetBBuilder = SubWidgetBBuilder.aSubWidgetB();

        subWidgetBBuilder.getProp1().setDefault("a");
        subWidgetBBuilder.getProp2().setDefault("b");
        subWidgetBBuilder.getProp3().setDefault("c");
        subWidgetBBuilder.getProp4().setDefault("d");

        SubWidgetB<String> subWidgetB = subWidgetBBuilder.build();

        assertThat(subWidgetB, is(SubWidgetBMatcher.<String>aSubWidgetBThat()
                .hasProp1("a").hasProp2("b").hasProp3("c").hasProp4("d")));
    }
}
