package app.utils;

import jakarta.servlet.http.Part;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

import static app.utils.Constants.SAVE_DIR;

public class Utils {
    public static String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    public static boolean isValidFile(String fileName) {
        return Arrays.asList("txt", "csv")
                .contains(getExtension(fileName));
    }

    private static String getExtension(String filename) {
        return Optional.ofNullable(filename)
                .map(f -> f.substring(filename.lastIndexOf(".") + 1))
                .orElse(null);
    }

    public static void mkdir() {
        File fileSaveDir = new File(getSavePath());
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
    }

    public static String getSavePath() {
        return System.getProperty("user.home") + File.separator + SAVE_DIR;

    }
}
