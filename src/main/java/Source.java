import Domain.Curse;
import Domain.Echipa;
import Domain.Participant;
import Domain.User;
import Repository.RepoSqlCurse;
import Repository.RepoSqlEchipa;
import Repository.RepoSqlLogin;
import Repository.RepoSqlParticipant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Source extends Application {


   /* public int add(int a, int b){
        return a+b;
    }
    public int sub(int a, int b) { return a-b; }*/

    public static void main(String[] args) {
        launch(args);
       /** Properties prop=new Properties();
        try{
            prop.load(new FileInputStream("bd.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
/*
         RepoSqlParticipant participanti =new RepoSqlParticipant();
         RepoSqlEchipa echipe = new RepoSqlEchipa();
         RepoSqlCurse curse= new RepoSqlCurse();
         RepoSqlLogin repoLogin=new RepoSqlLogin();

        for (Participant p:participanti.getAll().values()) {
            System.out.println(p);
        }
        for (Echipa p:echipe.getAll().values()) {
            System.out.println(p);
        }
        for (Curse p:curse.getAll().values()) {
            System.out.println(p);
        }
        for (User p:repoLogin.getAll().values()) {
            System.out.println(p);
        }

*/

       /* Logger log = LogManager.getLogger();

        log.traceEntry();
        System.out.println("Hy people");
        log.traceExit();

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("log.txt"));
            writer.write(log.info());

        } catch (IOException e) {
            e.printStackTrace();
        }*/
       /* Connection conn=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/masini?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" ,"Cristi","Delfinul0");
            Statement statement=conn.createStatement();

            ResultSet rs=statement.executeQuery("select * from masina");
            while(rs.next()){

                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("nume"));
                System.out.println(rs.getInt("cai"));
            }
            rs.close();
            statement.close();


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
        }*/

    }
   /* protected Properties proprierties(){
        Properties prop=new Properties();
        try{
            prop.load(new FileInputStream("bd.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }*/
    @Override
    public void start(Stage primaryStage) throws Exception {
       /* Properties prop=proprierties();
        String driver=prop.getProperty("jdbc.driver");//"com.mysql.cj.jdbc.Driver";
        String connection=prop.getProperty("jdbc.url");//"jdbc:mysql://localhost:3306/laborator1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String name=prop.getProperty("jdbc.user");//"Cristi";
        String psw=prop.getProperty("jdbc.psw");//"Delfinul0";

        Class.forName(driver);
        Connection conn= DriverManager.getConnection(connection ,name,psw);
        conn.close();*/



        Parent root= FXMLLoader.load(getClass().getResource("/fxml/log.fxml"));

        //Parent root=loader.load();
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
