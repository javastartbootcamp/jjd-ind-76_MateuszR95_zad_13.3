package pl.javastart.task;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurrenciesReader {

    public static List<Currency> fillCurrenciesList(String fileName) throws IOException {
        int fileLength = calculateFileLength(fileName);
        List<Currency> currencies = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            for (int i = 0; i < fileLength; i++) {
                String currencyLine = bufferedReader.readLine();
                currencies.add(i, createCurrencyFromLine(currencyLine));
            }
        }
        return currencies;
    }

    private static Currency createCurrencyFromLine(String currencyLine) {
        String[] currencyFields = currencyLine.split(";");
        String symbol = currencyFields[0];
        BigDecimal value = new BigDecimal(currencyFields[1]);

        return new Currency(symbol, value);
    }

    private static int calculateFileLength(String fileName) throws FileNotFoundException {
        int lines = 0;
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                lines++;
            }
        }
        return lines;
    }

}
