package ru.smirnovkirill.currencyExchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.smirnovkirill.currencyExchange.dao.ExchangeRatesDAO;
import ru.smirnovkirill.currencyExchange.model.Exchange;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ExchangeService {
    private final ExchangeRatesDAO exchangeRatesDAO;

    @Autowired
    public ExchangeService(ExchangeRatesDAO exchangeRatesDAO) {
        this.exchangeRatesDAO = exchangeRatesDAO;
    }

    public Optional<Exchange> convert(BigDecimal amount, String baseCode, String targetCode) {
        return exchangeRatesDAO.getExchangeRates(baseCode, targetCode)
                .map(rate -> {
                    BigDecimal converted = amount.multiply(rate.getRate());
                    return new Exchange(
                            rate.getBaseCurrency(),
                            rate.getTargetCurrency(),
                            rate.getRate(),
                            amount,
                            converted
                    );
                });
    }
}
