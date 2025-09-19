package ru.smirnovkirill.currencyExchange.utils;

public class UrlUtils {
    public static boolean isValidCurrencyUrl(String url) {
        return url.length() == 3 && url.equals(url.toUpperCase());
    }
}
