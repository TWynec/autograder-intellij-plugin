package actions;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;




public class SubmitAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        if (e == null) {
            System.err.println("AnActionEvent is null");
            return;
        }

        String filePath = null;
       VirtualFile virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE);
        if (virtualFile != null) {
             filePath = virtualFile.getPath();
            // rest of your code
        } else {
            System.err.println("virtualFile is null");
        }

        int result = Messages.showYesNoDialog("Are you sure you are ready to submit?", "Warning", "Yes", "No", Messages.getQuestionIcon());
        FileUploader conn = new FileUploader();
        if(result == 0) {
           conn.uploadFile(filePath);
        }

    }
}