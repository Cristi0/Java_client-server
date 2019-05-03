package Repository;


import Domain.Participant;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class RepoSqlParticipant extends RepoDataBase<Integer, Participant>  implements RepositoryParticipant<Integer, Participant> {

    public RepoSqlParticipant() {
        super.table_name=proprierties().getProperty("jdbc.table.participant");
    }

    @Override
    protected String addValues(Participant element) {
        return "('"+element.getName()+"', "+element.getCapacitateMotor()+", "+element.getIdEchipa()+")";
    }

    @Override
    protected String addNrFields() {
        return "(Nume, CapacitateMotor, idEchipa)";
    }

    @Override
    protected String updCol(Participant element) {
        return "Nume="+element.getName()+", CapacitateMotor="+element.getCapacitateMotor().toString()+", idEchipa="+element.getIdEchipa().toString();
    }


    @Override
    public Participant getFrom(Integer id) {
        Participant p=null;
        Connection conn=null;
        try {
            Class.forName(super.driver);
            conn= DriverManager.getConnection(super.connection ,super.name,super.psw);
            Statement statement=conn.createStatement();

            ResultSet rs=statement.executeQuery("select * from participant where id="+id.toString());

            p=new Participant(
                rs.getInt("id"),
                rs.getString("Nume"),
                rs.getInt("CapacitateMotor"),
                rs.getInt("idEchipa"));

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
    public Map<Integer, Participant> getAll() {
        return getAll("");
    }


    public Map<Integer, Participant> getAll(String condition) {
        Map<Integer,Participant> map=new TreeMap<Integer,Participant>();
        Connection conn=null;
        try {
            Class.forName(super.driver);
            conn= DriverManager.getConnection(super.connection ,super.name,super.psw);
            Statement statement=conn.createStatement();

            ResultSet rs=statement.executeQuery("select * from "+super.table_name+" "+condition);
            while(rs.next()){
                Participant p=new Participant(
                        rs.getInt("id"),
                        rs.getString("Nume"),
                        rs.getInt("CapacitateMotor"),
                        rs.getInt("idEchipa"));
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
