package pl.deska.springbootcurrencyapigame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import pl.deska.springbootcurrencyapigame.model.Currency;
import pl.deska.springbootcurrencyapigame.parser.CurrencyParser;

@Service
@Scope(scopeName = "prototype")
public class CurrencyService {

    private String hint = "";
    private Currency currency;
    private CurrencyParser currencyParser;

    @Autowired
    public CurrencyService(CurrencyParser currencyParser) {
        this.currencyParser = currencyParser;
        this.currency = currencyParser.getRandomCurrency();
    }

    public boolean isGuessCorrect(double rate, double guess){
        setHint(rate, guess);
        return rate == guess;
    }

    public Currency getCurrency() {
        return currency;
    }


    public void resetGame() {
        this.currency = currencyParser.getRandomCurrency();
        this.hint = "";
    }

    public String getHint() {
        return hint;
    }

    private void setHint(double rate, double guess) {
        if(String.valueOf(guess).isBlank() || guess <= 0){
            this.hint = "An invalid value was supplied";
        }else if(rate > guess){
            this.hint = "Not enough, try again";
        }else if(rate < guess){
            this.hint = "Too much, try again";
        }
    }

}
