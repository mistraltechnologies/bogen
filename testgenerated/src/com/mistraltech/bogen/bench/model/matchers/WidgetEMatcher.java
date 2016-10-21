package com.mistraltech.bogen.bench.model.matchers;

import com.mistraltech.smog.core.CompositePropertyMatcher;
import com.mistraltech.smog.core.MatchAccumulator;
import com.mistraltech.smog.core.PropertyMatcher;
import com.mistraltech.smog.core.ReflectingPropertyMatcher;
import com.mistraltech.smog.core.annotation.Matches;
import com.mistraltech.bogen.bench.model.WidgetE;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;

@Matches(WidgetE.class)
public final class WidgetEMatcher extends CompositePropertyMatcher<WidgetE> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "a WidgetE";
    private final PropertyMatcher<String> prop1Matcher = new ReflectingPropertyMatcher<String>("prop1", this);
    private final PropertyMatcher<String> prop2Matcher = new ReflectingPropertyMatcher<String>("prop2", this);

    private WidgetEMatcher(final String matchedObjectDescription, final WidgetE template) {
        super(matchedObjectDescription);
        if (template != null) {
            hasProp1(template.getProp1());
            hasProp2(template.getProp2());
        }
    }

    public static WidgetEMatcher aWidgetEThat() {
        return new WidgetEMatcher(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static WidgetEMatcher aWidgetELike(final WidgetE template) {
        return new WidgetEMatcher(MATCHED_OBJECT_DESCRIPTION, template);
    }

    public WidgetEMatcher hasProp1(final String prop1) {
        return hasProp1(equalTo(prop1));
    }

    public WidgetEMatcher hasProp1(final Matcher<? super String> prop1Matcher) {
        this.prop1Matcher.setMatcher(prop1Matcher);
        return this;
    }

    public WidgetEMatcher hasProp2(final String prop2) {
        return hasProp2(equalTo(prop2));
    }

    public WidgetEMatcher hasProp2(final Matcher<? super String> prop2Matcher) {
        this.prop2Matcher.setMatcher(prop2Matcher);
        return this;
    }

    @Override
    protected void matchesSafely(final WidgetE item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
