import exceptions.InvalidCalculatorInputException;
import exceptions.NegativesNotAllowedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public class CalculatorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Calculator calculator;

    @Before
    public void init() {
        calculator = new Calculator();
    }

    @Test
    public void shouldReturnZeroWhenEmptyString() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        int calculatorOutputActual = calculator.add("");
        assertEquals(0, calculatorOutputActual);
    }

    @Test
    public void shouldReturnInputWhenSingleNumber() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        int calculatorOutputActual = calculator.add("1");
        assertEquals(1, calculatorOutputActual);
    }

    @Test
    public void shouldReturnSumOfTwoNumbers() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        int calculatorOutputActual = calculator.add("1,2");
        assertEquals(3, calculatorOutputActual);
    }

    @Test
    public void shouldReturnZeroWhenWhitespace() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        int calculatorOutputActual = calculator.add(" ");
        assertEquals(0, calculatorOutputActual);
    }

    @Test
    public void shouldReturnSumOfTwoBiggerNumbers() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        int calculatorOutputActual = calculator.add("10,32");
        assertEquals(42, calculatorOutputActual);
    }

    @Test
    public void shouldReturnSumOfTwoNumbersWhenRandomWhitespacesInserted() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        int calculatorOutputActual = calculator.add(" 1 ,          2 ");
        assertEquals(3, calculatorOutputActual);
    }

    @Test
    public void shouldReturnInvalidCalculatorInputExceptionWhenEmptyStringBeforeComa() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        thrown.expect(InvalidCalculatorInputException.class);
        calculator.add(",1");
    }

    @Test
    public void shouldReturnInvalidCalculatorInputExceptionWhenEmptyStringAfterComa() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        thrown.expect(InvalidCalculatorInputException.class);
        calculator.add("1,");
    }

    @Test
    public void shouldReturnSumOfThreeNumbers() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        int calculatorOutputActual = calculator.add("1,2,3");
        assertEquals(6, calculatorOutputActual);
    }

    @Test
    public void shouldReturnSumOfMultipleNumbers() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        int calculatorOutputActual = calculator.add("6,677,9,10,2");
        assertEquals(704, calculatorOutputActual);
    }

    @Test
    public void shouldThrownInvalidCalculatorInputExceptionWhenNoNumbers() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        thrown.expect(InvalidCalculatorInputException.class);
        calculator.add("abcd");
    }

    @Test
    public void shouldThrownInvalidCalculatorInputExceptionWhenNoNumbersAndComa() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        thrown.expect(InvalidCalculatorInputException.class);
        calculator.add("a,b");
    }

    @Test
    public void shouldThrownInvalidCalculatorInputExceptionWhenDouble() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        thrown.expect(InvalidCalculatorInputException.class);
        calculator.add("0.5,1");
    }

    @Test
    public void shouldReturnSumOfNumbersWhenEndLineIsDelimiter() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        int calculatorOutputActual = calculator.add("1\n2,3");
        assertEquals(6, calculatorOutputActual);
    }

    @Test
    public void shouldThrownInvalidCalculatorInputExceptionWhenEndLineAsNumber() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        thrown.expect(InvalidCalculatorInputException.class);
        calculator.add("1,\n");
    }

    @Test
    public void shouldReturnSumOfNumbersWhenDelimiterSpecifiedAsSemicolon() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        int calculatorOutputActual = calculator.add("//;\n1;2");
        assertEquals(3, calculatorOutputActual);
    }

    @Test
    public void shouldReturnSumOfNumbersIgnoringNumbersBiggerThan1000() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        int calculatorOutputActual = calculator.add("2,1001");
        assertEquals(2, calculatorOutputActual);
    }

    @Test
    public void shouldReturnSumOfNumbersNotIgnoringNumbersEqual1000() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        int calculatorOutputActual = calculator.add("2,1000");
        assertEquals(1002, calculatorOutputActual);
    }

    @Test
    public void shouldReturnSumOfNumbersIgnoringNumbersBiggerThan1000AndNotIgnoringNumbersEqual1000() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        int calculatorOutputActual = calculator.add("2,1001,1000");
        assertEquals(1002, calculatorOutputActual);
    }

    @Test
    public void shouldThrownNegativesNotAllowedExceptionWhenAllNumbersAreNegative() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        thrown.expect(NegativesNotAllowedException.class);
        thrown.expectMessage("Negatives not allowed - [-1, -1]");
        calculator.add("-1,-1");
    }

    @Test
    public void shouldThrownNegativesNotAllowedExceptionWhenOneNumberIsNegative() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        thrown.expect(NegativesNotAllowedException.class);
        thrown.expectMessage("Negatives not allowed - [-1]");
        calculator.add("-1,2");
    }

    @Test
    public void shouldReturnSumOfNumbersWhenDelimiterSetAsMultipleCharacters() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        int calculatorOutputActual = calculator.add("//***\n1***2***3");
        assertEquals(6, calculatorOutputActual);
    }

    @Test
    public void shouldReturnSumOfNumbersWhenDelimiterSetAsDifferentMultipleCharacters() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        int calculatorOutputActual = calculator.add("//**%%\n1**2%%3**4");
        assertEquals(10, calculatorOutputActual);
    }

    @Test
    public void shouldThrownInvalidCalculatorInputExceptionWhenMixedDelimitersBetweenNumbers() throws NegativesNotAllowedException, InvalidCalculatorInputException {
        thrown.expect(InvalidCalculatorInputException.class);
        calculator.add("//*%\n1*%2*%3*%4");
    }
}
