package com.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GradingCalculatorTest {

    @Test
    public void testAGrade() {
        // Arrange
        GradingCalculator GD = new GradingCalculator(95,90);

        // Act
        char result = GD.getGrade();

        // Assert
        assertEquals('A', result);
    }

    @Test
    public void testBGrade85and90() {
        // Arrange
        GradingCalculator GD = new GradingCalculator(85,90);

        // Act
        char result = GD.getGrade();

        // Assert
        assertEquals('B', result);
    }

    @Test
    public void testBGrade95and65() {
        // Arrange
        GradingCalculator GD = new GradingCalculator(95,65);

        // Act
        char result = GD.getGrade();

        // Assert
        assertEquals('B', result);
    }

    @Test
    public void testCGrade() {
        // Arrange
        GradingCalculator GD = new GradingCalculator(65,90);

        // Act
        char result = GD.getGrade();

        // Assert
        assertEquals('C', result);
    }


    @Test
    public void testFGrade95and55() {
        // Arrange
        GradingCalculator GD = new GradingCalculator(95,55);

        // Act
        char result = GD.getGrade();

        // Assert
        assertEquals('F', result);
    }

    @Test
    public void testFGrade65and55() {
        // Arrange
        GradingCalculator GD = new GradingCalculator(65,55);

        // Act
        char result = GD.getGrade();

        // Assert
        assertEquals('F', result);
    }

    @Test
    public void testFGrade50and90() {
        // Arrange
        GradingCalculator GD = new GradingCalculator(50,90);

        // Act
        char result = GD.getGrade();

        // Assert
        assertEquals('F', result);
    }
}
