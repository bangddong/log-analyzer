package main.java.com.dhbang.listener;

import main.java.com.dhbang.view.MainForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 파일추출 버튼 리스너
 */
public class ExtractFileBtnListener implements ActionListener, BtnListener {

    private final MainForm form;

    public ExtractFileBtnListener(MainForm form) {
        this.form = form;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (form.getChooseFile() == null) {
            JOptionPane.showMessageDialog(form, "파일을 선택해주세요.");
        }
    }

}
