package main.java.com.dhbang.view;

import main.java.com.dhbang.listener.BtnListener;
import main.java.com.dhbang.listener.ListenerType;

import javax.swing.*;

public class MainForm extends JFrame{

    private JPanel panel1;
    private JButton selectFileBtn;
    private JButton extractFileBtn;
    private JButton analyzeResultBtn;
    private JTextField startDate;
    private JTextField endDate;
    private JLabel selectFileName;

    public MainForm() {
        init();
        setListener();
    }

    void init() {
        setTitle("Log Analyzer");
        setSize(300, 200);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel1);
    }

    void setListener() {
        selectFileBtn.addActionListener(BtnListener.of(ListenerType.SELECT_FILE));
        extractFileBtn.addActionListener(BtnListener.of(ListenerType.EXTRACT_FILE));
        analyzeResultBtn.addActionListener(BtnListener.of(ListenerType.ANALYZE_RESULT));
    }

}
