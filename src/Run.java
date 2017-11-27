import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Run {
    public static void main(String[] args) throws IOException {

        final int NUM_OF_STATES = 2;
        final int SIZE = 8;
        System.out.println("Testing");

        String homeFolder = System.getProperty("user.home");
        String dataFolder = homeFolder + File.separator + "data";


        for (int i = 0; i < NUM_OF_STATES; i++) {
            System.out.println("i : " + i);

            Path filePath = Paths.get(dataFolder + File.separator + i + ".bin");
            byte[] buffer = Files.readAllBytes(filePath);

            reverseBytes(buffer);

            String bits = convertBytesToBits(buffer);
            /**
             * Split string to equal length substrings in Java
             *
             * \G is a zero-width assertion that matches the position where the previous match ended. If there was no previous match, it matches the beginning of the input,
             */
            String [] states = Arrays.toString(bits.split("(?<=\\G.{64})")));



        }
    }

    public static void reverseBytes(byte[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        byte tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    public static String convertBytesToBits(byte[] bytes) {
        String bits = "";
        for(byte b : bytes) {
            bits += String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
        }
        return bits;
    }


}
