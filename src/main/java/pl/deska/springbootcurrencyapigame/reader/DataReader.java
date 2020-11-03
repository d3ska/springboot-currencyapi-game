package pl.deska.springbootcurrencyapigame.reader;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface DataReader {

    ResponseEntity getData();
}
