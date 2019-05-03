package Networking;

import Controller.Service;
import Except.Except;

import java.net.SocketException;


public class Server implements Runnable{

    Service srv;
    public Server(Service srv)  {
        this.srv=srv;
    }

    @Override
    public void run() {
        try {
            while (true) {

                Thread.sleep(5000);
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                if (srv.getNotify() == 30) {
                    throw new Except("update");
                }

            }
        }catch (InterruptedException e){
            System.out.println("exit");
            Thread.currentThread().stop();
        }

    }
}
