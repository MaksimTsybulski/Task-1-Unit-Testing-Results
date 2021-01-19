import exception.FileNameAlreadyExistsException;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class FileStorageTest {
    private FileStorage fileStorage = new FileStorage();

    @BeforeTest
    public void setUp() {
        Object[][] data = RandomData.randomFile(20);
        for (int i = 0; i <data.length ; i++) {
            for (int j = 0; j <data[i].length ; j++) {
                fileStorage.getFiles().add(new File((String) data[i][j], (String) data[i][j]));
            }
        }
    }

    @DataProvider
    public Object[] sizeData() {
        return RandomData.randomSize(10);
    }

    @DataProvider
    public Object[] fileNameData() {
        return RandomData.randomFileName(10);
    }

    @DataProvider
    public Object[] filesData() {
        return  RandomData.randomFile(10);
    }

    @Test(dataProvider = "sizeData",dependsOnMethods = "testConstructorFileAvailableSizeNotNegative")
    public void testConstructorFileAvailableSize(int size) {
        FileStorage fileStorage = new FileStorage(size);
        assertEquals(PrivateField.getAvailableSize(fileStorage), size, "checking initialization of available size in file storage." +
                "Input value: " + size);
    }

    @Test(dataProvider = "sizeData")
    public void testConstructorFileAvailableSizeNotNegative(int size){
        FileStorage fileStorage = new FileStorage(size);
        double actualAvailableSize = PrivateField.getAvailableSize(fileStorage);
        if (actualAvailableSize<0){
            fail("Available size is negative");
        }
    }

    @Test(dataProvider = "sizeData")
    public void testConstructorFileMaxSizeNotNegative(int size){
        FileStorage fileStorage = new FileStorage(size);
        double actualAvailableSize = PrivateField.getMaxSize(fileStorage);
        if (actualAvailableSize<0){
            fail("Available size is negative");
        }
    }


    @Test(dataProvider = "sizeData",dependsOnMethods = "testConstructorFileMaxSizeNotNegative")
    public void testConstructorFileMaxSize(int size) {
        FileStorage fileStorage = new FileStorage(size);
        assertEquals(PrivateField.getMaxSize(fileStorage), size,
                "checking the initialization of the maximum size in file storage. Input value: " + size);
    }


    @Test(dataProvider = "filesData")
    public void testWrite(File file) {
        for (File currentFile : fileStorage.getFiles()) {
            if (currentFile.getFilename().equals(file.getFilename())) {
                try {
                    fileStorage.write(file);
                } catch (FileNameAlreadyExistsException e) {
                    e.printStackTrace();
                }
            } else {

            }
        }
    }
    @Test(dataProvider = "filesData")
    public void testWriteAvailableSize(File file) throws FileNameAlreadyExistsException {
        double availableSizeBeforeWrite =  PrivateField.getAvailableSize(fileStorage);
        if (fileStorage.write(file)){
            double availableSizeAfterWrite = PrivateField.getAvailableSize(fileStorage);
            assertEquals(availableSizeAfterWrite-availableSizeBeforeWrite,1,
                    "check if the available file size has changed Storage On write. Input data");
        }

    }
    @Test(dataProvider = "filesData",expectedExceptions = FileNameAlreadyExistsException.class)
    public void testWriteExistFile(File file) throws FileNameAlreadyExistsException {
        if (fileStorage.write(file)){
            throw new FileNameAlreadyExistsException();
        }

    }

    @Test(dataProvider = "fileNameData", dependsOnMethods = "testIsExists_NOT_NULL")
    public void testIsExists(String fileName) {
        boolean actual = fileStorage.isExists(fileName);
        boolean expected = false;
        for (File file : fileStorage.getFiles()) {
            if (file.getFilename().equals(fileName)) {
                expected = true;
                break;
            }
        }
        assertEquals(actual, expected, "Checking for file existence in storage. Input value: " + fileName);
    }

    @Test(dataProvider = "fileNameData")
    public void testIsExists_NOT_NULL(String fileName) {
        assertNotNull(fileStorage.getFile(fileName), "checking the method for null. " + "Input value: " + fileName);
    }

    @Test(dataProvider = "fileNameData")
    public void testIsExistsNullPointNullPointerException(String fileName) {
        try {
            fileStorage.isExists(fileName);
        } catch (NullPointerException e) {
            fail("method throws NullPointNullPointerException Input value: " + fileName);
        }
    }

    @Test(dataProvider = "fileNameData", dependsOnMethods = "testDelete_NOT_NULL")
    public void testDelete(String fileName) {
        FileStorageTest fileStorageTest = new FileStorageTest();
        fileStorageTest.testIsExists(fileName);
        if (fileStorage.delete(fileName)) {

        }
    }

    @Test(dataProvider = "fileNameData", dependsOnMethods = "testDeleteNullPointNullPointerException")
    public void testDelete_NOT_NULL(String fileName) {
        assertNotNull(fileStorage.getFile(fileName), "checking the method for null. " + "Input value: " + fileName);

    }

    @Test(dataProvider = "fileNameData")
    public void testDeleteNullPointNullPointerException(String fileName) {
        try {
            fileStorage.isExists(fileName);
        } catch (NullPointerException e) {
            fail("method throws NullPointNullPointerException Input value: " + fileName);
        }
    }

    @Test()
    public void testGetFiles(){
        assertEquals(fileStorage.getFiles(), PrivateField.getFiles(fileStorage), "checking the method to return a list of files." +
                "Input value: ");
    }

    @Test(dataProvider = "fileNameData")
    public void testGetFile(String fileName) {
        String actualName = fileStorage.getFile(fileName).getFilename();
        System.out.println(actualName);
        assertEquals(actualName, fileName, "checking the method to return a file by file name. " + "Input value: " + fileName);
    }

    @Test(dataProvider = "fileNameData", dependsOnMethods = "testGetFileNullPointNullPointerException")
    public void testGetFile_NOT_NULL(String fileName) {
        assertNotNull(fileStorage.getFile(fileName), "checking the method for null. " + "Input value: " + fileName);
    }

    @Test(dataProvider = "fileNameData")
    public void testGetFileNullPointNullPointerException(String fileName) {
        try {
            fileStorage.isExists(fileName);
        } catch (NullPointerException e) {
            fail("method throws NullPointNullPointerException Input value: " + fileName);
        }
    }

}