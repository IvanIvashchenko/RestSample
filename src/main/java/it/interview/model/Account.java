package it.interview.model;

public class Account {

    private Long id;
    private String customer;
    private String currency;
    private Double moneyAmount;

    public static Builder getBuilder() {
        return new Account().new Builder();
    }

    public class Builder {

        public Builder id(final Long id) {
            Account.this.id = id;
            return this;
        }

        public Builder customer(final String customer) {
            Account.this.customer = customer;
            return this;
        }

        public Builder currency(final String currency) {
            Account.this.currency = currency;
            return this;
        }

        public Builder moneyAmount(final Double moneyAmount) {
            Account.this.moneyAmount = moneyAmount;
            return this;
        }

        public Account build() {
            return Account.this;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", currency='" + currency + '\'' +
                ", moneyAmount=" + moneyAmount +
                '}';
    }
}
