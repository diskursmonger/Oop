package bruh;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Handler {
    public void code(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> ABC = new HashMap<>();
        HashSet<Statistics> statistics = new HashSet<>();
        Scanner scannerABC = new Scanner(new File("Texts/ABC.txt"));
        FileWriter FileStatistics = new FileWriter("Texts/Statistics.txt");
        String symbol;
        while (scannerABC.hasNext()) {
            symbol = scannerABC.next();
            Statistics st = new Statistics();
            st.symb = symbol;
            statistics.add(st);
            ABC.put(symbol, scannerABC.nextLine());
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            symbol = scanner.next();
            Iterator<Statistics> iter = statistics.iterator();
            while (iter.hasNext()) {
                Statistics st = iter.next();
                if (st.symb.contains(symbol)) {
                    st.num = st.num + 1;
                }
            }
            stringBuilder.append(ABC.get(symbol));
        }
        FileWriter writer = new FileWriter("Texts/Out.txt");
        int indexErr = stringBuilder.toString().indexOf("null");
        if (indexErr == -1) {
            writer.write(stringBuilder.toString());
            System.out.println(stringBuilder);
        } else {
            System.err.println("There's wrong symbol");
        }
        Iterator<Statistics> i = statistics.iterator();
        while (i.hasNext()) {
            Statistics st = i.next();
            if (st.num != 0) FileStatistics.write(st.makeStr(st.symb, st.num) + "\n");
        }
        FileStatistics.close();
        writer.close();
    }

    public void decode(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> ABC = new HashMap<>();
        HashSet<Statistics> statistics = new HashSet<>();
        Scanner scannerABC = new Scanner(new File("Texts/ABC.txt"));
        String symbol, symbolCode;
        while (scannerABC.hasNext()) {
            symbol = scannerABC.next();
            symbolCode = scannerABC.next();
            ABC.put(symbolCode,symbol);
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            stringBuilder.append(ABC.get(scanner.next()));
        }
        FileWriter writer = new FileWriter("Texts/Out.txt");
        int indexErr = stringBuilder.toString().indexOf("null");
        FileWriter FileStatistics = new FileWriter("Texts/Statistics.txt");
        if (indexErr == -1) {
            char[] chars = stringBuilder.toString().toCharArray();
            int i = 0;
            while (i != chars.length) {
                Statistics stat = new Statistics();
                stat.symb = chars[i] + "";
                Iterator<Statistics> it = statistics.iterator();
                int check = 0;
                while (it.hasNext()) {
                    Statistics currSt = it.next();
                    if (currSt.equalSymbol(stat.symb)) {
                        check++;
                    }
                }
                if (check == 0) {
                    statistics.add(stat);
                }
                i++;
            }
            i = 0;
            while (i != chars.length) {
                Iterator<Statistics> iter = statistics.iterator();
                while (iter.hasNext()) {
                    Statistics st = iter.next();
                    if (st.symb.contains("" + chars[i])) {
                        st.num = st.num + 1;
                    }
                }
                i++;
            }
            Iterator<Statistics> it = statistics.iterator();
            while (it.hasNext()) {
                Statistics st = it.next();
                if (st.num != 0) FileStatistics.write(st.makeStr(st.symb, st.num) + "\n");
            }
            writer.write(stringBuilder.toString());
            System.out.println(stringBuilder);
        } else {
            System.err.println("There's wrong symbol");
        }
        writer.close();
        FileStatistics.close();
    }
}