package Controller.fxml;

import Controller.Service;
import Domain.User;
import Except.FXException;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;


public class Log extends UnicastRemoteObject implements Initializable, Serializable {
    public TextField nume;
    public javafx.scene.control.Button butlogin;
    public PasswordField parola;

    Service srv;

    public Log() throws RemoteException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        srv=new Service();

    }
    private void newWindow(String nameFxmlFile) throws IOException {

        Parent root= FXMLLoader.load(getClass().getResource("/fxml/"+nameFxmlFile+".fxml"));
        Scene scene=new Scene(root);
        Stage s=new Stage();
        s.setScene(scene);
        s.show();
    }

    public void login() throws IOException {
        User u=new User(0,nume.getText(),parola.getText(),"");
        String tip=srv.login(u);
        boolean err= false;
        switch (tip){
            case "admin":
                newWindow("admin");
                break;
            case "user":
                newWindow("user");
                break;
            case "office":
                newWindow("office");
                break;
            default:
            err=true;
            new FXException(Alert.AlertType.ERROR,"Login", "Cont inexistent sau parola incorecta!");

        }
        if(!err){
            Stage a= (Stage) nume.getScene().getWindow();
            a.close();
        }
    }
}
