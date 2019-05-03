package Controller.fxml;

import Controller.Service;
import Domain.Curse;
import Domain.Echipa;
import Domain.Participant;
import Networking.Server;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.ResourceBundle;

public class Office extends UnicastRemoteObject implements Initializable, Serializable {
    
    public TableView table;
    public ComboBox combCapacitateMotor;
    public TableColumn collumName;
    public TableColumn NrParticipanti;

    public ComboBox combEchipa;
    public TableView tableParticipanti;
    public TableColumn<Participant,String> numeParticipant=new TableColumn<>("Nume");
    public TableColumn capacitateMotorParticipant;//=new TableColumn<>("Capacitate");

    public TextField fieldnumeParticipant;
    public TextField echipaParticipant;
    public ComboBox comboCapMotor;


    private Service srv;
    private ObservableList<Curse> curse;
    private ObservableList<String> items;
    private ObservableList<Echipa> echipa;
    private ObservableList<Participant> participants;

    private Thread t1;
    public Office() throws RemoteException {
    }

    public void changeTable(ActionEvent actionEvent) {
        collumName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        int i=Integer.parseInt((String) combCapacitateMotor.getValue());
        curse= FXCollections.observableArrayList(srv.viewAllCurse(i).values());
        table.setItems(curse);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        srv=new Service();
        items = FXCollections.observableArrayList(srv.viewAllCapacitateMotor());
        echipa = FXCollections.observableArrayList(srv.viewAllEchipa());
        participants = FXCollections.observableArrayList(srv.viewAllParticipanti().values());


        combEchipa.setItems(echipa);
        comboCapMotor.setItems(items);
        combCapacitateMotor.setItems(items);
        if(!items.isEmpty()){
            combCapacitateMotor.setValue(items.get(0));
            curse = FXCollections.observableArrayList(srv.viewAllCurse(Integer.parseInt(items.get(0))).values());
            collumName.setCellValueFactory(new PropertyValueFactory<Curse,String>("Name"));
            NrParticipanti.setCellValueFactory(new PropertyValueFactory<Curse,Integer>("NrParticipanti"));
            table.setItems(curse);
        }

        numeParticipant.setCellValueFactory(new PropertyValueFactory<>("Name"));
        capacitateMotorParticipant.setCellValueFactory(new PropertyValueFactory<Participant, Integer>("CapacitateMotor"));
        tableParticipanti.setItems(participants);

        srv.addToNotify();
        server();
    }

    public void changeTableParticipanti(ActionEvent actionEvent) {
        int y= combEchipa.getSelectionModel().getSelectedIndex();
        y++;
        //int x=participants.get(y).getIdEchipa();
        Map<Integer, Participant> map=srv.viewAllParticipanti(y);
       participants=FXCollections.observableArrayList(map.values());
        tableParticipanti.setItems(participants);
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        t1.interrupt();
        srv.logout();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/log.fxml"));
        Scene scene=new Scene(root);
        Stage s=new Stage();
        s.setScene(scene);
        s.show();
        Stage a= (Stage) combEchipa.getScene().getWindow();
        a.close();
    }

    public void adaugaParticipant(ActionEvent actionEvent) {


        Participant p=new Participant(
                0,
                fieldnumeParticipant.getText(),
                Integer.parseInt(String.valueOf(comboCapMotor.getValue())),
                Integer.parseInt(echipaParticipant.getText())
        );
        srv.addParticipant(p);
        changeTableParticipanti(actionEvent);
    }

    private void server(){

        Server server = new Server(srv);
        t1 = new Thread(server);
        t1.start();
        t1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                changeTable(new ActionEvent());
                changeTableParticipanti(new ActionEvent());
                if(e.equals("update"))
                    server();
            }});
        }
}
