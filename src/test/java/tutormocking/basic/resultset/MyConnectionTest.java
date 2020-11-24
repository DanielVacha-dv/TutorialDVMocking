package tutormocking.basic.resultset;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;


/**
 * The type My connection test.
 */
public class MyConnectionTest {
    /**
     * The Actual result row list of map.
     */
// aktualni list of map pro jeden executeQuery, ten muze mit vicero zaznamu
    List<Map> actualResultRowListOfMap = new ArrayList<>();
    /**
     * The Statement list.
     */
// list  vsechny  executeQuery s jednotlivymi resultRowListOfMap
    List<List> statementList = new ArrayList<>();
    /**
     * The Usedresult 1 rows.
     */
//    //prave uzivany resultset
    MockRowRS usedresult1Rows = new MockRowRS();
    /**
     * The Idx.
     */
// atomicky citac zaznamu v resultset
    AtomicInteger idx = new AtomicInteger(0);

    /**
     * The Rule.
     */
//    @Rule
//    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private ResultSet resultSet;

    @Mock
    private Statement statement;

    @InjectMocks
    @Spy
    private MyConnection myConnection;


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
        myConnection.setRs(resultSet);
        myConnection.setStatement(statement);
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
        doAnswer(new Answer<ResultSet>() {
            @Override
            public ResultSet answer(InvocationOnMock invocation) throws Throwable {
                int index = idStatements.getAndIncrement();
                actualResultRowListOfMap = statementList.get(index);
                idx = new AtomicInteger(0);
                return resultSet;
            }
        }).when(statement).executeQuery(anyString());
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
        doAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String idx = (String) args[0];
                return resultRows.getColumn(idx);
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
    @Test
    public void testMain() throws SQLException {
        beforeMethod();
        boolean result = myConnection.doAction();
        org.junit.jupiter.api.Assertions.assertEquals(true,result);
    }
}