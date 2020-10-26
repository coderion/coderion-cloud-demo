package pl.coderion;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EuclidController {

    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod = "fallbackGcd")
    public Integer faultToleranceExample() {
        throw new RuntimeException("fault-tolerance-example");
    }

    @GetMapping("/gcd/{a}/{b}")
    public Integer getGcd(@PathVariable Integer a, @PathVariable Integer b) {
        Integer gcd = Euclid.gcd(a, b);
        log.info(String.format("GCD(%s,%s)=%s", a, b, gcd));
        return gcd;
    }

    @GetMapping("/lcd/{a}/{b}")
    public Integer getLcd(@PathVariable Integer a, @PathVariable Integer b) {
        Integer lcd = Euclid.lcd(a, b);
        log.info(String.format("LCD(%s,%s)=%s", a, b, lcd));
        return lcd;
    }

    public Integer fallbackGcd() {
        return Euclid.gcd(1, 1);
    }
}
