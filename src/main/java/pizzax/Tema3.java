package pizzax;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pizzax.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Tema3 {
    public static void main(String[] args) throws IOException {
      //  ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-concurs.xml");
      //  Pizza p= factory.getBean(Pizza.class);
        System.out.println("start connection ");
        Socket socket = new Socket("localhost", 6666);
        BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("connectioon establish\n");
        String s=in.readLine();
        System.out.println(s);
        System.out.println("closing..");
    }

}
