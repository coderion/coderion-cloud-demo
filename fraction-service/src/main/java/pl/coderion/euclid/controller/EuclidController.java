package pl.coderion.euclid.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.coderion.euclid.library.Euclid;

@Slf4j
@RestController
public class EuclidController {

    @GetMapping("/gcd/{a}/{b}")
    public Integer getGcd(@PathVariable Integer a, @PathVariable Integer b) {
        Integer gcd = Euclid.gcd(a, b);
        log.info(String.format("GCD(%s,%s)=%s", a, b, gcd));
        return gcd;
    }
}
