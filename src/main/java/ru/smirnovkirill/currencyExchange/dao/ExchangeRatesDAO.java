package ru.smirnovkirill.currencyExchange.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.smirnovkirill.currencyExchange.mapper.ExchangeRateRowMapper;
import ru.smirnovkirill.currencyExchange.model.ExchangeRates;

import java.util.List;
import java.util.Optional;

@Component
public class ExchangeRatesDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExchangeRatesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ExchangeRates> getAll() {
        return jdbcTemplate.query(
                "select er.id as rate_id, " +
                            "bc.id as base_id, " +
                            "bc.fullName as base_name, " +
                            "bc.code as base_code, " +
                            "bc.sign as base_sign, " +
                            "tc.id as target_id, " +
                            "tc.fullName as target_name, " +
                            "tc.code as target_code, " +
                            "tc.sign as target_sign, " +
                            "er.rate " +
                    "from ExchangeRates as er " +
                          "join " +
                          "currencies as bc on er.baseCurrencyId = bc.id " +
                          "join " +
                          "currencies as tc on er.targetCurrencyId = tc.id " +
                    "order by er.id",
                new ExchangeRateRowMapper()
        );
    }

    public Optional<ExchangeRates> getExchangeRates(String base, String target) {
        List<ExchangeRates> listExchange = jdbcTemplate.query(
                "select er.id as rate_id, " +
                        "bc.id as base_id, " +
                        "bc.fullName as base_name, " +
                        "bc.code as base_code, " +
                        "bc.sign as base_sign, " +
                        "tc.id as target_id, " +
                        "tc.fullName as target_name, " +
                        "tc.code as target_code, " +
                        "tc.sign as target_sign, " +
                        "er.rate " +
                        "from ExchangeRates as er " +
                        "join " +
                        "currencies as bc on er.baseCurrencyId = bc.id " +
                        "join " +
                        "currencies as tc on er.targetCurrencyId = tc.id " +
                        "where bc.code = ? and tc.code = ?",
                ps -> {
                    ps.setString(1, base);
                    ps.setString(2, target);
                } ,
                new ExchangeRateRowMapper()
        );

        return listExchange.isEmpty() ? Optional.empty() : Optional.of(listExchange.get(0));
    }
}
