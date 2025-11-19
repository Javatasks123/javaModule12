import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegerDecodeTests {

    // Сценарий 1: Простое положительное десятичное число (radix 10)
    @Test
    void test1_DecimalPositive() {
        assertEquals(123, Integer.decode("123"), "Должно декодировать '123' как 123.");
    }

    // Сценарий 2: Отрицательное десятичное число (radix 10, префикс '-')
    @Test
    void test2_DecimalNegative() {
        assertEquals(-45, Integer.decode("-45"), "Должно декодировать '-45' как -45.");
    }

    // Сценарий 3: Явно положительное десятичное число (radix 10, префикс '+')
    @Test
    void test3_DecimalExplicitPositive() {
        assertEquals(67, Integer.decode("+67"), "Должно декодировать '+67' как 67.");
    }

    // Сценарий 4: Шестнадцатеричное число (radix 16, префикс '0x')
    @Test
    void test4_HexPrefix0x() {
        // 0x1A = 26
        assertEquals(26, Integer.decode("0x1A"), "Должно декодировать '0x1A' как 26.");
    }

    // Сценарий 5: Шестнадцатеричное число (radix 16, префикс '#')
    @Test
    void test5_HexPrefixHash() {
        // #FF = 255
        assertEquals(255, Integer.decode("#FF"), "Должно декодировать '#FF' как 255.");
    }

    // Сценарий 6: Отрицательное шестнадцатеричное число (radix 16, префикс '-0x')
    @Test
    void test6_NegativeHex() {
        // -0xC = -12
        assertEquals(-12, Integer.decode("-0xC"), "Должно декодировать '-0xC' как -12.");
    }

    // Сценарий 7: Восьмеричное число (radix 8, префикс '0')
    @Test
    void test7_OctalPrefix0() {
        // 017 = 15
        assertEquals(15, Integer.decode("017"), "Должно декодировать '017' как 15.");
    }

    // Сценарий 8: Тест на null (NullPointerException)
    @Test
    void test8_NullInput() {
        assertThrows(NullPointerException.class, () -> {
            Integer.decode(null);
        }, "Должно выбросить NullPointerException для null.");
    }

    // Сценарий 9: Тест на пустую строку (NumberFormatException)
    @Test
    void test9_EmptyString() {
        assertThrows(NumberFormatException.class, () -> {
            Integer.decode("");
        }, "Должно выбросить NumberFormatException для пустой строки.");
    }

    // Сценарий 10: Тест на невалидные символы после префикса (NumberFormatException)
    @Test
    void test10_InvalidCharacters() {
        assertThrows(NumberFormatException.class, () -> {
            Integer.decode("0xG"); // 'G' не является шестнадцатеричной цифрой
        }, "Должно выбросить NumberFormatException для недопустимых символов.");
    }

    // Сценарий 11: Тест на переполнение (NumberFormatException)
    @Test
    void test11_Overflow() {
        assertThrows(NumberFormatException.class, () -> {
            // Строка больше, чем Integer.MAX_VALUE (2147483647)
            Integer.decode("2147483648");
        }, "Должно выбросить NumberFormatException при переполнении.");
    }
}