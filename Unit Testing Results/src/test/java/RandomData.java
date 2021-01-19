import java.util.Random;

public class RandomData {

    public static Object[][] randomFile(int amount) {
        Object[][] result = new Object[amount][2];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                if (j % 2 == 0) {
                    result[i][j] = randomString((int) (1 + Math.random() * 10));
                } else {
                    result[i][j] = randomString((int) (Math.random() * 20));
                }
            }
        }
        return result;
    }

    public static String randomString(int length) {
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = (char) (32 + Math.random() * 95);
        }
        return new String(chars);
    }

    public static Object[] randomSize(int amount) {
        Object[] result = new Object[amount];
        for (int i = 0; i < amount; i++) {
            result[i] = (int) (-500 + Math.random() * 1000);
        }
        return result;
    }

    public static Object[] randomFileName(int amount) {
        Object[] result = new Object[amount];
        for (int i = 0; i < amount; i++) {
            result[i] = randomString((int) (Math.random() * 10));
        }
        result[amount - 1] = null;
        return result;
    }

    public static void main(String[] args) {
        File file = new File(null,null);

    }
}
