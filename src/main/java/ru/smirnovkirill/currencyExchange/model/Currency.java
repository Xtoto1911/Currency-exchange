package ru.smirnovkirill.currencyExchange.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Currency {
    private long id;

    @NonNull
    private String code;
    @NonNull
    private String fullName;
    @NonNull
    private String sign;
}
