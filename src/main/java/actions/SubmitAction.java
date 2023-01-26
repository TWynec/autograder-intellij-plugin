package actions;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import javax.swing.JMenuBar;
import javax.swing.JComponent;
public class SubmitAction extends AnAction{

     @Override
    public void actionPerformed(@NotNull AnActionEvent e){
        Messages.showInfoMessage("Submit works","Info");
    }

}