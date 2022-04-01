package main.java.com.dhbang.listener;

import main.java.com.dhbang.view.MainForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static main.java.com.dhbang.listener.DialogMessage.*;

/**
 * 분석결과 버튼 리스너
 *
 * 로그 형식
 * [상태코드][URL][웹 브라우저 구분][호출시간]
 * [200][http://apis.dktechin.net/search/knowledge?apikey=23jf&q=dktechin][IE][2019-06-10 08:00:00]
 *
 * "200"외 다른 요청구분도 필요할시 ONLY_SUCCESS = false
 */
public class AnalyzeResultBtnListener implements ActionListener, BtnListener {

    private final MainForm form;

    public AnalyzeResultBtnListener(MainForm form) {
        this.form = form;
    }

    private HashMap<String, Integer> statusCdCntMap;
    private HashMap<String, Integer> serviceCntMap;
    private HashMap<String, Integer> apiCntMap;
    private HashMap<String, Integer> browserCntMap;
    private String readFileLine = "";

    private final String API_URL = "http://apis.dktechin.net/search";
    private final Boolean ONLY_SUCCESS = true;

    @Override
    public void actionPerformed(ActionEvent event) {
        File chooseFile = form.getChooseFile();
        if (chooseFile == null) {
            JOptionPane.showMessageDialog(form, NOT_SELECTED_FILE.getMessage());
            return;
        } else if (!chooseFile.exists()) {
            JOptionPane.showMessageDialog(form, FILE_DOES_NOT_EXIST.getMessage());
            return;
        }

        try {
            FileReader fileReader = new FileReader(chooseFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            statusCdCntMap = new HashMap<>();
            serviceCntMap = new HashMap<>();
            apiCntMap = new HashMap<>();
            browserCntMap = new HashMap<>();

            while( (readFileLine = bufferedReader.readLine()) != null) {
                if (!readFileLine.contains(API_URL)) {
                    JOptionPane.showMessageDialog(form, INCORRECT_FORMAT.getMessage());
                    break;
                }
                if (ONLY_SUCCESS && "200".equals(readFileLine.substring(1, readFileLine.indexOf("]")))) continue;
                cntStatusCd();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(form, FILE_READ_FAIL.getMessage());
        }
    }

    private void cntStatusCd() {
        String statusCd = readFileLine.substring(1, readFileLine.indexOf("]"));
        statusCdCntMap.put(statusCd, statusCdCntMap.getOrDefault(statusCd, 0) + 1);
        readFileLine = readFileLine.substring(readFileLine.indexOf("]") + 1);
    }

    // TODO 호출 서비스 아이디를 카운트 한다.
    private void cntServiceId() {

    }

    // TODO 호출 API KEY 를 카운트 한다.
    private void cntApiKey() {

    }

    // TODO 호출 웹 브라우저를 카운트 한다.
    private void cntBrowser() {

    }

}
