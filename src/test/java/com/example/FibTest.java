package com.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FibTest {

    @Test
    public void getFibSeries_range1_resultIsNotEmpty() {
        Fib fib = new Fib(1);

        List<Integer> result = fib.getFibSeries();

        assertFalse(result.isEmpty());
    }

    @Test
    public void getFibSeries_range1_equalsZero() {
        Fib fib = new Fib(1);

        List<Integer> result = fib.getFibSeries();

        assertEquals(Arrays.asList(0), result);
    }

    @Test
    public void getFibSeries_range6_containsThree() {
        Fib fib = new Fib(6);

        List<Integer> result = fib.getFibSeries();

        assertTrue(result.contains(3));
    }

    @Test
    public void getFibSeries_range6_hasSixElements() {
        Fib fib = new Fib(6);

        List<Integer> result = fib.getFibSeries();

        assertEquals(6, result.size());
    }

    @Test
    public void getFibSeries_range6_doesNotContainFour() {
        Fib fib = new Fib(6);

        List<Integer> result = fib.getFibSeries();

        assertFalse(result.contains(4));
    }

    @Test
    public void getFibSeries_range6_equalsExpectedSeries() {
        Fib fib = new Fib(6);

        List<Integer> result = fib.getFibSeries();

        assertEquals(Arrays.asList(0, 1, 1, 2, 3, 5), result);
    }

    @Test
    public void getFibSeries_range6_isSortedAscending() {
        Fib fib = new Fib(6);

        List<Integer> result = fib.getFibSeries();

        for (int i = 1; i < result.size(); i++) {
            assertTrue(result.get(i - 1) <= result.get(i));
        }
    }
}
