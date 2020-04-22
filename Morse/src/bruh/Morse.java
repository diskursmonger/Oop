package bruh;

import java.io.*;
import java.util.*;

/**
 * С консоли считывается команда - code/decode init.txt;
 * Использовать java.lang.StringBuilder для построения слов;
 * Азбука Морзе считывается из файла в начале программы;
 * Для команд code/decode заводится класс-обработчик;
 * Для чтения из файла удобно использовать java.io.InputStreamReader;
 */

public class Morse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command = sc.next();
        Handler handler = new Handler();
        File in = new File(sc.next());
        if (command.equals("code")) {
            try {
                handler.code(in);
            }
            catch (IOException e){
                System.err.println("Error while coding string: " + e);
            }
        } else {
            if (command.equals("decode")) {
                try {
                    handler.decode(in);
                }
                catch (IOException e){
                    System.err.println("Error while decoding string: " + e);
                }
            }
        }
    }
}
