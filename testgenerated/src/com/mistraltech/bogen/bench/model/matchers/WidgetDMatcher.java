package com.mistraltech.bogen.bench.model.matchers;

import com.mistraltech.smog.core.CompositePropertyMatcher;
import com.mistraltech.smog.core.MatchAccumulator;
import com.mistraltech.smog.core.annotation.Matches;
import com.mistraltech.bogen.bench.model.WidgetD;

@Matches(WidgetD.class)
public final class WidgetDMatcher extends CompositePropertyMatcher<WidgetD> {
    private static final String MATCHED_OBJECT_DESCRIPTION = "a WidgetD";

    private WidgetDMatcher(final String matchedObjectDescription, final WidgetD template) {
        super(matchedObjectDescription);
    }

    public static WidgetDMatcher aWidgetDThat() {
        return new WidgetDMatcher(MATCHED_OBJECT_DESCRIPTION, null);
    }

    public static WidgetDMatcher aWidgetDLike(final WidgetD template) {
        return new WidgetDMatcher(MATCHED_OBJECT_DESCRIPTION, template);
    }

    @Override
    protected void matchesSafely(final WidgetD item, final MatchAccumulator matchAccumulator) {
        super.matchesSafely(item, matchAccumulator);
    }
}
