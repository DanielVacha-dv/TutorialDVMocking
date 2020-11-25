package tutormocking.basic.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MyConnection {

    private static final Logger LOGGER=Logger.getLogger(MyConnection.class.getName());
    private   ResultSet rs;

    public Statement statement;

   boolean   doAction() {
        try {
            LOGGER.info("exicuted query 1");
            statement.executeQuery("kuku");
            boolean next = rs.next();
            String string2 = rs.getString("prvniklic11");
            LOGGER.info("prvni zaznam="+string2);
            LOGGER.info("pokus o next neni druhy zaznam ="+rs.next());
            statement.executeQuery("kuku");
            LOGGER.info("exicuted query 2 =");
            LOGGER.info("cteni 1. zaznam v druhem query ="+rs.next());
            LOGGER.info("cteni 1. zaznam-klic v druhem query ="+rs.getString("klic11"));
            LOGGER.info("cteni 2. zaznam v druh0m query ="+rs.next());
            LOGGER.info("cteni 2. zaznam-klic v druhem query ="+rs.getString("klic21"));
            return true;
        } catch (SQLException  e){
            LOGGER.warning("to neni hezke");
        } catch (Exception e) {
            LOGGER.warning("to ne Fuj " +e.getMessage());
        }
       return false;
    }
    ResultSet getResultSet() {
        try {
            rs=statement.executeQuery("");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }


    public static void main(String[] args) throws Exception {
//        ResultsetClass rs=new ResultsetClass();
//        rs.doAction();
        List<String> s=new ArrayList<>();
        System.out.println("s ="+s.size() );
        s.add("pipi");
        System.out.println("s ="+s.size() );
    }
}
