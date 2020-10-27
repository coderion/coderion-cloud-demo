package pl.coderion;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EuclidController {

    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod = "fallbackToleranceExample")
    public Integer faultToleranceExample() {
        throw new RuntimeException("fault-tolerance-example");
    }

    @GetMapping("/gcd-fault/{a}/{b}")
    @HystrixCommand(fallbackMethod = "fallbackGcd", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10"),  // timeout 10ms
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),        // 10s window
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),              // 5 times
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),           // min 50% calls with error
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),        // leave 5s in fallback
    })
    public Integer getGcdWithFaultTolerance(@PathVariable Integer a, @PathVariable Integer b) {
        try {
            Thread.sleep(new RandomDataGenerator().nextLong(1, 20));
            Integer gcd = Euclid.gcd(a, b);
            log.info(String.format("GCD(%s,%s)=%s", a, b, gcd));
            return gcd;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

    public Integer fallbackToleranceExample() {
        return Euclid.gcd(1, 1);
    }

    public Integer fallbackGcd(Integer a, Integer b) {
        return Euclid.gcd(1, 1);
    }
}
