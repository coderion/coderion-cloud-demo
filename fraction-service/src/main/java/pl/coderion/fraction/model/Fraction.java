package pl.coderion.fraction.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Fraction {

    private Integer numerator;

    private Integer denominator;

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
