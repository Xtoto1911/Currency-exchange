package ru.smirnovkirill.currencyExchange.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto {

    private String code;

    private String fullName;

    private String sign;
}
