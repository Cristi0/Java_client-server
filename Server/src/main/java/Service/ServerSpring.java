package Service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ServerSpring {

   // public static Observer<Socket> clientiActivi= new Observer<Socket>();
    public void startServer(){
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:springServer.xml");
        //((ClassPathXmlApplicationContext) factory).start();
    }
}
