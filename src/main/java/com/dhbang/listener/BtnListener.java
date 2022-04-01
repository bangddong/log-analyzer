package main.java.com.dhbang.listener;

import main.java.com.dhbang.view.MainForm;

import java.awt.event.ActionListener;

/**
 * 타입에 맞는 버튼 리스너를 반환한다.
 */
public interface BtnListener {

    static ActionListener getBtnListener(ListenerType listenerType, MainForm form) {
        if (listenerType == ListenerType.SELECT_FILE) {
            return new SelectFileBtnListener(form);
        } else if (listenerType == ListenerType.EXTRACT_FILE) {
            return new ExtractFileBtnListener(form);
        } else {
            return new AnalyzeResultBtnListener(form);
        }
    }

}
