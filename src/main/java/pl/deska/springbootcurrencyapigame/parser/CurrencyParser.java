package pl.deska.springbootcurrencyapigame.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.deska.springbootcurrencyapigame.model.CurrenciesData;
import pl.deska.springbootcurrencyapigame.model.Currency;
import pl.deska.springbootcurrencyapigame.model.Rates;
import pl.deska.springbootcurrencyapigame.model.TypeCurrency;
import pl.deska.springbootcurrencyapigame.reader.DataReader;

import java.util.*;

@Service
public class CurrencyParser implements DataParser{

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
        parseCurrenciesToMap(currencies, currenciesData);
    }

    @Override
    public Currency getRandomCurrency(){
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
        return Double.parseDouble(result);
    }

    private void parseCurrenciesToMap(Map<String, Double> currencies, CurrenciesData body) {
        Rates rates = body.getRates();
        currencies.put("AUD",rates.getAUD());
        currencies.put("BGN", rates.getBGN());
        currencies.put("BRL", rates.getBRL());
        currencies.put("CAD", rates.getCAD());
        currencies.put("CHF", rates.getCHF());
        currencies.put("CNY", rates.getCNY());
        currencies.put("DKK", rates.getDKK());
        currencies.put("CZK", rates.getCZK());
        currencies.put("GBP", rates.getGBP());
        currencies.put("HKD", rates.getHKD());
        currencies.put("HRK", rates.getHRK());
        currencies.put("HUF", rates.getHUF());
        currencies.put("IDR", rates.getIDR());
        currencies.put("ILS", rates.getILS());
        currencies.put("INR", rates.getINR());
        currencies.put("ISK", rates.getISK());
        currencies.put("JPY", rates.getJPY());
        currencies.put("KRW", rates.getKRW());
        currencies.put("MXN", rates.getMXN());
        currencies.put("MYR", rates.getMYR());
        currencies.put("NOK", rates.getNOK());
        currencies.put("NZD", rates.getNZD());
        currencies.put("PHP", rates.getPHP());
        currencies.put("PLN", rates.getPLN());
        currencies.put("RON", rates.getRON());
        currencies.put("RUB", rates.getRUB());
        currencies.put("SEK", rates.getSEK());
        currencies.put("SGD", rates.getSGD());
        currencies.put("THB", rates.getTHB());
        currencies.put("TRY", rates.getTRY());
        currencies.put("USD", rates.getUSD());
        currencies.put("ZAR", rates.getZAR());
    }
}
