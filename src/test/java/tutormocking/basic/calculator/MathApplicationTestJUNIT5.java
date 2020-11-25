package tutormocking.basic.calculator;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MathApplicationTestJUNIT5 {


    /**
     * The Calc service.
     * objekt to be inserted inside mathApplication object
     */
    @Mock
    CalculatorService calcService;


    /**
     * objekt do ktereho budou vkladany mockovane objekty aby odstranili jeho zavislost
     * na vnejsim svete
     * The Math application.
     */
    @InjectMocks
    MathApplication mathApplication = new MathApplication();

    @BeforeEach
     void initAll() {
        MockitoAnnotations.initMocks(this);
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "10,    20,  30.0",
            "20,    20,  40.0"
    })
    void add(int first, int second, double expectedResult) {
        when(calcService.add(first, second)).thenReturn(expectedResult);
        assertEquals(expectedResult, mathApplication.add(first, second),
                () -> first + " + " + second + " should equal " + expectedResult);
    }
}