package com.mistraltech.bogen.bench.model.matchers;

import com.mistraltech.smog.core.CompositePropertyMatcher;
import com.mistraltech.smog.core.MatchAccumulator;
import com.mistraltech.smog.core.PropertyMatcher;
import com.mistraltech.smog.core.ReflectingPropertyMatcher;
import com.mistraltech.smog.core.annotation.Matches;
import com.mistraltech.bogen.bench.model.WidgetB;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;

@Matches(WidgetB.class)
public final class WidgetBMatcher<P1> extends CompositePropertyMatcher<WidgetB<P1>> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "a WidgetB";
    private final PropertyMatcher<P1> prop1Matcher = new ReflectingPropertyMatcher<P1>("prop1", this);
    private final PropertyMatcher<P1> prop2Matcher = new ReflectingPropertyMatcher<P1>("prop2", this);

    private WidgetBMatcher(final String matchedObjectDescription, final WidgetB<P1> template) {
        super(matchedObjectDescription);
        if (template != null) {
            hasProp1(template.getProp1());
            hasProp2(template.getProp2());
        }
    }

    public static <P1> WidgetBMatcher<P1> aWidgetBThat() {
        return new WidgetBMatcher<P1>(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static <P1> WidgetBMatcher<P1> aWidgetBLike(final WidgetB<P1> template) {
        return new WidgetBMatcher<P1>(MATCHED_OBJECT_DESCRIPTION, template);
    }

    public WidgetBMatcher<P1> hasProp1(final P1 prop1) {
        return hasProp1(equalTo(prop1));
    }

    public WidgetBMatcher<P1> hasProp1(final Matcher<? super P1> prop1Matcher) {
        this.prop1Matcher.setMatcher(prop1Matcher);
        return this;
    }

    public WidgetBMatcher<P1> hasProp2(final P1 prop2) {
        return hasProp2(equalTo(prop2));
    }

    public WidgetBMatcher<P1> hasProp2(final Matcher<? super P1> prop2Matcher) {
        this.prop2Matcher.setMatcher(prop2Matcher);
        return this;
    }

    @Override
    protected void matchesSafely(final WidgetB<P1> item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
