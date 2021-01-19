import java.lang.reflect.Field;
import java.util.ArrayList;

public class PrivateField {

    public static double getAvailableSize(FileStorage fileStorage) {
        double actualAvailableSize = 0;
        try {
            Field fieldAvailableSize = fileStorage.getClass().getDeclaredField("availableSize");
            fieldAvailableSize.setAccessible(true);
            actualAvailableSize = (double) fieldAvailableSize.get(fileStorage);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return actualAvailableSize;
    }

    public static double getMaxSize(FileStorage fileStorage) {
        double actualMaxSize = 0;
        try {
            Field fieldMaxSize = fileStorage.getClass().getDeclaredField("maxSize");
            fieldMaxSize.setAccessible(true);
            actualMaxSize = (double) fieldMaxSize.get(fileStorage);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return actualMaxSize;
    }

    public static String getContent(String fileName, String content) {
        File file = new File(fileName, content);
        String actualContent = null;
        try {
            Field fieldContent = file.getClass().getDeclaredField("content");
            fieldContent.setAccessible(true);
            actualContent = (String) fieldContent.get(file);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return actualContent;
    }

    public static String getExtension(String fileName, String content) {
        File file = new File(fileName, content);
        String actualExtension = null;
        try {
            Field fieldExtension = file.getClass().getDeclaredField("extension");
            fieldExtension.setAccessible(true);
            actualExtension = (String) fieldExtension.get(file);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return actualExtension;
    }

    public static ArrayList<File> getFiles(FileStorage fileStorage) {
        ArrayList<File> expectedFiles = null;
        try {
            Field fieldFiles = fileStorage.getClass().getDeclaredField("files");
            fieldFiles.setAccessible(true);
            expectedFiles = (ArrayList<File>) fieldFiles.get(fileStorage);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return expectedFiles;
    }
}
