package pl.deska.springbootcurrencyapigame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.deska.springbootcurrencyapigame.model.Currency;
import pl.deska.springbootcurrencyapigame.service.CurrencyService;

@Controller
public class CurrencyController {

    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyVerifier) {
        this.currencyService = currencyVerifier;
    }

    @GetMapping("/play")
    public String playGame(Model model){
        Currency currency = currencyService.getCurrency();
        model.addAttribute("userGuess", new Currency());
        model.addAttribute("randomCurrency", currency.getName());
        model.addAttribute("randomCurrencyRate", currency.getRate());
        model.addAttribute("currencyBase", currency.getBase());
        model.addAttribute("rateDate", currency.getDate());
        model.addAttribute("hint", currencyService.getHint());
        model.addAttribute("description",  currency.getName() + " relative to " + currency.getBase());

        return "gameCurrency";
    }

    @PostMapping("/check")
    public String checkThatGuessIsCorrect(@ModelAttribute Currency currency, Model model){
        Double userGuess = currency.getRate();
        Double correctRate = currencyService.getCurrency().getRate();
        boolean guessIsCorrect = currencyService.isGuessCorrect(correctRate, userGuess);
        if(guessIsCorrect) {
            model.addAttribute("message", "Congratulations, you guessed it!");
            model.addAttribute("info",userGuess + " " + currencyService.getCurrency().getName() + "  is equal to   1 PLN");
            model.addAttribute("buttonValue", "Play again");
            return "gameResult";
        }
        return "redirect:/play";
    }


    @PostMapping("/reset")
    public String resetGame(){
        currencyService.resetGame();
        return "redirect:/play";
    }
    }
