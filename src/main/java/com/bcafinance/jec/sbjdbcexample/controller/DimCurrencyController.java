package com.bcafinance.jec.sbjdbcexample.controller;

import com.bcafinance.jec.sbjdbcexample.model.DimCurrency;
import com.bcafinance.jec.sbjdbcexample.repository.DimCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 11/22/2022
@Last Modified 11/22/2022 2:37 PM
Version 1.0
*/

@RestController
@RequestMapping("/api/v1")
public class DimCurrencyController {
    @Autowired
    DimCurrencyRepository dimCurrencyRepository;

    @GetMapping("/dimcurrencies/{id}")
    public ResponseEntity<DimCurrency> getDimCurrencyById(@PathVariable("id") long id) {
        DimCurrency dimCurrency = dimCurrencyRepository.findById(id);

        if (dimCurrency != null) {
            return new ResponseEntity<>(dimCurrency, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/dimcustomers/{name}")
//    public ResponseEntity<DimCustomer> getDimCustomerById(@PathVariable("id") long id) {


    @PostMapping("/dimcurrencies")
    public ResponseEntity<String> createDimCurrency(@RequestBody DimCurrency dimCurrency) {
        try {
            dimCurrencyRepository.save(new DimCurrency(dimCurrency.getCurrencyalternatekey(),
                    dimCurrency.getCurrencyname()));
            return new ResponseEntity<>("Data berhasil dibuat.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/dimcurrencies/{id}")
    public ResponseEntity<String> updateDimCurrency(@PathVariable("id") long id, @RequestBody DimCurrency dimCurrency) {
        DimCurrency _dimCurrency = dimCurrencyRepository.findById(id);

        if (_dimCurrency != null) {
            _dimCurrency.setCurrencykey(id);
            _dimCurrency.setCurrencyalternatekey(dimCurrency.getCurrencyalternatekey());
            _dimCurrency.setCurrencyname(dimCurrency.getCurrencyname());

            dimCurrencyRepository.update(_dimCurrency);
            return new ResponseEntity<>("Data Berhasil diperbaharui.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Tidak dapat menemukan data dengan id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/dimcurrencies/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id) {
        try {
            int result = dimCurrencyRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Data dengan id " + id +" Tidak ada !!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Data dengan id "+id+" Berhasil di hapus", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Tidak dapat menghapus data .", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/dimcurrencies/6832BLE")
    public ResponseEntity<String> deleteAll() {
        try {
            int numRows = dimCurrencyRepository.deleteAll();
            return new ResponseEntity<>("Berhasil Menghapus  " + numRows + " Data .", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Tidak dapat menghapus data.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dimcurrencies/datas/{name}")
    public ResponseEntity<List<DimCurrency>> findByCurrencyName(@PathVariable("name") String name) {
        try {
            List<DimCurrency> lsDimCurrency = dimCurrencyRepository.findByName(name);

            if (lsDimCurrency.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lsDimCurrency, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
