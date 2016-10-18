package com.mistraltech.bogen.plugin;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class BogenConfigurable implements Configurable {

    private final Project project;

    private BogenConfiguration bogenConfiguration;

    public BogenConfigurable(Project project) {
        this.project = project;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "BOG Builder Generator";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        bogenConfiguration = new BogenConfiguration(project);
        return bogenConfiguration.getMainPanel();
    }

    @Override
    public boolean isModified() {
        return bogenConfiguration != null && bogenConfiguration.isModified();
    }

    @Override
    public void apply() throws ConfigurationException {
        if (bogenConfiguration != null) {
            bogenConfiguration.save();
        }
    }

    @Override
    public void reset() {
        if (bogenConfiguration != null) {
            bogenConfiguration.reset();
        }
    }

    @Override
    public void disposeUIResources() {
        bogenConfiguration = null;
    }
}
