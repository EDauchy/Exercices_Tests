package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class PriceCalculatorTest {

    // CAS NOMINAUX

    @Test
    public void testCalculateTotalPrice() {
        PriceCalculator pc = new PriceCalculator();
        double res = pc.calculateTotalPrice(10.0, 3);
        assertEquals(30.0, res, 0.0001);
    }

    @Test
    public void testApplyDiscount() {
        PriceCalculator pc = new PriceCalculator();
        double res = pc.applyDiscount(100.0, 0.20);
        assertEquals(80.0, res, 0.0001);
    }

    @Test
    public void testCalculateVat() {
        PriceCalculator pc = new PriceCalculator();
        double res = pc.calculateVat(100.0, 0.20);
        assertEquals(20.0, res, 0.0001);
    }

    @Test
    public void testCalculatePriceWithVat() {
        PriceCalculator pc = new PriceCalculator();
        double res = pc.calculatePriceWithVat(100.0, 0.20);
        assertEquals(120.0, res, 0.0001);
    }

    // CAS D'ERREUR

    @Test
    public void shouldThrowWhenUnitPriceIsNegative() {
        PriceCalculator pc = new PriceCalculator();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> pc.calculateTotalPrice(-10.0, 2)
        );

        assertEquals("Le prix unitaire ne doit pas être négatif.", ex.getMessage());
    }

    @Test
    public void shouldThrowWhenQuantityIsNegative() {
        PriceCalculator pc = new PriceCalculator();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> pc.calculateTotalPrice(10.0, -2)
        );

        assertEquals("La quantité ne doit pas être négative.", ex.getMessage());
    }

    @Test
    public void shouldThrowWhenPriceIsNegative() {
        PriceCalculator pc = new PriceCalculator();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> pc.applyDiscount(-100.0, 0.2)
        );

        assertEquals("Le prix ne doit pas être négatif.", ex.getMessage());
    }

    @Test
    public void shouldThrowWhenDiscountRateIsNegative() {
        PriceCalculator pc = new PriceCalculator();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> pc.applyDiscount(100.0, -0.2)
        );

        assertEquals("Le taux de remise ne doit pas être négatif.", ex.getMessage());
    }

    @Test
    public void shouldThrowWhenPriceIsNegativeForVat() {
        PriceCalculator pc = new PriceCalculator();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> pc.calculateVat(-100.0, 0.2)
        );

        assertEquals("Le prix ne doit pas être négatif.", ex.getMessage());
    }

    @Test
    public void shouldThrowWhenVatRateIsNegative() {
        PriceCalculator pc = new PriceCalculator();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> pc.calculateVat(100.0, -0.2)
        );

        assertEquals("Le taux de TVA ne doit pas être négatif.", ex.getMessage());
    }

    @Test
    public void shouldThrowWhenPriceIsNegativeForTotalVatCalculation() {
        PriceCalculator pc = new PriceCalculator();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> pc.calculatePriceWithVat(-100.0, 0.2)
        );

        assertEquals("Le prix ne doit pas être négatif.", ex.getMessage());
    }

    @Test
    public void shouldThrowWhenVatRateIsNegativeForTotalPrice() {
        PriceCalculator pc = new PriceCalculator();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> pc.calculatePriceWithVat(100.0, -0.2)
        );

        assertEquals("Le taux de TVA ne doit pas être négatif.", ex.getMessage());
    }
}