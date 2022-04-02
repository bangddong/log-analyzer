package main.java.com.dhbang.utils;

import java.awt.*;

public class Util {

    public static FileDialog getFileDialog(Frame frame, int dialogMod, String extension) {
        FileDialog fileDialog = new FileDialog(frame, "파일을 선택해주세요.", dialogMod);
        fileDialog.setName(null); // 기본값으로 파일 미선택시 컴포넌트의 이름을 가져오기에 NULL 로 설정.
        fileDialog.setMultipleMode(false);
        fileDialog.setFilenameFilter(FileExtensionFilter.of(extension, ""));

        return fileDialog;
    }

}
