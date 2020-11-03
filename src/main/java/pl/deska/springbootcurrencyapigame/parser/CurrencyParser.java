package pl.deska.springbootcurrencyapigame.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.deska.springbootcurrencyapigame.model.CurrenciesData;
import pl.deska.springbootcurrencyapigame.model.Currency;
import pl.deska.springbootcurrencyapigame.model.TypeCurrency;
import pl.deska.springbootcurrencyapigame.reader.DataReader;

import java.util.*;

@Service
public class CurrencyParser {

    private Random random;
    private DataReader dataReader;
    private CurrenciesData currenciesData;
    private Map<String, Double> currencies;

    @Autowired
    public CurrencyParser(DataReader dataReader) {
        this.random = new Random();
        this.dataReader = dataReader;
        this.currenciesData = (CurrenciesData)dataReader.getData().getBody();
        this.currencies = new HashMap<>();
    }

    public Currency getRandomCurrency(){
        parseCurrenciesToMap(currencies, currenciesData);
        String randomCurrencyName = getRandomCurrencyName();
        Double rate  = changeDecimalPlace(currencies.get(randomCurrencyName));
        Currency currency = new Currency(randomCurrencyName, rate, currenciesData.getBase(), currenciesData.getDate());

        return currency;
    }

    private String getRandomCurrencyName() {
        return TypeCurrency.values()[random.nextInt(TypeCurrency.values().length)].toString();
    }

    private Double changeDecimalPlace(Double price) {
        String result = price.toString();
        if(price >= 1000){
            result = result.substring(0, 7);
        }
        else if(price >= 100){
          result =  result.substring(0,6);
        }else if( price >= 10){
           result =  result.substring(0,5);
        }else if(price < 10){
           result =  result.substring(0, 4);
        }
        return Double.valueOf(result);
    }

    private void parseCurrenciesToMap(Map<String, Double> currencies, CurrenciesData body) {
        currencies.put("AUD", body.getRates().getAUD());
        currencies.put("BGN", body.getRates().getBGN());
        currencies.put("BRL", body.getRates().getBRL());
        currencies.put("CAD", body.getRates().getCAD());
        currencies.put("CHF", body.getRates().getCHF());
        currencies.put("CNY", body.getRates().getCNY());
        currencies.put("DKK", body.getRates().getDKK());
        currencies.put("CZK", body.getRates().getCZK());
        currencies.put("GBP", body.getRates().getGBP());
        currencies.put("HKD", body.getRates().getHKD());
        currencies.put("HRK", body.getRates().getHRK());
        currencies.put("HUF", body.getRates().getHUF());
        currencies.put("IDR", body.getRates().getIDR());
        currencies.put("ILS", body.getRates().getILS());
        currencies.put("INR", body.getRates().getINR());
        currencies.put("ISK", body.getRates().getISK());
        currencies.put("JPY", body.getRates().getJPY());
        currencies.put("KRW", body.getRates().getKRW());
        currencies.put("MXN", body.getRates().getMXN());
        currencies.put("MYR", body.getRates().getMYR());
        currencies.put("NOK", body.getRates().getNOK());
        currencies.put("NZD", body.getRates().getNZD());
        currencies.put("PHP", body.getRates().getPHP());
        currencies.put("PLN", body.getRates().getPLN());
        currencies.put("RON", body.getRates().getRON());
        currencies.put("RUB", body.getRates().getRUB());
        currencies.put("SEK", body.getRates().getSEK());
        currencies.put("SGD", body.getRates().getSGD());
        currencies.put("THB", body.getRates().getTHB());
        currencies.put("TRY", body.getRates().getTRY());
        currencies.put("USD", body.getRates().getUSD());
        currencies.put("ZAR", body.getRates().getZAR());
    }
}
