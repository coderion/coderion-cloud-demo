package pl.coderion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MathController {

    @Autowired
    private FractionServiceProxy fractionServiceProxy;

    @GetMapping("/gcd/{a}/{b}")
    Integer getGcd(@PathVariable Integer a, @PathVariable Integer b) {
        Integer gcd = fractionServiceProxy.getGcd(a, b);
        log.info("{}", gcd);
        return gcd;
    }

    @GetMapping("/lcd/{a}/{b}")
    Integer getLcd(@PathVariable Integer a, @PathVariable Integer b) {
        Integer lcd = fractionServiceProxy.getLcd(a, b);
        log.info("{}", lcd);
        return lcd;
    }
}
