package tutormocking.basic.calculator;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.when;

public class MathApplicationTest {

    //Pole s poznámkami musí být veřejná, nestatická a podtypy TestRule nebo MethodRule
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public ConsoleOutRule rule2 = new ConsoleOutRule("di do pr");

    @Mock
    CalculatorService calcService;

    @InjectMocks
    MathApplication mathApplication = new MathApplication();


    @Test
    public void testAdd(){
        //add the behavior of calc service to add two numbers
        when(calcService.add(10.0,20.0)).thenReturn(30.00);
        double add = mathApplication.add(10.0, 20.0);
        System.out.println("add="+add);
        //test the add functionality
        Assert.assertEquals(add,30.0,0);
    }

}