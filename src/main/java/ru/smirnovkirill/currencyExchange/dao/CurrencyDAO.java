package ru.smirnovkirill.currencyExchange.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.smirnovkirill.currencyExchange.model.Currency;

import java.util.List;

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
}
