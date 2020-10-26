package pl.coderion.euclid.library;

import pl.coderion.fraction.model.Fraction;

public class Euclid {

    public static Integer gcd(Integer a, Integer b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }

        return a;
    }

    public static Integer lcd(Integer a, Integer b) {
        return (a * b) / gcd(a, b);
    }

    public static Fraction add(Fraction a, Fraction b) {
        Integer newDenominator = lcd(a.getDenominator(), b.getDenominator());
        Integer newNumerator1 = (newDenominator / a.getDenominator()) * a.getNumerator();
        Integer newNumerator2 = (newDenominator / b.getDenominator()) * b.getNumerator();
        Integer newNumerator = newNumerator1 + newNumerator2;

        Fraction newFraction = new Fraction(newNumerator, newDenominator);

        return simplify(newFraction);
    }

    public static Fraction subtract(Fraction a, Fraction b) {
        Fraction newB = new Fraction(-1 * b.getNumerator(), b.getDenominator());
        return add(a, newB);
    }

    public static Fraction multiply(Fraction a, Fraction b) {
        return simplify(new Fraction(a.getNumerator() * b.getNumerator(), a.getDenominator() * b.getDenominator()));
    }

    public static Fraction divide(Fraction a, Fraction b) {
        Fraction newB = new Fraction(b.getDenominator(), b.getNumerator());
        return multiply(a, newB);
    }

    public static Fraction square(Fraction a) {
        return simplify(new Fraction(a.getNumerator() * a.getNumerator(), a.getDenominator() * a.getDenominator()));
    }

    public static Fraction simplify(Fraction a) {
        Integer gcd = gcd(a.getNumerator(), a.getDenominator());
        Integer newNumerator = a.getNumerator() / gcd;
        Integer newDenominator = a.getDenominator() / gcd;
        Fraction result = new Fraction(newNumerator, newDenominator);
        return result;
    }
}