import org.example.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculatorTest {
    Calculator calculator;
    Calculator calculator2;

    @Mock
    Random random;

    @BeforeAll
    static void initial() throws Exception {
        System.out.println("Hello TDD");
    }

    @BeforeEach
    void setUp() throws Exception {
        calculator = spy(new Calculator());
    }

    @Test
    @EnabledOnOs({OS.MAC})
    void itShouldNotNull(){
        assertNull(calculator2, "Calculator should null");
    }

    @Test
    @DisplayName("Simple multiplication should work")
    void shouldDoSimpleMultiplyCorrectly() {
        int answer = calculator.multiply(4, 5);
        assertEquals(20, answer,"Regular multiplication should work");
    }

    @Test
    @DisplayName("Ensure multiply by zero")
    void testMultiplyByZero() {
        assertEquals(0, calculator.multiply(4, 0),"Regular multiplication should work");
    }

    @Test
    void testDiv(){
        assertEquals(2, calculator.div(4,2), "Regular divide");
    }

    @Test
    void divByZeroShouldThrowException(){
       Throwable exception = assertThrows(IllegalArgumentException.class, () -> calculator.div(4,0));
        assertEquals("Division by 0",exception.getMessage());
        //assertEquals(0, calculator.div(4,0), "Regular division by zero");
    }

    @CsvSource({
            "1,+,1,2",
            "2,+,2,4",
            "1,-,1,0",
            "2,-,1,1",
            "3,*,1,3",
            "3,*,2,6"
    })
    @ParameterizedTest
    void shouldBeCalculateDependOnOperator(int operand1, String operator, int operand2, int result) {
        switch (operator) {
            case "+":
                assertEquals(result, calculator.add(operand1, operand2));
                break;
            case "-":
                assertEquals(result, calculator.del(operand1, operand2));
                break;
            case "*":
                assertEquals(result, calculator.multiply(operand1, operand2));
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "./input.txt")
    void shouldBeCalculateDependOnOperatorFromFileInput(int operand1, String operator, int operand2, int result) {
        switch (operator) {
            case "+":
                assertEquals(result, calculator.add(operand1, operand2));
                break;
            case "-":
                assertEquals(result, calculator.del(operand1, operand2));
                break;
            case "*":
                assertEquals(result, calculator.multiply(operand1, operand2));
        }
    }

    @Test
    void shouldRandomAnyNumber() {
        Random r = new Random();
        assertNotNull(calculator.magicRandom(r));
    }

    @Test
    void shouldRandomReturn5() {
        when(random.nextInt()).thenReturn(5);
        assertEquals(5, calculator.magicRandom(random));
        //assertFalse(true);
    }

    @Test
    void shouldRandomBeingCalled1Time() {
          Random spyRandom = spy(new Random());
          calculator.magicRandom(spyRandom);
          verify(spyRandom, atLeast(1)).nextInt();
    }

    @Test
    void shouldUseCustomAddWhenMultiply() {

        calculator.multiply(2,2);
        verify(calculator, atLeastOnce()).add(2,2);
    }

}
