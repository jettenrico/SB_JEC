package com.bcafinance.jec.sbjdbcexample.model;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 11/22/2022
@Last Modified 11/22/2022 2:29 PM
Version 1.0
*/

public class DimCurrency {
    private long currencykey;
    private String currencyalternatekey;
    private String currencyname;

    public DimCurrency(){
    }

    public DimCurrency(long currencykey, String currencyalternatekey, String currencyname) {
        this.currencykey = currencykey;
        this.currencyalternatekey = currencyalternatekey;
        this.currencyname = currencyname;
    }

    public DimCurrency(String currencyalternatekey, String currencyname) {
        this.currencyalternatekey = currencyalternatekey;
        this.currencyname = currencyname;
    }

    public void setCurrencykey(long currencykey) {
        this.currencykey = currencykey;
    }

    public void setCurrencyalternatekey(String currencyalternatekey) {
        this.currencyalternatekey = currencyalternatekey;
    }

    public void setCurrencyname(String currencyname) {
        this.currencyname = currencyname;
    }

    public long getCurrencykey() {
        return currencykey;
    }

    public String getCurrencyalternatekey() {
        return currencyalternatekey;
    }

    public String getCurrencyname() {
        return currencyname;
    }
}