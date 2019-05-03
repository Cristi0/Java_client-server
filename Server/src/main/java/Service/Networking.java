package Service;

import Domain.Participant;
import Domain.User;

import java.io.*;
import java.net.Socket;

public class Networking implements Runnable {
   // Observer<Socket> clientiActivi; // de adaugat la login si notificarea ii trimiterea la fiecare server pus pe client
    Socket client;
    Service srv;

    public Networking(Socket client,  Service service) {
        this.client=client;

        srv=service;
    }

    @Override
    public void run() {
        selectAction();
        //clientiActivi.notficaAll(a-> System.out.println(a.toString()));
    }
    
    public void selectAction(){
        int comanda=50;
        boolean remove=true;
        try {
            ObjectInputStream in= new ObjectInputStream(client.getInputStream());
            comanda= in.readInt();     //citeste ce comanda se doreste pentru a fi executata
            switch(comanda){
                case 0:
                    finishConnection();         //todo done
                    break;
                case 1:
                    getAllParticipanti(in);
                    break;
                case 2:
                    getAllCurse(in);             //todo done
                    break;
                case 3:
                    getAllEchipe();             //todo done
                    break;
                case 4:
                    getAllCapacitateMotor();    //todo done
                    break;
                case 5:
                    login(in);                  //todo done
                    break;
                case 6:
                    getAllParticipanti();       //todo done
                    break;
                case 7:
                    addPersone(in);       //todo done
                    break;
                case 8:
                    addToNotify();
                    remove=false;
            }
        } catch (IOException e) {
            System.out.println("!!!!!!!!!!!! Nu s-a putut face legatura la inputStream pe clientul "+client.toString());
            e.printStackTrace();
        } finally {
            if(remove)
                finishConnection();
        }
    }

    private void addToNotify() {
        synchronized (Server.clientiActivi){
            Server.clientiActivi.add(client);
        }
    }

    private void addPersone(ObjectInputStream in) {
        try {

            Participant participant=(Participant) in.readObject();
            srv.addParticipant(participant);

            Server.clientiActivi.notficaAll(a->{
                try {
                    ObjectOutputStream out= new ObjectOutputStream(a.getOutputStream());
                    out.writeInt(30);
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getAllCapacitateMotor() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

            out.writeObject(srv.viewAllCapacitateMotor());
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login(ObjectInputStream in) {
        try {
            /*synchronized (Server.clientiActivi){
                Server.clientiActivi.add(client);
            }*/
            PrintWriter out = new PrintWriter(client.getOutputStream());
            User user=(User) in.readObject();

            out.println(srv.login(user));
            out.flush();

          //  System.out.println(in.readLine());

          //  out.close();
         //   in.close();

          //  in=new ObjectInputStream(client.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getAllCurse(ObjectInputStream in) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

            out.writeObject(srv.viewAllCurse(in.readInt()));
            out.flush();

           // System.out.println(in.readLine());

           // out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getAllParticipanti(ObjectInputStream in) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

            out.writeObject(srv.viewAllParticipanti(in.readInt()));
            out.flush();

            // System.out.println(in.readLine());

            // out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void getAllParticipanti() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

            out.writeObject(srv.viewAllParticipanti());
            out.flush();

            // System.out.println(in.readLine());

            // out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getAllEchipe() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

            out.writeObject(srv.viewAllEchipa());
            out.flush();

            // System.out.println(in.readLine());

            // out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void finishConnection() {
        synchronized (Server.clientiActivi){
            Server.clientiActivi.rmv(client);
        }
        try {
            if(client.isConnected())
                client.close();
        } catch (IOException e) {
            System.out.println("!!!!!!!!!! Nu s-a putut inchide conexiunea cu clientul "+client.toString());
            e.printStackTrace();
        }
    }
}
