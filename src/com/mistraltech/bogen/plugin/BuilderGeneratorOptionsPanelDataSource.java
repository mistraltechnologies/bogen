package com.mistraltech.bogen.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface BuilderGeneratorOptionsPanelDataSource {
    @NotNull
    Project getProject();

    @NotNull
    String getRecentsKey();

    @NotNull
    String getPackageName();

    @NotNull
    PsiClass getBuiltClass();

    @NotNull
    String getDefaultClassName();

    @NotNull
    VirtualFile getDefaultRoot();

    @NotNull
    List<VirtualFile> getCandidateRoots();

    @NotNull
    List<PsiMethod> getCandidateConstructors();

    boolean getDefaultIsExtensible();
}
