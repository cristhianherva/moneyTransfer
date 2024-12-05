package dao;

import java.math.BigDecimal;

public class Transfer {
    private int id;
    private BigDecimal amount;
    private String currency;

    // Constructor, getters, and setters
    public Transfer(int id, BigDecimal amount, String currency) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
