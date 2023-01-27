package actions;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import javax.swing.JMenuBar;
import javax.swing.JComponent;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.Path;

public class SubmitAction extends AnAction {
     @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
         Path currentRelativePath = Paths.get("FileUploader.java");
         String s = currentRelativePath.toAbsolutePath().toString();

         Messages.showInfoMessage(s,"Info");
         FileUploader conn = new FileUploader();

         conn.uploadFile(s);
    }
}