create schema if not exists CurrancyExchanger;

create table if not exists CurrancyExchanger.Currencies (
    id serial primary key,
    code varchar(10) unique not null,
    fullName varchar(100) not null,
    sign varchar(5) not null
);

create table if not exists CurrancyExchanger.ExchangeRates (
    id serial primary key,
    baseCurrencyId int not null references CurrancyExchanger.currencies(id),
    targetCurrencyId int not null references CurrancyExchanger.currencies(id),
    rate decimal not null
);
