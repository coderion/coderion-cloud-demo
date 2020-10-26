package pl.coderion;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "fraction-service")
public interface FractionServiceProxy {

    @GetMapping("/fraction-service/gcd/{a}/{b}")
    Integer getGcd(@PathVariable Integer a, @PathVariable Integer b);

    @GetMapping("/fraction-service/lcd/{a}/{b}")
    Integer getLcd(@PathVariable Integer a, @PathVariable Integer b);
}
