package ru.smirnovkirill.currencyExchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.smirnovkirill.currencyExchange.dao.CurrencyDAO;
import ru.smirnovkirill.currencyExchange.dto.CurrencyDto;
import ru.smirnovkirill.currencyExchange.model.Currency;
import ru.smirnovkirill.currencyExchange.utils.UrlUtils;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/currencies")
public class CurrencyController {

    private final CurrencyDAO currencyDAO;

    @Autowired
    public CurrencyController(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    @GetMapping()
    public ResponseEntity<List<Currency>> getAll() {
        List<Currency> currencies = currencyDAO.getAll();
        return ResponseEntity.ok(currencies);
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
    })
    public ResponseEntity<Currency> createCurrency(
            @RequestBody(required = false) CurrencyDto jsonDto,
            @ModelAttribute CurrencyDto formDto) {

        CurrencyDto currencyDto = jsonDto != null ? jsonDto : formDto;

        if(currencyDAO.getCurrencyByCode(currencyDto.getCode()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Currency saveCurrency = currencyDAO.save(currencyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCurrency);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Currency> getCurrency(@PathVariable("code") String code) {
        return Optional.ofNullable(code)
                .filter(UrlUtils::isValidCurrencyUrl)
                .flatMap(currencyDAO::getCurrencyByCode)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
