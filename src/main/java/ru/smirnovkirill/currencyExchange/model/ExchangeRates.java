package ru.smirnovkirill.currencyExchange.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ExchangeRates {

    private long id;

    @NonNull
    private Currency baseCurrency;

    @NonNull
    private Currency targetCurrency;

    @NonNull
    private BigDecimal rate;
}
