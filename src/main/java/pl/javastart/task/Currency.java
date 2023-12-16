package pl.javastart.task;

import java.math.BigDecimal;

public class Currency {

    private String symbol;
    private BigDecimal value;

    public Currency(String symbol, BigDecimal value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "symbol='" + symbol + '\'' +
                ", value=" + value +
                '}';
    }
}
