package com.jlh.mybatis.view;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import db.mframe.MainFrame;
import db.mysql.env.RuntimeEnv;

import java.io.IOException;

/**
 * com.jlh.plugin
 *
 * @author ASUS
 * @date 2017/11/17 11:07
 */
public class MVPSupportAction extends AnAction {
    private static MainFrame mainFrame;
    static {
        try {
            RuntimeEnv.reader();
            RuntimeEnv.storage();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        VirtualFile projectFileDirectory=anActionEvent.getData(PlatformDataKeys.PROJECT_FILE_DIRECTORY);
        if (mainFrame==null) {
            mainFrame = new MainFrame();
            mainFrame.selectFilePath = project.getBasePath();
            mainFrame.generateCallBack=()-> projectFileDirectory.refresh(false,true);
        }else {
            mainFrame.setVisible(true);
        }

    }
    /**
     * 显示提示对话框
     *
     * @param file
     * @param prefix
     * @param project
     */
//    private void showHintDialog(PsiFile file, String prefix, Project project) {
//        HintDialog dialog = new HintDialog(file, prefix, project);
//        dialog.setSize(600, 400);
//        dialog.setLocationRelativeTo(null);
//        dialog.setVisible(true);
//        dialog.requestFocus();
//    }

}
