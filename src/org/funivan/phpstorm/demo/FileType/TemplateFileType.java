package org.funivan.phpstorm.demo.FileType;

import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.php.lang.PhpLanguage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by ivan
 */
public class TemplateFileType extends LanguageFileType {
    public static final TemplateFileType INSTANCE = new TemplateFileType();

    TemplateFileType() {
        super(PhpLanguage.INSTANCE);
    }

    @NotNull
    public String getName() {
        return "Custom template file";
    }

    @NotNull
    public String getDescription() {
        return "Custom Template file";
    }

    @NotNull
    public String getDefaultExtension() {
        return "html";
    }

    public Icon getIcon() {
        return FileTypeManager.getInstance().getStdFileType("HTML").getIcon();
    }

    public String getCharset(@NotNull VirtualFile file, @NotNull byte[] content) {
        return null;
    }

}
