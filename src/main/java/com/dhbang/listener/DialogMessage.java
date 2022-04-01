package main.java.com.dhbang.listener;

public enum DialogMessage {

    // 사용자 오류
    NOT_SELECTED_FILE(1, "파일을 선택해주세요."),
    FILE_DOES_NOT_EXIST(2, "파일이 존재하지 않습니다."),
    INCORRECT_FORMAT(3, "로그 형식이 올바르지 않습니다."),
    INCORRECT_EXTENSION(4, "확장자가 log 인 파일만 선택 가능합니다."),

    // 프로그램 오류
    FILE_READ_FAIL(100, "파일 처리 중 오류가 발생하였습니다.\n다시 시도해주세요.")
    ;

    private final Integer errorCode;
    private final String message;

    DialogMessage(Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public Integer code() {
        return this.errorCode;
    }

}
