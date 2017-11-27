import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Bytes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Run {
    public static void main(String[] args) throws IOException {

        final int NUM_OF_STATES = 2;
        final int SIZE = 8;
        final String[] numberTable = {
                "0000000000000001","0000000000000010","0000000000000100","0000000000001000",
                "0000000000010000","0000000000100000","0000000001000000","0000000010000000",
                "0000000100000000","0000001000000000","0000010000000000","0000100000000000",
                "0001000000000000","0010000000000000","0100000000000000","1000000000000000",
        };

        String homeFolder = System.getProperty("user.home");
        String dataFolder = homeFolder + File.separator + "data";


        for (int i = 0; i < NUM_OF_STATES; i++) {
            System.out.println("i : " + i);

            Path filePath = Paths.get(dataFolder + File.separator + i + ".bin");
            byte[] buffer = Files.readAllBytes(filePath);

            reverseBytes(buffer);

            String bits = convertBytesToBits(buffer);

            String[] states = toFixedString(bits,64);

            for(String s : states) {
                String[] number = toFixedString(s,4);
                for(String n : number) {
                    int digit = btod(n);
                    System.out.print(digit + "\t");
                    System.out.println(numberTable[digit]);
                }
                System.out.println();
            }



        }
    }

    /**
     * convert binary string to integer
     * @param binary string representation
     * @return an integer
     */
    public static int btod(String binary) {
        return Integer.parseInt(binary,2);
    }

    /**
     * Split string to equal length substrings
     * @param string the source string
     * @param size fixed length
     * @return an array of fixed length substring
     */
    public static String[] toFixedString(String string, int size) {
        String reg = "(?<=\\G.{" + size + "})";
        return string.split(reg);
    }

    public static byte[] reverseBytes(byte[] array) {
        return Bytes.toArray(Lists.reverse(Bytes.asList(array)));
    }

    public static String convertBytesToBits(byte[] bytes) {
        String bits = "";
        for(byte b : bytes) {
            bits += String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
        }
        return bits;
    }


}
