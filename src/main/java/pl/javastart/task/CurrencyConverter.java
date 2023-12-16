package pl.javastart.task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class CurrencyConverter {

    public static BigDecimal getCheapestProductPrice(List<Product> products, List<Currency> currencies) {
        BigDecimal minPrice = null;
        List<BigDecimal> productsPricesInEur = fillListWithPriceInEur(products, currencies);
        int indexWithMinPriceInEuro = findIndexWithMinPriceInEuro(products, currencies);
        for (int i = 0; i < productsPricesInEur.size(); i++) {
            minPrice = productsPricesInEur.get(indexWithMinPriceInEuro);
            minPrice = minPrice.setScale(2, RoundingMode.HALF_UP);
        }
        return minPrice;
    }

    public static BigDecimal getMostValuableProductPrice(List<Product> products, List<Currency> currencies) {
        BigDecimal maxPrice = null;
        List<BigDecimal> productsPricesInEur = fillListWithPriceInEur(products, currencies);
        int indexWithMaxPriceInEuro = findIndexWithMaxPriceInEuro(products, currencies);
        for (int i = 0; i < productsPricesInEur.size(); i++) {
            maxPrice = productsPricesInEur.get(indexWithMaxPriceInEuro);
            maxPrice = maxPrice.setScale(2, RoundingMode.HALF_UP);
        }
        return maxPrice;
    }

    public static String getCheapestProductName(List<Product> products, List<Currency> currencies) {
        String productName = null;
        int indexWithMinPriceInEuro = findIndexWithMinPriceInEuro(products, currencies);
        for (int i = 0; i < products.size(); i++) {
            productName = products.get(indexWithMinPriceInEuro).getName();
        }
        return productName;
    }

    public static String getMostValuableProductName(List<Product> products, List<Currency> currencies) {
        String productName = null;
        int indexWithMaxPriceInEuro = findIndexWithMaxPriceInEuro(products, currencies);
        for (int i = 0; i < products.size(); i++) {
            productName = products.get(indexWithMaxPriceInEuro).getName();
        }
        return productName;
    }

    private static int findIndexWithMinPriceInEuro(List<Product> products, List<Currency> currencies) {
        int index = 0;
        List<BigDecimal> pricesInEur = fillListWithPriceInEur(products, currencies);
        BigDecimal minPrice = pricesInEur.get(0);
        for (int i = 0; i < pricesInEur.size(); i++) {
            if (pricesInEur.get(i).compareTo(minPrice) < 0) {
                minPrice = pricesInEur.get(i);
                index = i;
            }
        }
        return index;
    }

    private static int findIndexWithMaxPriceInEuro(List<Product> products, List<Currency> currencies) {
        int index = 0;
        List<BigDecimal> pricesInEur = fillListWithPriceInEur(products, currencies);
        BigDecimal maxPrice = pricesInEur.get(0);
        for (int i = 0; i < pricesInEur.size(); i++) {
            if (pricesInEur.get(i).compareTo(maxPrice) > 0) {
                maxPrice = pricesInEur.get(i);
                index = i;
            }
        }
        return index;
    }

    public static BigDecimal calcAveragePrice(List<Product> products, List<Currency> currencies) {
        BigDecimal averagePrice;
        List<BigDecimal> pricesInEur = fillListWithPriceInEur(products, currencies);
        BigDecimal totalSale = totalSale(products, currencies);
        averagePrice = totalSale.divide(BigDecimal.valueOf(pricesInEur.size()), 2, RoundingMode.HALF_UP);

        return averagePrice;
    }

    public static BigDecimal totalSale(List<Product> products, List<Currency> currencies) {
        BigDecimal totalSale = BigDecimal.ZERO;
        List<BigDecimal> pricesInEur = fillListWithPriceInEur(products, currencies);
        for (int i = 0; i < pricesInEur.size(); i++) {
            totalSale = totalSale.add(pricesInEur.get(i));

        }
        return totalSale.setScale(2, RoundingMode.HALF_UP);
    }

    private static List<BigDecimal> fillListWithPriceInEur(List<Product> products, List<Currency> currencies) {
        List<BigDecimal> pricesInEur = new ArrayList<>(products.size());
        for (int i = 0; i < products.size(); i++) {
            String productCurrency = products.get(i).getCurrency();
            BigDecimal exchangeRate = getAmountForCurrency(currencies, productCurrency);
            BigDecimal priceInSpecificCurrency = products.get(i).getPrice();
            BigDecimal productPriceInEur = priceInSpecificCurrency.divide(exchangeRate, 16, RoundingMode.HALF_UP);
            pricesInEur.add(productPriceInEur);
        }
        return pricesInEur;
    }

    private static BigDecimal getAmountForCurrency(List<Currency> currencies, String symbol) {
        BigDecimal amount = BigDecimal.ZERO;
        for (int i = 0; i < currencies.size(); i++) {
            if (currencies.get(i).getSymbol().equalsIgnoreCase(symbol)) {
                amount = currencies.get(i).getValue();
            }
        }
        return amount;
    }

}

