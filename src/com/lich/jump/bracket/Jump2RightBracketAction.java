package com.lich.jump.bracket;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import org.jetbrains.annotations.NotNull;

public class Jump2RightBracketAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor editor = event.getRequiredData(CommonDataKeys.EDITOR);
        Document document = editor.getDocument();
        String text = document.getText();

        Caret primaryCaret = editor.getCaretModel().getPrimaryCaret();

        String[] lines = text.split("\n");

        LogicalPosition logicalPos = primaryCaret.getLogicalPosition();
        //向右移动
        String nowLine = lines[logicalPos.line];
        nowLine = nowLine.replaceAll("\t", "    ");
        // 注意-1的情况
        int i = nowLine.indexOf("(", logicalPos.column);
        if (i > -1) {
            primaryCaret.moveToLogicalPosition(new LogicalPosition(logicalPos.line, i + 1));
        } else {
            String trim = nowLine.replaceAll("^\\s+", "");
            int targetIndex = nowLine.indexOf(trim);
            // 将光标移动到最左边
            primaryCaret.moveToLogicalPosition(new LogicalPosition(logicalPos.line, targetIndex));
        }

        // 向左移动
        /*int i = lines[logicalPos.line].lastIndexOf(")", logicalPos.column - 1);
        if (i > -1) {
            primaryCaret.moveToLogicalPosition(new LogicalPosition(logicalPos.line, i));
        }*/

        lines = null;
    }
}
