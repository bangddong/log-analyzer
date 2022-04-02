package main.java.com.dhbang.listener;

import main.java.com.dhbang.utils.Util;
import main.java.com.dhbang.view.MainForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static main.java.com.dhbang.listener.DialogMessage.INCORRECT_EXTENSION;

/**
 * 파일선택 버튼 리스너
 */
public class SelectFileBtnListener implements ActionListener, BtnListener {

    private final MainForm form;

    public SelectFileBtnListener(MainForm form) {
        this.form = form;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        FileDialog fileDialog = Util.getFileDialog(form, 0, "*.log");
        fileDialog.setVisible(true);

        String selectedFileName = fileDialog.getName();
        String selectedFileDir = fileDialog.getDirectory();

        if (selectedFileName != null) { // 확장자가 log 인 파일을 선택했을 경우
            File selectedFile = new File(selectedFileDir + selectedFileName);
            form.getSelectFileName().setText(selectedFileName);
            form.setChooseFile(selectedFile);
        } else if (selectedFileDir != null) { // 선택은 했으나 확장자가 log 가 아닌 경우
            JOptionPane.showMessageDialog(form, INCORRECT_EXTENSION.getMessage());
        }
    }

}