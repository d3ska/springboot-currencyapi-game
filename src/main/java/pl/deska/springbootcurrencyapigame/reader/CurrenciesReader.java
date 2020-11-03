package pl.deska.springbootcurrencyapigame.reader;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.deska.springbootcurrencyapigame.model.CurrenciesData;

@RestController
public class CurrenciesReader implements DataReader {

    private static final String CURRENCY_API = "https://api.exchangeratesapi.io/latest?base=PLN";

    @Override
    public ResponseEntity getData() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CurrenciesData> currencies = restTemplate.exchange(CURRENCY_API,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                CurrenciesData.class);
        return currencies;
    }
}
