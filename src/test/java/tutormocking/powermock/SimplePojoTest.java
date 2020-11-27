package tutormocking.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(SimplePojo.class)
public class SimplePojoTest {


     static final Logger  LOGGER = Logger.getLogger(SimplePojoTest.class.getName());

    /**
     * Print element 3.
     * mock privatni metody ve spy objektu
     * @throws Exception the exception
     */
    @Test
    public void mockPrivateFunction() throws Exception {
        SimplePojo simplePojo = new SimplePojo();
        SimplePojo spyMock = spy(simplePojo);
        String expectedResult="String to je inkrement 0 mockovany private";
        // spyMock privatni metody pomoci powermocku
        when(spyMock, "getInfoAndVersion").thenReturn(" mockovany private");
        String result = spyMock.processing();
        LOGGER.info(" processing "+result);
        assertEquals(expectedResult, result);
    }
}