package ru.smirnovkirill.currencyExchange.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.smirnovkirill.currencyExchange.dto.CurrencyDto;
import ru.smirnovkirill.currencyExchange.model.Currency;

import java.util.List;
import java.util.Optional;

@Component
public class CurrencyDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CurrencyDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Currency> getAll(){
        return jdbcTemplate.query("select * from currencies", new BeanPropertyRowMapper<>(Currency.class));
    }

    public Optional<Currency> getCurrencyByCode(String code) {
        List<Currency> currencies = jdbcTemplate.query(
                                                    "select * from currencies where code=?",
                                                        new BeanPropertyRowMapper<>(Currency.class),
                                                        code);
        return currencies.isEmpty() ? Optional.empty() : Optional.of(currencies.get(0));
    }

    public Currency save(CurrencyDto currencyDto) {
        return jdbcTemplate.queryForObject(
                "insert into currencies(code, fullName, sign) values(?,?,?) returning id, code, fullName, sign",
                new Object[]{currencyDto.getCode(), currencyDto.getFullName(), currencyDto.getSign()},
                ((rs, rowNum) -> new Currency(
                        rs.getLong("id"),
                        rs.getString("code"),
                        rs.getString("fullName"),
                        rs.getString("sign")
                )));
    }
}
