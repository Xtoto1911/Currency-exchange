insert into CurrancyExchanger.Currencies(code, fullName, sign)
values ('USD','United States dollar','$'),
       ('EUR','Euro', '€');

insert into CurrancyExchanger.ExchangeRates(baseCurrencyId, targetCurrencyId, rate)
values (1,3, 0.84),(3,1, 1.18);


