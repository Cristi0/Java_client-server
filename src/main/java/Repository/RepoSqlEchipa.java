package Repository;

import Domain.Echipa;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class RepoSqlEchipa extends RepoDataBase<Integer, Echipa> implements RepositoryEchipa<Integer, Echipa> {
    public RepoSqlEchipa() {
        super.table_name=proprierties().getProperty("jdbc.table.echipe");
    }

    @Override
    protected String addValues(Echipa element) {
        return "("+element.getName()+", "+element.getIdCursa()+")";
    }

    @Override
    protected String addNrFields() {
        return "(Nume, idCursa)";
    }

    @Override
    protected String updCol(Echipa element) {
        return "Nume="+element.getName()+" idCursa="+element.getIdCursa();
    }

    @Override
    public Echipa getFrom(Integer id) {
        Echipa ec=null;
        Connection conn=null;
        try {
            Class.forName(super.driver);
            conn= DriverManager.getConnection(super.connection ,super.name,super.psw);
            Statement statement=conn.createStatement();

            ResultSet rs=statement.executeQuery("select * from participant where id="+id.toString());

            ec=new Echipa(
                    rs.getInt("id"),
                    rs.getString("Nume"),
                    rs.getInt("idCursa"));

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
        return ec;
    }

    @Override
    public Map<Integer, Echipa> getAll() {
        return getAll("");
    }

    public Map<Integer, Echipa> getAll(String condition) {
        Map<Integer,Echipa> map=new TreeMap<Integer,Echipa>();
        Connection conn=null;
        try {
            Class.forName(super.driver);
            conn= DriverManager.getConnection(super.connection ,super.name,super.psw);
            Statement statement=conn.createStatement();

            ResultSet rs=statement.executeQuery("select * from "+super.table_name+" "+condition);
            while(rs.next()){
                Echipa p=new Echipa(
                        rs.getInt("id"),
                        rs.getString("Nume"),
                        rs.getInt("idCursa"));
                map.put(p.getID(),p);
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
        }
        return map;
    }
}
