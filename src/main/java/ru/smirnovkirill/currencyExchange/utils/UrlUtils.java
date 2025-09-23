package ru.smirnovkirill.currencyExchange.utils;

public class UrlUtils {
    public static boolean isValidCurrencyUrl(String url) {
        return url.length() == 3 && url.equals(url.toUpperCase());
    }

    public static String[] parseUrl(String url) {
        String baseUrl = url.substring(0, 3);
        String targetUrl = url.substring(3, 6);
        return new String[]{baseUrl,targetUrl};
    }

    public static boolean isValidExchangeUrl(String baseUrl, String targetUrl) {
        return isValidCurrencyUrl(baseUrl) && isValidCurrencyUrl(targetUrl);
    }

}
