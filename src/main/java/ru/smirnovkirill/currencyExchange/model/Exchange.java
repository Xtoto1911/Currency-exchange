package ru.smirnovkirill.currencyExchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exchange extends ExchangeRates{
    @NonNull
    private BigDecimal amount;

    private BigDecimal convertedAmount;
}
