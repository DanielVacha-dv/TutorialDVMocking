package tutormocking.basic.calculator;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.logging.Logger;

import static org.mockito.Mockito.*;

/**
 * old way hot to use Runner
 * The type Math application test.
 *
 * @RunWith attaches a runner with the test class to initialize the test data
 */
//@RunWith(MockitoJUnitRunner.class)
public class MathApplicationTest {

    Logger LOGGER = Logger.getLogger(MathApplicationTest.class.getName());
    /**
     *  zpusob s vyuzitim Rule objektu
     * The constant rule.
     * Pole s poznámkami musí být veřejná, nestatická a podtypy TestRule nebo MethodRule
     */
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    /**
     * The Rule 2.
     */
    @Rule
    public ConsoleOutRule rule2 = new ConsoleOutRule("di do pr");

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


    /**
     * pro overeni testovaci metody soucet
     * Test add.
     */
    @Test
    public void testAdd() {
        //add the behavior of calc service to add two numbers
        // mock chovani, kdyz se zavola metoda calcService.add(.. vrati se hodnota 30
        when(calcService.add(10.0, 20.0)).thenReturn(30.00);
        double add = mathApplication.add(10.0, 20.0);
        LOGGER.info("add method");
        // overeni vysledku
        Assert.assertEquals(30.0, add, 0);
        //overeni ze se volala metoda add objektu calcService s parametry 10 a 40 bude fail
//        verify(calcService).add(10.0, 40);
        //overeni ze se volala metoda add objektu calcService s parametry 10 a 20 bude pass
        verify(calcService).add(10.0, 20);
        LOGGER.info("metod end");
    }

    @Test
    public void subTract() {
        when(calcService.subtract(20.0, 10.0)).thenReturn(10.00);
        double subtract = mathApplication.subtract(10.0, 20.0);
        LOGGER.info("subtract  method");
        Assert.assertEquals(10, subtract, 0);
        verify(calcService,times(1)).subtract(20.0, 10);
        subtract = mathApplication.subtract(20.0, 10.0);
        LOGGER.info("subtract  method");
        Assert.assertEquals(10.0, subtract, 0);
        verify(calcService,times(2)).subtract(20.0, 10.0);
    }


}