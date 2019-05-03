package Repository;

import Domain.User;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class RepoSqlLogin extends RepoDataBase<Integer, User> implements RepositoryLogin<Integer, User>{
    public RepoSqlLogin() {
        super.table_name=proprierties().getProperty("jdbc.table.users");
    }

    @Override
    protected String addValues(User element) {
        return "("+element.getName()+", "+element.getParola()+", "+element.getTip()+")";
    }

    @Override
    protected String addNrFields() {
        return "(nume, parola, tip)";
    }

    @Override
    protected String updCol(User element) {
        return "nume="+element.getName()+" parola="+element.getParola()+" tip="+element.getTip();
    }

    @Override
    public User getFrom(Integer id) {
        User p=null;
        Connection conn=null;
        try {
            Class.forName(super.driver);
            conn= DriverManager.getConnection(super.connection ,super.name,super.psw);
            Statement statement=conn.createStatement();

            ResultSet rs=statement.executeQuery("select * from participant where id="+id.toString());

            p=new User(
                    rs.getInt("id"),
                    rs.getString("nume"),
                    rs.getString("parola"),
                    rs.getString("tip"));

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
        }
        return p;
    }

    @Override
    public Map<Integer, User> getAll() {
        return getAll("");
    }
    private Map<Integer, User> getAll(String condition) {
        Map<Integer,User> map=new TreeMap();
        Connection conn=null;
        ResultSet rs=null;
        Statement statement=null;
        try {
            Class.forName(super.driver);
            conn= DriverManager.getConnection(super.connection ,super.name,super.psw);
            statement=conn.createStatement();

            rs=statement.executeQuery("select * from "+super.table_name+" "+condition);

            while(rs.next()) {
                User p = new User(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("parola"),
                        rs.getString("tip"));
                map.put(p.getID(),p);
            }

            statement.close();
            rs.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return map;
    }


    @Override
    public String getType(User user) {
            String tip="";
            Map<Integer, User> map=getAll("where nume='"+user.getName()+"' and parola='"+user.getParola()+"'");//+" and parola="+parola);
            for (User usr:map.values()) {
                tip=usr.getTip();
                return tip;
            }
        return tip;
    }
}
