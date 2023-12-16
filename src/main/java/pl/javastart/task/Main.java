package pl.javastart.task;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        String productsFile = "src/main/resources/products.csv";
        String currenciesFile = "src/main/resources/currencies.csv";
        List<Product> products = ProductsReader.fillProductList(productsFile);
        List<Currency> currencies = CurrenciesReader.fillCurrenciesList(currenciesFile);
        BigDecimal totalSale = CurrencyConverter.totalSale(products, currencies);
        System.out.println("Suma sprzedaży wszystkich produktów wynosi: " + totalSale + "EUR");
        BigDecimal averagePrice = CurrencyConverter.calcAveragePrice(products, currencies);
        System.out.println("Średnia wartość produktów wynosi: " + averagePrice + "EUR");
        System.out.println("Najdroższy produkt to: " + CurrencyConverter.getMostValuableProductName(products, currencies)
                + ", jego cena wynosi: " + CurrencyConverter.getMostValuableProductPrice(products, currencies) + "EUR");
        System.out.println("Najtańszy produkt to: " + CurrencyConverter.getCheapestProductName(products, currencies)
            + ", jego cena wynosi: " + CurrencyConverter.getCheapestProductPrice(products, currencies) + "EUR");

    }
}
