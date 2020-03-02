package bruh;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
/**
 * С консоли считывается команда - code/decode init.txt;
 * Использовать java.lang.StringBuilder для построения слов;
 * Азбука Морзе считывается из файла в начале программы;
 * Для команд code/decode заводится класс-обработчик;
 * Для чтения из файла удобно использовать java.io.InputStreamReader;
 */

public class Morse {
    public static class Handler {
        public void code(File file) throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder stat = new StringBuilder();
            Map<String,String> ABC = new HashMap<>();
            Map<String,Integer> Statistics = new HashMap<>(); // пример: A, 2 раза
            Scanner scannerABC = new Scanner(new File("Texts/ABC.txt"));
            String symbol;
            while(scannerABC.hasNext()){
                symbol = scannerABC.next();
                ABC.put(symbol,scannerABC.nextLine());
                if (!Statistics.containsKey(symbol)){
                    Statistics.put(symbol,0);
                    stat.append(symbol);
                }
            }
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                symbol = scanner.next();
                stringBuilder.append(ABC.get(symbol));
                Statistics.put(symbol,Statistics.get(symbol) + 1);
            }
            FileWriter writer = new FileWriter("Texts/Out.txt");
            FileWriter statistics = new FileWriter("Texts/Statistics.txt");
            int indexErr = stringBuilder.toString().indexOf("null");
            if (indexErr == -1){
                char []chars = stat.toString().toCharArray();
                int i = 0;
                String str = "";
                while (i != chars.length){
                    str = "" + chars[i];
                    if(chars[i] != ' ' && Statistics.get(str) != 0){
                        statistics.write(chars[i] + " occurs " + Statistics.get(str) + " times\n");
                    }
                    i++;
                }
                writer.write(stringBuilder.toString());
                System.out.println(stringBuilder);
            } else {
                System.err.println("There's wrong symbol");
            }
            statistics.close();
            writer.close();
        }
        public void decode(File file) throws IOException {
            StringBuilder stringBuilderDec = new StringBuilder();
            Map<String,String> ABCdec = new HashMap<>();
            Map<Character,Integer> StatisticsDec = new HashMap<>();
            Scanner scannerABCdec = new Scanner(new File("Texts/ABCreverse.txt"));
            while(scannerABCdec.hasNext()){
                ABCdec.put(scannerABCdec.next(),scannerABCdec.nextLine());
            }
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                stringBuilderDec.append(ABCdec.get(scanner.next()));
            }
            FileWriter writerDec = new FileWriter("Texts/Out.txt");
            int indexErr = stringBuilderDec.toString().indexOf("null");
            FileWriter statisticsDec = new FileWriter("Texts/Statistics.txt");
            if (indexErr == -1){
                char []chars = stringBuilderDec.toString().toCharArray();
                int i = 0;
                while(i != chars.length){
                    StatisticsDec.put(chars[i],0);
                    i++;
                }
                i = 0;
                while(i != chars.length){
                    StatisticsDec.put(chars[i],StatisticsDec.get(chars[i]) + 1);
                    i++;
                }
                i = 0;
                Integer Value = 0;
                while(i != chars.length){
                    Value = StatisticsDec.get(chars[i]);
                    if (Value != 0 && chars[i] != ' ') statisticsDec.write(chars[i] + " occurs " + Value.toString() + " times\n");
                    StatisticsDec.put(chars[i], 0);
                    i++;
                }
                writerDec.write(stringBuilderDec.toString());
                System.out.println(stringBuilderDec);
            } else {
                System.err.println("There's wrong symbol");
            }
            writerDec.close();
            statisticsDec.close();
        }
    }
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
