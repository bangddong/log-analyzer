package main.java.com.dhbang.utils;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * FileFilter 를 FileDialog 에서 사용하기 위해 재구현
 * 생성자로 넘어온 extensions 외 선택하지 않는다.
 */
public class FileExtensionFilter extends FileFilter implements FilenameFilter {

    private final String[] extensions;
    private final String desc;

    private FileExtensionFilter(String extensions, String desc) {
        this.extensions = parseExtensions(extensions);
        this.desc = desc;
    }

    @Override
    public boolean accept(File f) {
        return f.isDirectory() || accept(null, f.getName());
    }

    @Override
    public boolean accept(File f, String s) {
        String extension = getExtension(s);
        if (!"".equals(extension)) {
            for (String extension1 : extensions) {
                if (extension1.equals("*") || extension1.equalsIgnoreCase(extension)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String[] parseExtensions(String extensions) {
        StringTokenizer st = new StringTokenizer(extensions, ",");
        String[] ext = new String[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            ext[i++] = st.nextToken();
        }
        return ext;
    }

    public static String getExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        if (i == -1) return "";
        return fileName.substring(i + 1).toLowerCase(Locale.KOREAN);
    }

    public String getDescription() {
            return desc;
        }

    public static FileExtensionFilter of(String extensions, String desc) {
        return new FileExtensionFilter(extensions, desc);
    }

}