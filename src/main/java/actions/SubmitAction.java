package actions;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;


public class SubmitAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

       VirtualFile virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE);
       String filePath = virtualFile.getPath();

       Messages.showInfoMessage(filePath,"Info");
       FileUploader conn = new FileUploader();

       conn.uploadFile(filePath);
    }
}