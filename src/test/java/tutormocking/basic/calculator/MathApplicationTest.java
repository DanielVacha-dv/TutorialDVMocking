package tutormocking.basic.calculator;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.when;

/** old way hot to use Runner
 * The type Math application test.
 * @RunWith attaches a runner with the test class to initialize the test data
 */
@RunWith(MockitoJUnitRunner.class)
public class MathApplicationTest {

    /**
     * Novy zpusob s vyuzitim Rule objektu
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


    /** objekt do ktereho budou vkladany mockovane objekty abz odstranili jeho zavislost
     * na vnejsim svete
     * The Math application.
     */
    @InjectMocks
    MathApplication mathApplication = new MathApplication();


    /** pro overeni testovaci metody soucet
     * Test add.
     */
    @Test
    public void testAdd() {
        //add the behavior of calc service to add two numbers
        // mock chovani, kdyz se zavola metoda calcService.add(.. vrati se hodnota 30
        when(calcService.add(10.0, 20.0)).thenReturn(30.00);
        double add = mathApplication.add(10.0, 20.0);
        System.out.println("add=" + add);
        //test the add functionality
        Assert.assertEquals(add, 30.0, 0);
    }

}