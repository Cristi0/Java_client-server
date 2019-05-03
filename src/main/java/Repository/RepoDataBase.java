package Repository;

import Domain.Domain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.Map;
import java.util.Properties;

public abstract class RepoDataBase<K,V extends Domain> implements Repository<K,V> {

    protected String driver;
    protected String connection;
    protected String name;
    protected String psw;
    protected String table_name;

    protected Properties proprierties(){
        Properties prop=new Properties();
        try{
            prop.load(new FileInputStream("bd.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    protected void writeLog(Logger log){
       /* BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("log.txt"));
            writer.write(log.info());

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    //Doar pentru mostenire
    protected RepoDataBase() {
        Properties prop=proprierties();

        driver=prop.getProperty("jdbc.driver");//"com.mysql.cj.jdbc.Driver";
        connection=prop.getProperty("jdbc.url");//"jdbc:mysql://localhost:3306/laborator1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        name=prop.getProperty("jdbc.user");//"Cristi";
        psw=prop.getProperty("jdbc.psw");//"Delfinul0";
        this.table_name=null;
    }
    public RepoDataBase(String table_name) {
        Properties prop=proprierties();

        driver=prop.getProperty("jdbc.driver");//"com.mysql.cj.jdbc.Driver";
        connection=prop.getProperty("jdbc.url");//"jdbc:mysql://localhost:3306/laborator1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        name=prop.getProperty("jdbc.user");//"Cristi";
        psw=prop.getProperty("jdbc.psw");//"Delfinul0";
        this.table_name=table_name;
    }

    private int Sqloperation(String op){
        Connection conn=null;
        int val=-3000;  //-3000 cod de succes propriu
        try {
            Class.forName(driver);
            conn= DriverManager.getConnection(connection ,name,psw);
            Statement statement=conn.createStatement();

             val=statement.executeUpdate(op);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return val;
    }

    @Override
    public void add(V element) {
        Logger log = LogManager.getLogger();
        log.traceEntry();

        String nrFields=addNrFields();
        String values=addValues(element);
        Sqloperation("insert into "+table_name+" "+nrFields+ " values "+values);

        log.traceExit();
        writeLog(log);
    }

    @Override
    public void update(V element) {
        Logger log = LogManager.getLogger();
        log.traceEntry();

        String colValues=updCol(element);
        Sqloperation("update "+table_name+" set "+colValues+" where id="+element.getID().toString());

        log.traceExit();
        writeLog(log);
    }

    @Override
    public void remove(K id) {
        Logger log = LogManager.getLogger();
        log.traceEntry();

        Sqloperation("delete from "+table_name+" where id="+id.toString());

        log.traceExit();
        writeLog(log);
    }



    protected abstract String addValues(V element);
    protected abstract String addNrFields();

    abstract protected String updCol(V element);
}
