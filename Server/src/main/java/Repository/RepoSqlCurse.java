package Repository;

import Domain.Curse;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class RepoSqlCurse extends RepoDataBase<Integer, Curse> implements RepositoryCurse<Integer, Curse> {
    public RepoSqlCurse() {
        super.table_name=proprierties().getProperty("jdbc.table.curse");
    }

    @Override
    protected String addValues(Curse element) {
        return "("+element.getName()+", "+element.getCapacitateMotor()+")";
    }

    @Override
    protected String addNrFields() {
        return "(Nume, CapacitateMotor)";
    }

    @Override
    protected String updCol(Curse element) {
        return "Nume="+element.getName()+", CapacitateMotor="+element.getCapacitateMotor();
    }

    @Override
    public Curse getFrom(Integer id) {
        Curse p=null;
        Connection conn=null;
        try {
            Class.forName(super.driver);
            conn= DriverManager.getConnection(super.connection ,super.name,super.psw);
            Statement statement=conn.createStatement();

            ResultSet rs=statement.executeQuery("select * from participant where id="+id.toString());
            ResultSet quantity=statement.executeQuery("select count(*) as cantitate from echipe  inner join participant on echipe.id=idEchipa  where idCursa=" + id.toString());
            p=new Curse(
                    rs.getInt("id"),
                    rs.getString("Nume"),
                    quantity.getInt("cantitate")
                    , rs.getInt("CapacitateMotor"));

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
    public Map<Integer, Curse> getAll() {
        return getAll("");
    }
    public Map<Integer,Curse> getAll(String condition){
        Map<Integer,Curse> map=new TreeMap();
        Connection conn=null;
        try {
            Class.forName(super.driver);
            conn= DriverManager.getConnection(super.connection ,super.name,super.psw);
            Statement statement=conn.createStatement();
            Statement statement2=conn.createStatement();

            ResultSet rs=statement.executeQuery("select * from "+super.table_name+" "+condition);

            while(rs.next()) {
                int i=rs.getInt("id");
                ResultSet quantity=statement2.executeQuery("select count(*) as cantitate from echipe  inner join participant on echipe.id=idEchipa  where idCursa=" + i);
                quantity.next();
                Curse p = new Curse(
                        i,
                        rs.getString("Nume"),
                        quantity.getInt("cantitate"),
                        rs.getInt("CapacitateMotor"));
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
