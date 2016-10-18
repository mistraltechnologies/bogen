package com.mistraltech.bogen.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.FileIndex;
import com.intellij.openapi.roots.JavaProjectRootsUtil;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiNameHelper;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.ProjectScope;
import com.intellij.refactoring.ui.ClassNameReferenceEditor;
import com.intellij.refactoring.ui.PackageNameReferenceEditorCombo;
import com.intellij.ui.ListCellRendererWrapper;
import com.intellij.ui.ReferenceEditorWithBrowseButton;
import com.intellij.util.PlatformIcons;
import com.mistraltech.bogen.utils.PsiUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Optional;

public class BuilderGeneratorOptionsPanel {
    private static final int PANEL_WIDTH_CHARS = 60;

    private static final String BOG_JAVASSIST_GENERATOR = "com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator";

    private final BuilderGeneratorOptionsPanelDataSource dataSource;

    private JPanel rootPanel;

    private JTextField classNameTextField;

    private JCheckBox makeExtensibleCheckBox;

    private PackageNameReferenceEditorCombo packageComponent;

    private JComboBox<ListItemWrapper<?>> destinationSourceRootComboBox;

    private JRadioButton aRadioButton;

    private JRadioButton anRadioButton;

    private JLabel buildsLabel;

    private JCheckBox extendsCheckBox;

    private ReferenceEditorWithBrowseButton superClassChooser;

    private JLabel sourceClassName;

    private JRadioButton classRadioButton;

    private JRadioButton interfaceRadioButton;

    private JComboBox<PsiMethod> constructorComboBox;

    private boolean generateFactoryMethods;

    public BuilderGeneratorOptionsPanel(@NotNull BuilderGeneratorOptionsPanelDataSource dataSource) {
        this.dataSource = dataSource;

        initialiseSourceClassNameField();
        initialiseGenerateTypeRadioButtons();
        initialiseClassNameField();
        initialiseMakeExtensibleCheckBox();
        initialiseExtendsFields();
        initialiseFactoryMethodPrefixRadioButtons();
        initialiseDestinationSourceRootComboBox();
        initialiseConstructorComboBox();
    }

    private void initialiseSourceClassNameField() {
        final String qualifiedName = dataSource.getBuiltClass().getQualifiedName();
        sourceClassName.setText(qualifiedName);
    }

    private void initialiseGenerateTypeRadioButtons() {
        boolean bogJavassist = isBogJavassistOnClasspath();
        boolean java8 = isProjectJava8();

        classRadioButton.setEnabled(true);
        interfaceRadioButton.setEnabled(bogJavassist && java8);

        if (interfaceRadioButton.isEnabled()) {
            interfaceRadioButton.setSelected(true);
        } else {
            classRadioButton.setSelected(true);
        }
    }

    private void initialiseClassNameField() {
        classNameTextField.setText(dataSource.getDefaultClassName());
    }

    private void initialiseExtendsFields() {
        superClassChooser.setEnabled(false);
        extendsCheckBox.setSelected(false);
        extendsCheckBox.addChangeListener(e -> onExtendsCheckBoxStateChange());
    }

    private void initialiseMakeExtensibleCheckBox() {
        // If we are building an abstract class, likelihood is we want the builder to be extensible
        if (PsiUtils.isAbstract(dataSource.getBuiltClass())) {
            makeExtensibleCheckBox.setSelected(true);
        } else {
            makeExtensibleCheckBox.setSelected(dataSource.getDefaultIsExtensible());
        }
        makeExtensibleCheckBox.setEnabled(true);
    }

    private void initialiseFactoryMethodPrefixRadioButtons() {
        // The factory method prefix is either 'a' or 'an', selected by radio buttons
        // and labelled by buildsLabel.
        String matchedClassName = dataSource.getBuiltClass().getName();

        if (matchedClassName == null) {
            throw new IllegalStateException("Matched class has no name");
        }

        aRadioButton.setText("a " + matchedClassName);
        anRadioButton.setText("an " + matchedClassName);

        if (hasVowelSound(matchedClassName)) {
            anRadioButton.setSelected(true);
        } else {
            aRadioButton.setSelected(true);
        }
    }

    private void initialiseDestinationSourceRootComboBox() {
        for (VirtualFile candidateRoot : dataSource.getCandidateRoots()) {
            ListItemWrapper<?> listItemWrapper = createSourceRootItemWrapper(candidateRoot);
            destinationSourceRootComboBox.addItem(listItemWrapper);
            if (candidateRoot.equals(dataSource.getDefaultRoot())) {
                destinationSourceRootComboBox.setSelectedItem(listItemWrapper);
            }
        }

        destinationSourceRootComboBox.setRenderer(new ListCellRendererWrapper<ListItemWrapper<?>>() {
            @Override
            public void customize(JList list, ListItemWrapper<?> listItemWrapper, int index, boolean selected, boolean hasFocus) {
                setIcon(listItemWrapper.getIcon());
                setText(listItemWrapper.getText());
            }
        });
    }

