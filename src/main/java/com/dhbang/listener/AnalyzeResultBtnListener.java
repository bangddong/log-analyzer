package main.java.com.dhbang.listener;

import main.java.com.dhbang.utils.Util;
import main.java.com.dhbang.view.MainForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.rmi.server.ExportException;
import java.util.HashMap;

import static main.java.com.dhbang.listener.DialogMessage.*;

/**
 * 분석결과 버튼 리스너
 *
 * 로그 형식
 * [상태코드][URL][웹 브라우저 구분][호출시간]
 * [200][http://apis.dktechin.net/search/knowledge?apikey=23jf&q=dktechin][IE][2019-06-10 08:00:00]
 *
 * "200"외 다른 요청구분도 필요할시 RESPONSE_CHECK = false
 */
public class AnalyzeResultBtnListener implements ActionListener, BtnListener {

    private final MainForm form;

    public AnalyzeResultBtnListener(MainForm form) {
        this.form = form;
    }

    private HashMap<String, Integer> statusCdCntMap;
    private HashMap<String, Integer> serviceIdCntMap;
    private HashMap<String, Integer> apiKeyCntMap;
    private HashMap<String, Integer> browserCntMap;
    private String readLineStr = "";

    private final String API_URL = "http://apis.dktechin.net/search";
    private final Boolean RESPONSE_CHECK = true;

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
            serviceIdCntMap = new HashMap<>();
            apiKeyCntMap = new HashMap<>();
            browserCntMap = new HashMap<>();

            while( (readLineStr = bufferedReader.readLine()) != null) {
                if (!readLineStr.contains(API_URL)) {
                    JOptionPane.showMessageDialog(form, INCORRECT_FORMAT.getMessage());
                    break;
                }
                if (RESPONSE_CHECK && "200".equals(readLineStr.substring(1, readLineStr.indexOf("]")))) continue;
                cntStatusCd();
                cntServiceId();
                cntApiKey();
                cntBrowser();
            }

            // TODO 분석 결과 오더링 구현

            // TODO 사용자 선택 경로 구현
            FileDialog dialog = Util.getFileDialog(form, 1, "");
            dialog.setVisible(true);


            extractFile();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(form, FILE_READ_FAIL.getMessage());
        }
    }

    private void cntStatusCd() {
        String statusCd = readLineStr.substring(1, readLineStr.indexOf("]"));
        statusCdCntMap.put(statusCd, statusCdCntMap.getOrDefault(statusCd, 0) + 1);
        readLineStr = readLineStr.substring(readLineStr.indexOf("]") + 1);
    }

    private void cntServiceId() {
        String serviceId = readLineStr.substring(readLineStr.lastIndexOf("/") + 1, readLineStr.indexOf("?"));
        serviceIdCntMap.put(serviceId, serviceIdCntMap.getOrDefault(serviceId, 0) + 1);
        readLineStr = readLineStr.substring(readLineStr.indexOf("?") + 1);
    }

    private void cntApiKey() {
        String apiKey = readLineStr.substring(0, readLineStr.indexOf("&"));
        apiKeyCntMap.put(apiKey, apiKeyCntMap.getOrDefault(apiKey, 0) + 1);
        readLineStr = readLineStr.substring(readLineStr.indexOf("]") + 1);
    }

    private void cntBrowser() {
        String browser = readLineStr.substring(1, readLineStr.indexOf("]"));
        browserCntMap.put(browser, browserCntMap.getOrDefault(browser, 0) + 1);
        readLineStr = readLineStr.substring(readLineStr.indexOf("]") + 1);
    }

    // TODO 분석 결과를 FileDialog 에서 정한 경로로 저장 구현
    private void extractFile() throws IOException {
        String message = "This is a sample message.\n";

        File file = new File("test1.txt");
        FileWriter writer = null;
        BufferedWriter bWriter = null;

        writer = new FileWriter(file, true);
        bWriter = new BufferedWriter(writer);

        bWriter.write(message);
        bWriter.flush();
    }

}
