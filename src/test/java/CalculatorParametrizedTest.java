import exceptions.InvalidCalculatorInputException;
import exceptions.NegativesNotAllowedException;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/* Template for happy paths */
@RunWith(Parameterized.class)
public class CalculatorParametrizedTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Constants.EMPTY_STRING, 0},
                {"1", 1},
                {"1,2", 3},
                {Constants.WHITESPACE, 0},
                {"0,0", 0},
                {" 1 , 2 ", 3},
                {"10,32", 42},
                {" 1 ,          2 ", 3},
                {"1,2,3", 6},
                {"6,677,9,10,2", 704},
                {"1,2,3,4,5", 15},
                {"1\n2,3", 6},
                {"//;\n1;2", 3},
                {"2,1001", 2},
                {"2, 1000", 1002},
                {"2, 1001, 1000", 1002},
                {"//***\n1***2***3", 6},
                {"//**%%\n1**2%%3", 6},
                {"//**%%\n1**2%%3**4", 10}
        });
    }

    String calculatorInput;
    int calculatorOutputExpected;

    public CalculatorParametrizedTest(String calculatorInput, int calculatorOutputExpected) {
        this.calculatorInput = calculatorInput;
        this.calculatorOutputExpected = calculatorOutputExpected;
    }

    @Test
    public void addTest() throws InvalidCalculatorInputException, NegativesNotAllowedException {
            int calculatorOutputActual = new Calculator().add(calculatorInput);
            assertEquals(calculatorOutputExpected, calculatorOutputActual);
    }
}