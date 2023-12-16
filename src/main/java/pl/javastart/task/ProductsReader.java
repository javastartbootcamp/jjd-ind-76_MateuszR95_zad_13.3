package pl.javastart.task;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductsReader {

    public static List<Product> fillProductList(String fileName) throws IOException {
        int fileLength = calculateFileLength(fileName);
        List<Product> products = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            for (int i = 0; i < fileLength; i++) {
                String productLine = bufferedReader.readLine();
                products.add(i, createProductFromLine(productLine));
            }
        }
        return products;
    }

    private static Product createProductFromLine(String productLine) {
        String[] productFields = productLine.split(";");
        String name = productFields[0];
        BigDecimal price = new BigDecimal(productFields[1]);
        String currency = productFields[2];

        return new Product(name, price, currency);
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