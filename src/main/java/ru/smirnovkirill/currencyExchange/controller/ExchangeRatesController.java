package ru.smirnovkirill.currencyExchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smirnovkirill.currencyExchange.dao.ExchangeRatesDAO;
import ru.smirnovkirill.currencyExchange.model.ExchangeRates;
import ru.smirnovkirill.currencyExchange.utils.UrlUtils;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/exchangeRates")
public class ExchangeRatesController {

    private final ExchangeRatesDAO exchangeRatesDAO;

    @Autowired
    public ExchangeRatesController(ExchangeRatesDAO exchangeRatesDAO) {
        this.exchangeRatesDAO = exchangeRatesDAO;
    }


    @GetMapping()
    public ResponseEntity<List<ExchangeRates>> getAll() {
        return ResponseEntity.ok(exchangeRatesDAO.getAll());
    }

    @GetMapping("/{code}")
    public ResponseEntity<ExchangeRates> getExchange(@PathVariable("code") String urlCode) {
        String[] urls = UrlUtils.parseUrl(urlCode);
        if(urls.length != 2 || !UrlUtils.isValidExchangeUrl(urls[0], urls[1])) {
            return ResponseEntity.notFound().build();
        }

        Optional<ExchangeRates> exchangeRates = exchangeRatesDAO.getExchangeRates(urls[0], urls[1]);

        return exchangeRates.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
}
