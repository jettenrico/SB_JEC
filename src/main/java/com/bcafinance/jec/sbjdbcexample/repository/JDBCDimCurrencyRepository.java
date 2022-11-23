package com.bcafinance.jec.sbjdbcexample.repository;


import com.bcafinance.jec.sbjdbcexample.model.DimCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCDimCurrencyRepository implements  DimCurrencyRepository{


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(DimCurrency dc) {
        return jdbcTemplate.update("INSERT INTO DimCurrency (CurrencyAlternateKey,CurrencyName) VALUES(?,?)",
                new Object[] {dc.getCurrencyalternatekey(),
                        dc.getCurrencyname()});
    }

    @Override
    public int update(DimCurrency dc) {
//        System.out.println(dc.getCurrencyname());
        return jdbcTemplate.update(
                "UPDATE DimCurrency SET CurrencyAlternateKey=?,CurrencyName=? WHERE CurrencyKey=?",
                new Object[] { dc.getCurrencyalternatekey(),
                        dc.getCurrencyname(),
                        dc.getCurrencykey()});
    }

    @Override
    public DimCurrency findById(long id) {
        try{
            DimCurrency dimCurrency = jdbcTemplate.queryForObject("SELECT * FROM DimCurrency WHERE CurrencyKey=?",
                    BeanPropertyRowMapper.newInstance(DimCurrency.class), id);
            return dimCurrency;
        } catch (
                Exception e) {
            return null;
        }
    }

    @Override
    public int deleteById(long id) {
        try {
            return jdbcTemplate.update("DELETE FROM DimCurrency WHERE CurrencyKey=?", id);
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<DimCurrency> findAll() {
        return jdbcTemplate.query("SELECT * from DimCurrency", BeanPropertyRowMapper.newInstance(DimCurrency.class));
    }

    @Override
    public List<DimCurrency> findByName(String name) {
        return jdbcTemplate.query("SELECT * from DimCurrency WHERE CurrencyName LIKE CONCAT('%',?,'%')",
                BeanPropertyRowMapper.newInstance(DimCurrency.class), name);
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}