import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Field;

import static org.testng.Assert.*;

public class FileTest {
    @DataProvider
    public Object[][] fileInitData() {
        return RandomData.randomFile(10);
    }

    @Test(dataProvider = "fileInitData")
    public void testGetSize(String fileName, String content) {
        File file = new File(fileName, content);
        double actualSize = file.getSize();
        double expected = (double) content.length() / 2;
        assertEquals(actualSize, expected, "checking the method to return the file size. " +
                "input value: " + content);

    }

    @Test(dataProvider = "fileInitData")
    public void testConstructorFileContent(String fileName, String content) {
        assertEquals(PrivateField.getContent(fileName, content), content,
                "checking the constructor for content initialization. Input value: " + content);

    }

    @Test(dataProvider = "fileInitData")
    public void testConstructorFileExtension(String fileName, String content) {
        String actual =PrivateField.getExtension(fileName, content);
        String expected =fileName.split("\\.")[fileName.split("\\.").length - 1];
        assertEquals(actual, expected,
                "checking the constructor for extension initialization. Input value: " + fileName);
    }

    @Test(dataProvider = "fileInitData")
    public void testConstructorFileExtensionWithoutPoint(String fileName, String content) {
        if (!fileName.contains(".")) {
            String actual = PrivateField.getExtension(fileName, content);
            assertEquals(actual, "",
                    "checking the constructor for extension initialization. Input value: " + fileName);
        }
    }

    @Test(dataProvider = "fileInitData")
    public void testGetFilename(String fileName, String content) {
        File file = new File(fileName, content);
        assertEquals(file.getFilename(), fileName, "checking the method to return the file name. " +
                "Input value: " + fileName);
    }
}