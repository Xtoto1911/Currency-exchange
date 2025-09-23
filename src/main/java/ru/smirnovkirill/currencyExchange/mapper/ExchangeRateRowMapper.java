package ru.smirnovkirill.currencyExchange.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.smirnovkirill.currencyExchange.model.Currency;
import ru.smirnovkirill.currencyExchange.model.ExchangeRates;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ExchangeRateRowMapper implements RowMapper<ExchangeRates> {

    @Override
    public ExchangeRates mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExchangeRates er = new ExchangeRates();
        er.setId(rs.getInt("rate_id"));
        er.setRate(rs.getBigDecimal("rate"));

        Currency base = new Currency();
        base.setId(rs.getLong("base_id"));
        base.setFullName(rs.getString("base_name"));
        base.setCode(rs.getString("base_code"));
        base.setSign(rs.getString("base_sign"));

        er.setBaseCurrency(base);

        Currency target = new Currency();
        target.setId(rs.getLong("target_id"));
        target.setFullName(rs.getString("target_name"));
        target.setCode(rs.getString("target_code"));
        target.setSign(rs.getString("target_sign"));

        er.setTargetCurrency(target);

        return er;
    }
}
