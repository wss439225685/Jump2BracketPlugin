package com.lich.jump.bracket;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import org.jetbrains.annotations.NotNull;

public class Jump2LeftBracketAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor editor = event.getRequiredData(CommonDataKeys.EDITOR);
        Document document = editor.getDocument();
        String text = document.getText();

        Caret primaryCaret = editor.getCaretModel().getPrimaryCaret();

        String[] lines = text.split("\n");

        LogicalPosition logicalPos = primaryCaret.getLogicalPosition();

        String nowLine = lines[logicalPos.line];
        nowLine = nowLine.replaceAll("\t", "    ");
        // 向左移动
        int i = nowLine.lastIndexOf(")", logicalPos.column - 1);
        if (i > -1) {
            primaryCaret.moveToLogicalPosition(new LogicalPosition(logicalPos.line, i));
        } else {
            // 将光标移动到最右边
            primaryCaret.moveToLogicalPosition(new LogicalPosition(logicalPos.line, nowLine.length()));
        }

        lines = null;
    }
}
