package tutormocking.basic.resultset;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;


/**
 * The type My connection test.
 */
public class MyConnectionTest {


    private static Logger LOGGER = Logger.getLogger(MyConnectionTest.class.getName());
    /**
     * The Actual result row list of map.
     */
// aktualni list of map pro jeden executeQuery, ten muze mit vicero zaznamu
    List<Map> actualResultRowListOfMap = new ArrayList<>();
    /**
     * The Statement list.
     */
    // list  vsechny  executeQuery s jednotlivymi resultRowListOfMap
    // da se rict ze je to tabulka  kde jeden zaznam obsahuje dalsi tabulku
    List<List> statementList = new ArrayList<>();
    /**
     * The Usedresult 1 rows.
     */
//    prave uzivany resultset
//    MockRowRS usedresult1Rows = new MockRowRS();
    /**
     * The Idx.
     */
// atomicky citac zaznamu v resultset
    AtomicInteger idx = new AtomicInteger(0);

    @Mock
    private ResultSet resultSet;

    @Mock
    private Statement statement;

    @InjectMocks
    @Spy
    private MyConnection myConnection;

    @BeforeAll
    static void initAll() {
        LOGGER.info("volani initAll()");
    }

    /**
     * vygeneruje data pro resultset s 1 radkem
     *
     * @return the list
     */
    List<Map> initFirstRowsRS() {
        List<Map> resultRowListOfMapLoc = new ArrayList<>();
        Map<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("prvniklic11", "value11");
        hashMap2.put("prvniklic12", "value12");
        resultRowListOfMapLoc.add(hashMap2);
        return resultRowListOfMapLoc;
    }

    /**
     * vygeneruje data pro resultset s 2radky
     *
     * @return the list
     */
    List<Map> init2RowsRS() {
        List<Map> resultRowListOfMapLoc = new ArrayList<>();
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("klic11", "value11");
        hashMap.put("klic12", "value12");
        resultRowListOfMapLoc.add(hashMap);
        Map<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("klic21", "value21");
        hashMap2.put("klic22", "value22");
        resultRowListOfMapLoc.add(hashMap2);
        return resultRowListOfMapLoc;
    }

    /**
     * nepouziva se jupiter api  pri pouziti testu vzika chyba
     */
    @BeforeEach
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
        statementList = new ArrayList<>(Arrays.asList(initFirstRowsRS(), init2RowsRS()));
    }


    /**
     * Before method.  nastavi chovani mock pro konkretni test
     *
     * @throws SQLException the sql exception
     */
    public void beforeMethod() throws SQLException {
        idx = new AtomicInteger(0);
        final AtomicInteger idStatements = new AtomicInteger(0);
        MockRowRS resultRows = new MockRowRS();
        // metoda, ktera mockuje, tedy na základě volani metody executeQuery
        // vrati mock objekt resultSet
        doAnswer((i) -> {
            int index = idStatements.getAndIncrement();
            actualResultRowListOfMap = statementList.get(index);
            idx = new AtomicInteger(0);
            return resultSet;
        }).when(statement).executeQuery(anyString());
        // prepina radky tabulky v danem resultSetu
        doAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                int index = idx.getAndIncrement();
                if (actualResultRowListOfMap.size() <= index) {
                    return false;
                }
                Map<String, String> map = actualResultRowListOfMap.get(index);
                resultRows.setCurrentHashMapData(map);
                return true;
            }
        }).when(resultSet).next();
        // pri volani vraci v danem zaznamu hodnotu na zaklade klice
        doAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String key = (String) args[0];
                return resultRows.getColumn(key);
            }
        }).when(resultSet).getString(anyString());
    }

    /**
     * The type Mock row rs.
     */
    static class MockRowRS {
        /**
         * The Hash map.
         */
        Map<String, String> hashMap = new HashMap<>();

        /**
         * Sets current hash map data.
         *
         * @param hashMap the hash map
         */
        public void setCurrentHashMapData(Map<String, String> hashMap) {
            this.hashMap = hashMap;
        }

        /**
         * Gets column.
         *
         * @param key the key
         * @return the column
         */
        public String getColumn(String key) {
            return hashMap.get(key);
        }
    }


    /**
     * Test main.
     *
     * @throws SQLException the sql exception
     */
    @DisplayName("testmain")
    @Test
    public void testMain() throws SQLException {
        beforeMethod();
        org.junit.jupiter.api.Assertions.assertEquals(true, myConnection.doAction());
    }
}