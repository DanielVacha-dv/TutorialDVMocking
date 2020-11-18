package tutormocking.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(SimplePojo.class)
public class SimplePojoTest {


    /**
     * Print element 3.
     * mock privatni metody ve spy objektu
     * @throws Exception the exception
     */
    @Test
    public void mockPrivateFunction() throws Exception {
        SimplePojo hlavniTrida = new SimplePojo();
        SimplePojo mock = spy(hlavniTrida);
        // mock privatni metody pomoci powermocku
        when(mock, "getInfoAndVersion").thenReturn(" mockovany private .");
        String s = mock.processing();
        System.out.println("  ret = " + s);
    }
}