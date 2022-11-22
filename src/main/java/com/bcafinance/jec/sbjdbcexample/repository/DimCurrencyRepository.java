package com.bcafinance.jec.sbjdbcexample.repository;

import com.bcafinance.jec.sbjdbcexample.model.DimCurrency;

import java.util.List;

public interface DimCurrencyRepository {

    int save(DimCurrency dc);
    int update(DimCurrency dc);
    DimCurrency findById(long id);

    /*
        Delete maksud nya adalah mengupdate kolom flag status dirubah menjadi 0
     */
    int deleteById(long id);
    List<DimCurrency> findAll();

    List<DimCurrency> findByName(String name);
    int deleteAll();
}