    private void initialiseConstructorComboBox() {
        dataSource.getCandidateConstructors().stream()
                .filter(m -> !m.getModifierList().hasModifierProperty(PsiModifier.PRIVATE))
                .forEach(constructorComboBox::addItem);

        constructorComboBox.setRenderer(new ListCellRendererWrapper<PsiMethod>() {
            @Override
            public void customize(JList list, PsiMethod method, int index, boolean selected, boolean hasFocus) {
                Optional.ofNullable(method)
                        .map(PsiMethod::getParameterList)
                        .ifPresent(l -> setText(l.getText()));
            }
        });
    }

    private void onExtendsCheckBoxStateChange() {
        superClassChooser.setEnabled(extendsCheckBox.isSelected());
    }

    @NotNull
    public VirtualFile getSelectedSourceRoot() {
        //noinspection unchecked
        return ((ListItemWrapper<VirtualFile>) destinationSourceRootComboBox.getSelectedItem()).getItem();
    }

    @NotNull
    public String getSelectedClassName() {
        return classNameTextField.getText();
    }

    @NotNull
    public String getSelectedPackageName() {
        return packageComponent.getText();
    }

    public boolean isMakeExtensible() {
        return makeExtensibleCheckBox.isSelected();
    }

    public boolean isAn() {
        return anRadioButton.isSelected();
    }

    public boolean isGenerateInterface() {
        return interfaceRadioButton.isSelected();
    }

    public boolean isGenerateFactoryMethods() {
        return classRadioButton.isSelected();
    }

    @Nullable
    public String getSuperClassName() {
        if (extendsCheckBox.isSelected()) {
            return superClassChooser.getText().trim();
        } else {
            return null;
        }
    }

    public PsiMethod getSelectedConstructor() {
        return (PsiMethod) constructorComboBox.getSelectedItem();
    }

    @NotNull
    public JComponent getRoot() {
        return rootPanel;
    }

    private boolean hasVowelSound(@NotNull String matchedClassName) {
        return "aeiou".contains(matchedClassName.substring(0, 1).toLowerCase());
    }

    @NotNull
    private ListItemWrapper<VirtualFile> createSourceRootItemWrapper(@NotNull VirtualFile candidateRoot) {
        String relativePath = ProjectUtil.calcRelativeToProjectPath(candidateRoot, dataSource.getProject(), true, false, true);
        return new ListItemWrapper<>(candidateRoot, relativePath, getSourceRootIcon(candidateRoot));
    }

    private void createUIComponents() {
        // This is called by the form handler for custom-created components
        Project project = dataSource.getProject();
        packageComponent = new PackageNameReferenceEditorCombo(dataSource.getPackageName(),
                project, dataSource.getRecentsKey(), "Choose Destination Package");
        packageComponent.setTextFieldPreferredWidth(PANEL_WIDTH_CHARS);

        GlobalSearchScope scope = JavaProjectRootsUtil.getScopeWithoutGeneratedSources(ProjectScope.getProjectScope(project), project);
        superClassChooser = new ClassNameReferenceEditor(project, null, scope);
        superClassChooser.setTextFieldPreferredWidth(PANEL_WIDTH_CHARS);
    }

    @NotNull
    private Icon getSourceRootIcon(@NotNull VirtualFile virtualFile) {
        FileIndex fileIndex = ProjectRootManager.getInstance(dataSource.getProject()).getFileIndex();

        if (fileIndex.isInTestSourceContent(virtualFile)) {
            return PlatformIcons.MODULES_TEST_SOURCE_FOLDER;
        } else if (fileIndex.isInSourceContent(virtualFile)) {
            return PlatformIcons.MODULES_SOURCE_FOLDERS_ICON;
        } else {
            return PlatformIcons.FOLDER_ICON;
        }
    }

    public ValidationInfo doValidate() {
        String className = classNameTextField.getText().trim();
        if (StringUtil.isEmpty(className)) {
            return new ValidationInfo("Class name is empty", classNameTextField);
        }

        if (!PsiNameHelper.getInstance(dataSource.getProject()).isIdentifier(className)) {
            return new ValidationInfo("Class name is not a valid identifier", classNameTextField);
        }

        return null;
    }

    private boolean isBogJavassistOnClasspath() {
        final Project project = dataSource.getProject();
        final JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(project);
        final PsiClass generatorClass = javaPsiFacade.findClass(BOG_JAVASSIST_GENERATOR, GlobalSearchScope.allScope(project));
        return generatorClass != null;
    }

    private boolean isProjectJava8() {
        final Project project = dataSource.getProject();
        final Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();

        if (projectSdk == null) {
            return false;
        }

        try {
            final float ver = Float.parseFloat(projectSdk.getName());
            return ver >= 1.8f;
        } catch (Exception e) {
            return false;
        }
    }

    private class ListItemWrapper<T> {
        private T item;

        private String text;

        private Icon icon;

        ListItemWrapper(T item, String text, Icon icon) {
            this.item = item;
            this.text = text;
            this.icon = icon;
        }

        public T getItem() {
            return item;
        }

        public String getText() {
            return text;
        }

        public Icon getIcon() {
            return icon;
        }
    }
}
