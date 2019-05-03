package Service;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    ServerSocket server;
    Service srv;
    public static Observer<Socket> clientiActivi= new Observer<Socket>();
    public Server() throws IOException {
        server=new ServerSocket(6666);
        srv=new Service();
    }
    public void startServer(){
        try {
            while(true){
                System.out.println("Waiting..");
                Socket client = server.accept();
                System.out.println("client found..");
                Networking networking = new Networking(client, srv);
                Thread t1 = new Thread(networking);
                t1.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(server!=null){
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
