import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class CalculateTests { // Класс для тестов

    private final Calculate calculate = new Calculate();

    // Параметризованный тест на сложение, на вход передаем первое число, второе число, систему счисления и ожидаем результат
    @ParameterizedTest(name = "{displayName} => Первое число = {0}, Второе число = {1}, Система счисления = {2}, Ожидаемый результат = {3}")
    @CsvSource({
            "0, 1, 2, 1",
            "101, 1111, 2, 10100",
            "6, 5, 8, 13",
            "456, 123, 8, 601",
            "100, 100, 10, 200",
            "0, 0, 10, 0",
            "A15, BC, 16, AD1",
            "3A5, 1A45F0D, 16, 1A462B2"
    })
    @DisplayName("Проверка сложения")
    public void testPlus(String number1, String number2, Integer numberSystem, String expectedResult) {
        String actualResult = calculate.plus(number1, number2, numberSystem); // передаем объекты в метод калькулятора
        System.out.println(number1 + " + " + number2 + " = " + actualResult); // debug
        Assertions.assertEquals(expectedResult, actualResult, "Результат сложения не соответствует ожидаемому"); // метод сравнения двух значений, на вход ему подается объект ожидаемый, актуальный и сообщение, если например результаты будут не равны
    }

    // Параметризованный тест на вычитание
    @ParameterizedTest(name = "{displayName} => Первое число = {0}, Второе число = {1}, Система счисления = {2}, Ожидаемый результат = {3}")
    @CsvSource({
            "01, 1, 2, 0",
            "1111, 101, 2, 1010",
            "456, 173, 8, 263",
            "4, 2, 8, 2",
            "10, 100, 10, -90",
            "19, 9, 10, 10",
            "1BC4, AF, 16, 1B15",
            "25EB, 8, 16, 25E3"
    })
    @DisplayName("Проверка вычитания")
    public void testMinus(String number1, String number2, Integer numberSystem, String expectedResult) {
        String actualResult = calculate.minus(number1, number2, numberSystem);
        System.out.println(number1 + " - " + number2 + " = " + actualResult);
        Assertions.assertEquals(expectedResult, actualResult, "Результат вычитания не соответствует ожидаемому");
    }

    // Параметризованный тест на умножение
    @ParameterizedTest(name = "{displayName} => Первое число = {0}, Второе число = {1}, Система счисления = {2}, Ожидаемый результат = {3}")
    @CsvSource({
            "10001, 1110, 2, 11101110",
            "11, 0, 2, 0",
            "7, 7, 8, 61",
            "5, 21, 8, 125",
            "6, 8, 10, 48",
            "200, 21, 10, 4200",
            "AF, BC4, 16, 80AFC",
            "14, 5EB, 16, 765C"
    })
    @DisplayName("Проверка умножения")
    public void testMultiply(String number1, String number2, Integer numberSystem, String expectedResult) {
        String actualResult = calculate.multiply(number1, number2, numberSystem);
        System.out.println(number1 + " * " + number2 + " = " + actualResult);
        Assertions.assertEquals(expectedResult, actualResult, "Результат умножения не соответствует ожидаемому");
    }

    // Параметризованный тест на деление
    @ParameterizedTest(name = "{displayName} => Первое число = {0}, Второе число = {1}, Система счисления = {2}, Ожидаемый результат = {3}")
    @CsvSource({
            "1111, 110, 2, 10",
            "10010, 110, 2, 11",
            "2374, 35, 8, 54",
            "13351, 163, 8, 63",
            "1, 0, 10, zero",
            "30, 5, 10, 6",
            "DE11, 66, 16, 22D",
            "748, 0, 16, zero"
    })
    @DisplayName("Проверка деления")
    public void testDivide(String number1, String number2, Integer numberSystem, String expectedResult) {
        if (number2.equals("0")) { // проверка деления на ноль
            Exception exception = assertThrows(ArithmeticException.class, () -> calculate.divide(number1, number2, numberSystem));
            Assertions.assertEquals("/ by zero", exception.getMessage(), "Сообщение исключения не соответствует ожидаемому");
        } else {
            String actualResult = calculate.divide(number1, number2, numberSystem);
            System.out.println(number1 + " / " + number2 + " = " + actualResult);
            Assertions.assertEquals(expectedResult, actualResult, "Результат деления не соответствует ожидаемому");
        }
    }
    // Динамический тест на умножение
    @TestFactory
    @DisplayName("Проверка умножения. Динамический тест")
    public Stream<DynamicTest> testCalculate() {
        InputStream testData = getClass().getClassLoader().getResourceAsStream("testData.csv"); // получем из каталога resources файл testData.csv
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(testData)));
        return br.lines().skip(1).map(mapCsvLineToDynamicTest()); // делаем map
    }

    private Function<String, DynamicTest> mapCsvLineToDynamicTest() { // метод map
        return s -> { // построчно передаем значения, которые получаются
            final String[] values = s.split(";"); // используем регулярку со сплитом
            return dynamicTest(values[0].replace("{0}", values[1]).replace("{1}", values[2]), () -> assertCalculate(values)); // метод dynamicTest
        };
    }

    private void assertCalculate(String[] values) { // метод asserCalculate для динамических тестов
        String result = calculate.multiply(values[1], values[2], Integer.parseInt(values[3]));
        System.out.println(values[1] + " * " + values[2] + " = " + result);
        assertEquals(values[4], result, "Результат умножения не соответствует ожидаемому");
    }
}
