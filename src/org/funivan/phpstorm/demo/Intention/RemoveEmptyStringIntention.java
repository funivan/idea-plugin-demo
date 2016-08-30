package org.funivan.phpstorm.demo.Intention;

import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.php.PhpWorkaroundUtil;
import com.jetbrains.php.lang.psi.elements.ConcatenationExpression;
import com.jetbrains.php.lang.psi.elements.StringLiteralExpression;
import org.jetbrains.annotations.NotNull;

public class RemoveEmptyStringIntention extends PsiElementBaseIntentionAction {

    /**
     * Get intent description.
     *
     * @return Description string.
     */
    @NotNull
    public String getText() {
        return "Remove empty concatenated string";
    }

    /**
     * Get intent family name.
     *
     * @return Family name.
     */
    @NotNull
    public String getFamilyName() {
        return getText();
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement psiElement) throws IncorrectOperationException {
        PsiElement parent = psiElement.getParent();
        if (!(parent instanceof StringLiteralExpression)) {
            return;
        }

        PsiElement prev = parent.getPrevSibling();
        if (prev instanceof PsiWhiteSpace) {
            prev = prev.getPrevSibling();
        }

        if (prev != null && prev.getText().equals(".")) {
            prev.delete();
        }
        parent.delete();

        if (parent.getPrevSibling() instanceof PsiWhiteSpace) {
            parent.getPrevSibling().delete();
        }
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement psiElement) {


        if (psiElement == null || !PhpWorkaroundUtil.isIntentionAvailable(psiElement) || psiElement.getParent() == null) {
            return false;
        }

        PsiElement baseElement = psiElement.getParent();
        if (baseElement instanceof StringLiteralExpression) {
            psiElement = baseElement;
        }

        if (!(psiElement instanceof StringLiteralExpression)) {
            return false;
        }


        if (!(psiElement.getParent() instanceof ConcatenationExpression)) {
            return false;
        }


        String text = psiElement.getText();

        return (text.equals("''") || text.equals("\"\""));
    }


}
