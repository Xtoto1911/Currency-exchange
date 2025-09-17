package ru.smirnovkirill.currencyExchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smirnovkirill.currencyExchange.dao.CurrencyDAO;
import ru.smirnovkirill.currencyExchange.model.Currency;


import java.util.List;


@RestController
@RequestMapping("/v1/currencies")
public class CurrencyController {

    private final CurrencyDAO currencyDAO;

    @Autowired
    public CurrencyController(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    @GetMapping()
    public ResponseEntity<List<Currency>> getAll(){
        List<Currency> currencies = currencyDAO.getAll();
        return ResponseEntity.ok(currencies);
    }

}
