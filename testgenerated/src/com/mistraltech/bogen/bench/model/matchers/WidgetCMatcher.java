package com.mistraltech.bogen.bench.model.matchers;

import com.mistraltech.smog.core.CompositePropertyMatcher;
import com.mistraltech.smog.core.MatchAccumulator;
import com.mistraltech.smog.core.PropertyMatcher;
import com.mistraltech.smog.core.ReflectingPropertyMatcher;
import com.mistraltech.smog.core.annotation.Matches;
import com.mistraltech.bogen.bench.model.WidgetC;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;

@Matches(WidgetC.class)
public final class WidgetCMatcher extends CompositePropertyMatcher<WidgetC> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "a WidgetC";
    private final PropertyMatcher<String> aMatcher = new ReflectingPropertyMatcher<String>("a", this);
    private final PropertyMatcher<Byte> bMatcher = new ReflectingPropertyMatcher<Byte>("b", this);
    private final PropertyMatcher<Character> cMatcher = new ReflectingPropertyMatcher<Character>("c", this);
    private final PropertyMatcher<Double> dMatcher = new ReflectingPropertyMatcher<Double>("d", this);
    private final PropertyMatcher<Float> fMatcher = new ReflectingPropertyMatcher<Float>("f", this);
    private final PropertyMatcher<Integer> iMatcher = new ReflectingPropertyMatcher<Integer>("i", this);
    private final PropertyMatcher<Short> sMatcher = new ReflectingPropertyMatcher<Short>("s", this);

    private WidgetCMatcher(final String matchedObjectDescription, final WidgetC template) {
        super(matchedObjectDescription);
        if (template != null) {
            hasA(template.getA());
            hasB(template.getB());
            hasC(template.getC());
            hasD(template.getD());
            hasF(template.getF());
            hasI(template.getI());
            hasS(template.getS());
        }
    }

    public static WidgetCMatcher aWidgetCThat() {
        return new WidgetCMatcher(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static WidgetCMatcher aWidgetCLike(final WidgetC template) {
        return new WidgetCMatcher(MATCHED_OBJECT_DESCRIPTION, template);
    }

    public WidgetCMatcher hasA(final String a) {
        return hasA(equalTo(a));
    }

    public WidgetCMatcher hasA(final Matcher<? super String> aMatcher) {
        this.aMatcher.setMatcher(aMatcher);
        return this;
    }

    public WidgetCMatcher hasB(final byte b) {
        return hasB(equalTo(b));
    }

    public WidgetCMatcher hasB(final Matcher<? super Byte> bMatcher) {
        this.bMatcher.setMatcher(bMatcher);
        return this;
    }

    public WidgetCMatcher hasC(final char c) {
        return hasC(equalTo(c));
    }

    public WidgetCMatcher hasC(final Matcher<? super Character> cMatcher) {
        this.cMatcher.setMatcher(cMatcher);
        return this;
    }

    public WidgetCMatcher hasD(final double d) {
        return hasD(equalTo(d));
    }

    public WidgetCMatcher hasD(final Matcher<? super Double> dMatcher) {
        this.dMatcher.setMatcher(dMatcher);
        return this;
    }

    public WidgetCMatcher hasF(final float f) {
        return hasF(equalTo(f));
    }

    public WidgetCMatcher hasF(final Matcher<? super Float> fMatcher) {
        this.fMatcher.setMatcher(fMatcher);
        return this;
    }

    public WidgetCMatcher hasI(final int i) {
        return hasI(equalTo(i));
    }

    public WidgetCMatcher hasI(final Matcher<? super Integer> iMatcher) {
        this.iMatcher.setMatcher(iMatcher);
        return this;
    }

    public WidgetCMatcher hasS(final short s) {
        return hasS(equalTo(s));
    }

    public WidgetCMatcher hasS(final Matcher<? super Short> sMatcher) {
        this.sMatcher.setMatcher(sMatcher);
        return this;
    }

    @Override
    protected void matchesSafely(final WidgetC item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
