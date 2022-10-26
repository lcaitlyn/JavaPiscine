package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {

    // Код, отмеченный @Before, выполняется перед каждым тестом,
    // а @BeforeClass выполняется один раз перед всем тестированием.
    // Если ваш тестовый класс имеет десять тестов, код @Before
    // будет выполняться десять раз, но @BeforeClass будет выполняться только один раз.
    // В JUnit 5 теги @BeforeEach и @BeforeAll являются
    // эквивалентами @Before и @BeforeClass в JUnit 4
    NumberWorker numberWorker;

    @BeforeEach
    void beforeEach() {
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 11, 13, 17, 19, 997, 811, Integer.MAX_VALUE})
    void isPrimeTrueTest(int number) {
        Assertions.assertTrue(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 15, 25, 112, 9, 16})
    void isPrimeFalseTest(int number) {
        Assertions.assertFalse(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1, -228, Integer.MIN_VALUE})
    void isPrimeExpectedExceptionTest(int number) {
        Assertions.assertThrows(IllegalNumberException.class, ()->{
           numberWorker.isPrime(number);
        });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.scv", delimiter = ',')
    void digitsSumTest(int source, int expected) {
        Assertions.assertEquals(expected, numberWorker.digitsSum(source));
    }
}
