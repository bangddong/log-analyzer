package main.java.com.dhbang.listener;

import main.java.com.dhbang.utils.FileExtensionFilter;
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
        FileDialog fileDialog = new FileDialog(form, "파일을 선택해주세요.", FileDialog.LOAD);
        fileDialog.setVisible(true);
        fileDialog.setName(null); // FileDialog 는 파일을 찾지 못할시 컴포넌트의 이름을 반환하기에 default 를 null 로 설정
        fileDialog.setMultipleMode(false);
        fileDialog.setFilenameFilter(FileExtensionFilter.of("log", ""));

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
