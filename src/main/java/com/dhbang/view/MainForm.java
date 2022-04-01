package main.java.com.dhbang.view;

import main.java.com.dhbang.listener.BtnListener;

import javax.swing.*;
import java.io.File;
import java.time.LocalDate;

import static main.java.com.dhbang.listener.ListenerType.*;

public class MainForm extends JFrame{

    private JPanel panel;
    private JButton selectFileBtn;
    private JButton extractFileBtn;
    private JButton analyzeResultBtn;
    private JTextField startDate;
    private JTextField endDate;
    private JLabel selectFileName;

    private File selectedFile;

    public MainForm() {
        createAndShowGUI();
        setListener();
    }

    void createAndShowGUI() {
        setTitle("Log Analyzer");
        setSize(300, 200);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(panel);
        startDate.setText(String.valueOf(LocalDate.now()));
        endDate.setText(String.valueOf(LocalDate.now()));
    }

    void setListener() {
        selectFileBtn.addActionListener(BtnListener.getBtnListener(SELECT_FILE, this));
        extractFileBtn.addActionListener(BtnListener.getBtnListener(EXTRACT_FILE, this));
        analyzeResultBtn.addActionListener(BtnListener.getBtnListener(ANALYZE_RESULT, this));
    }

    public JLabel getSelectFileName() {
        return selectFileName;
    }

    public File getChooseFile() {
        return selectedFile;
    }

    public void setChooseFile(File chooseFile) {
        this.selectedFile = chooseFile;
    }

}
