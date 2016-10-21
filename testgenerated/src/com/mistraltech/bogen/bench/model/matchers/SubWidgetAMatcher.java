package com.mistraltech.bogen.bench.model.matchers;

import com.mistraltech.smog.core.CompositePropertyMatcher;
import com.mistraltech.smog.core.MatchAccumulator;
import com.mistraltech.smog.core.PropertyMatcher;
import com.mistraltech.smog.core.ReflectingPropertyMatcher;
import com.mistraltech.smog.core.annotation.Matches;
import com.mistraltech.bogen.bench.model.SubWidgetA;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;

@Matches(SubWidgetA.class)
public final class SubWidgetAMatcher extends CompositePropertyMatcher<SubWidgetA> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "a SubWidgetA";
    private final PropertyMatcher<String> prop1Matcher = new ReflectingPropertyMatcher<String>("prop1", this);
    private final PropertyMatcher<String> prop2Matcher = new ReflectingPropertyMatcher<String>("prop2", this);
    private final PropertyMatcher<String> prop3Matcher = new ReflectingPropertyMatcher<String>("prop3", this);

    private SubWidgetAMatcher(final String matchedObjectDescription, final SubWidgetA template) {
        super(matchedObjectDescription);
        if (template != null) {
            hasProp1(template.getProp1());
            hasProp2(template.getProp2());
            hasProp3(template.getProp3());
        }
    }

    public static SubWidgetAMatcher aSubWidgetAThat() {
        return new SubWidgetAMatcher(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static SubWidgetAMatcher aSubWidgetALike(final SubWidgetA template) {
        return new SubWidgetAMatcher(MATCHED_OBJECT_DESCRIPTION, template);
    }

    public SubWidgetAMatcher hasProp1(final String prop1) {
        return hasProp1(equalTo(prop1));
    }

    public SubWidgetAMatcher hasProp1(final Matcher<? super String> prop1Matcher) {
        this.prop1Matcher.setMatcher(prop1Matcher);
        return this;
    }

    public SubWidgetAMatcher hasProp2(final String prop2) {
        return hasProp2(equalTo(prop2));
    }

    public SubWidgetAMatcher hasProp2(final Matcher<? super String> prop2Matcher) {
        this.prop2Matcher.setMatcher(prop2Matcher);
        return this;
    }

    public SubWidgetAMatcher hasProp3(final String prop3) {
        return hasProp3(equalTo(prop3));
    }

    public SubWidgetAMatcher hasProp3(final Matcher<? super String> prop3Matcher) {
        this.prop3Matcher.setMatcher(prop3Matcher);
        return this;
    }

    @Override
    protected void matchesSafely(final SubWidgetA item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
