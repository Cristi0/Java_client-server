import Domain.User;
import Service.*;
import Service.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ServerSource {

    public static void main(String[] args) throws IOException {
        Service srv=new Service();
        //System.out.println(srv.login(new User(0,"admin","admin","")));
        System.out.println("Hello");
        System.out.println("Start server..");
        Server n=new Server();
        n.startServer();
        System.out.println("closed");
    }
}
