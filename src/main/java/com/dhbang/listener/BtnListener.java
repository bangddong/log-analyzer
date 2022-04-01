package main.java.com.dhbang.listener;

import java.awt.event.ActionListener;

public interface BtnListener {

    static ActionListener of(ListenerType listenerType) {
        if (listenerType == ListenerType.SELECT_FILE) {
            return new SelectFileBtnListener();
        } else if (listenerType == ListenerType.EXTRACT_FILE) {
            return new ExtractFileBtnListener();
        } else {
            return new AnalyzeResultBtnListener();
        }
    }

}
