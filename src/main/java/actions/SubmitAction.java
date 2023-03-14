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
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


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

        } else {
            System.err.println("virtualFile is null");
        }
       int result1 =Messages.showYesNoDialog("Please make sure the correct project is highlighted in project view. Are you ready to continue?","Warning","Yes","No",Messages.getQuestionIcon());
        if(result1 == 1){
            return;
        }
        JTextField nameOfFolder = new JTextField();
       Messages.showTextAreaDialog(nameOfFolder,"Enter name","text");
        String text = nameOfFolder.getText();
        JTextField nameOfPath = new JTextField();
        Messages.showTextAreaDialog(nameOfPath, "Enter path for folder, Ex: C:/example/folder","text");
        String pathText = nameOfPath.getText();

        File folderName = new File(pathText,text);
        if(folderName.mkdir() == true){
            Messages.showInfoMessage("Folder was created!","Info");
        }


        int result = Messages.showYesNoDialog("Are you sure you are ready to submit?", "Warning", "Yes", "No", Messages.getQuestionIcon());
        try {
            Path sourcePath = Paths.get(filePath);
            Path destinationPath = Paths.get(pathText, sourcePath.getFileName().toString());
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        FileUploader conn = new FileUploader();
        if(result == 0) {
         conn.uploadFile(filePath);
        }

    }
}