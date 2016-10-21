package com.mistraltech.bogen.bench.model.matchers;

import com.mistraltech.smog.core.CompositePropertyMatcher;
import com.mistraltech.smog.core.MatchAccumulator;
import com.mistraltech.smog.core.PropertyMatcher;
import com.mistraltech.smog.core.ReflectingPropertyMatcher;
import com.mistraltech.smog.core.annotation.Matches;
import com.mistraltech.bogen.bench.model.SubWidgetB;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;

@Matches(SubWidgetB.class)
public final class SubWidgetBMatcher<P1> extends CompositePropertyMatcher<SubWidgetB<P1>> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "a SubWidgetB";
    private final PropertyMatcher<P1> prop1Matcher = new ReflectingPropertyMatcher<P1>("prop1", this);
    private final PropertyMatcher<P1> prop2Matcher = new ReflectingPropertyMatcher<P1>("prop2", this);
    private final PropertyMatcher<P1> prop3Matcher = new ReflectingPropertyMatcher<P1>("prop3", this);
    private final PropertyMatcher<P1> prop4Matcher = new ReflectingPropertyMatcher<P1>("prop4", this);

    private SubWidgetBMatcher(final String matchedObjectDescription, final SubWidgetB<P1> template) {
        super(matchedObjectDescription);
        if (template != null) {
            hasProp1(template.getProp1());
            hasProp2(template.getProp2());
            hasProp3(template.getProp3());
            hasProp4(template.getProp4());
        }
    }

    public static <P1> SubWidgetBMatcher<P1> aSubWidgetBThat() {
        return new SubWidgetBMatcher<P1>(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static <P1> SubWidgetBMatcher<P1> aSubWidgetBLike(final SubWidgetB<P1> template) {
        return new SubWidgetBMatcher<P1>(MATCHED_OBJECT_DESCRIPTION, template);
    }

    public SubWidgetBMatcher<P1> hasProp1(final P1 prop1) {
        return hasProp1(equalTo(prop1));
    }

    public SubWidgetBMatcher<P1> hasProp1(final Matcher<? super P1> prop1Matcher) {
        this.prop1Matcher.setMatcher(prop1Matcher);
        return this;
    }

    public SubWidgetBMatcher<P1> hasProp2(final P1 prop2) {
        return hasProp2(equalTo(prop2));
    }

    public SubWidgetBMatcher<P1> hasProp2(final Matcher<? super P1> prop2Matcher) {
        this.prop2Matcher.setMatcher(prop2Matcher);
        return this;
    }

    public SubWidgetBMatcher<P1> hasProp3(final P1 prop3) {
        return hasProp3(equalTo(prop3));
    }

    public SubWidgetBMatcher<P1> hasProp3(final Matcher<? super P1> prop3Matcher) {
        this.prop3Matcher.setMatcher(prop3Matcher);
        return this;
    }

    public SubWidgetBMatcher<P1> hasProp4(final P1 prop4) {
        return hasProp4(equalTo(prop4));
    }

    public SubWidgetBMatcher<P1> hasProp4(final Matcher<? super P1> prop4Matcher) {
        this.prop4Matcher.setMatcher(prop4Matcher);
        return this;
    }

    @Override
    protected void matchesSafely(final SubWidgetB<P1> item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
