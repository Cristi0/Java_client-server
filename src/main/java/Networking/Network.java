package Networking;

import Domain.Curse;
import Domain.Echipa;
import Domain.Participant;
import Domain.User;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.Vector;

public class Network {
    Socket socket;
    static  Socket mainsocket;

    static {
        try {
            mainsocket = new Socket("localhost",6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String host;
    int port;
    public Network() {
        host="localhost";
        port=6666;

    }

    public String sendLoginRequest(User user) {

            socket = mainsocket;

        BufferedReader in=null;
        ObjectOutputStream out=null;
        String mesaj="";
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.flush();
            out.writeInt(5);
            out.flush();
            //out.flush();
            out.writeObject(user);
            out.flush();

            mesaj=in.readLine();
          //  out.writeInt(0);
          //  out.flush();

           // out.close();
           // in.close();
            //out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return mesaj;
    }
    public void sendLogoutRequest(){

        socket = mainsocket;

        ObjectOutputStream out=null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());

            out.flush();
            out.writeInt(0);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Curse> sendAllCurseRequest(Integer capacitateMotor) {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectInputStream in=null;
        ObjectOutputStream out=null;
        Map<Integer, Curse> mesaj=null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());


            out.flush();
            out.writeInt(2);
            out.flush();
            //out.flush();
            out.writeInt(capacitateMotor.intValue());
            out.flush();


            in = new ObjectInputStream(socket.getInputStream());
            mesaj= (Map<Integer, Curse>) in.readObject();

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return mesaj;
    }

    public Vector<String> sendAllCapacitateMotor(){
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectInputStream in=null;
        ObjectOutputStream out=null;
        Vector<String> mesaj=null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());


            out.flush();
            out.writeInt(4);
            out.flush();

            in = new ObjectInputStream(socket.getInputStream());
            mesaj= (Vector<String>) in.readObject();

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return mesaj;
    }

    public Vector<Echipa> sendAllEchipe() {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectInputStream in=null;
        ObjectOutputStream out=null;
        Vector<Echipa> mesaj=null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());

            out.flush();
            out.writeInt(3);
            out.flush();

            in = new ObjectInputStream(socket.getInputStream());
            mesaj= (Vector<Echipa>) in.readObject();

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return mesaj;
    }

    public Map<Integer, Participant> sendAllParticipantiRequest(Integer idEchipa) {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectInputStream in=null;
        ObjectOutputStream out=null;
        Map<Integer, Participant> mesaj=null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());

            out.flush();
            out.writeInt(1);
            out.flush();
            out.writeInt(idEchipa.intValue());
            out.flush();

            in = new ObjectInputStream(socket.getInputStream());
            mesaj= (Map<Integer, Participant>) in.readObject();

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return mesaj;
    }

    public Map<Integer, Participant> sendAllParticipantiRequest() {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectInputStream in=null;
        ObjectOutputStream out=null;
        Map<Integer, Participant> mesaj=null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());

            out.flush();
            out.writeInt(6);
            out.flush();

            in = new ObjectInputStream(socket.getInputStream());
            mesaj= (Map<Integer, Participant>) in.readObject();

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return mesaj;
    }

    public void sendAddRequest(Participant participant) {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectInputStream in=null;
        ObjectOutputStream out=null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());

            out.flush();
            out.writeInt(7);
            out.flush();
            out.writeObject(participant);

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void addToNotify(){
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectInputStream in=null;
        ObjectOutputStream out=null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());

            out.flush();
            out.writeInt(8);
            out.flush();
            mainsocket=socket;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public int getNotify() {

        int val=0;
        try {
            ObjectInputStream in=new ObjectInputStream(mainsocket.getInputStream());
            val=in.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return val;
    }
}
