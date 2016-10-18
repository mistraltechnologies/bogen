package com.mistraltech.bogen.codegenerator;

import com.intellij.psi.PsiClass;
import com.mistraltech.bogen.codegenerator.buildergenerator.BuilderGeneratorProperties;

import java.util.Optional;

public abstract class AbstractInterfaceGeneratorTest extends AbstractGeneratorTest {
    @Override
    protected BuilderGeneratorProperties defaultGeneratorProperties() {
        return super.defaultGeneratorProperties()
                .setGenerateInterface(true);
    }

    // TODO is this required?
    protected Optional<PsiClass> loadDefaultBaseClass() {
        return loadTestClassFromText("com/mistraltech/bog/core/TwoPhaseBuilder.java",
                "package com.mistraltech.bog.core;\n" +
                        "public class TwoPhaseBuilder<T> {}\n");
    }
}
