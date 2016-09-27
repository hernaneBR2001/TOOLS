package br.com.dbatools.domain;
import java.net.Socket;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.nio.charset.Charset;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DBAToolsConnector{
    private Socket socket;
    private BufferedOutputStream oStream;
    private Gson gson;

    private String hostIP;
    private int port;

    public static void main(String[] args){
        DBAToolsConnector app = new DBAToolsConnector(args);
    }

    public DBAToolsConnector(String[] args){
        gson = new GsonBuilder().create();
        try{
            socket = new Socket("localhost",9090);
            System.out.println("Conectado");
            System.out.println("Default Charset=" + Charset.defaultCharset());
            oStream = new BufferedOutputStream(socket.getOutputStream()); //, Charset.forName("UTF-8"));
            oStream.write(gson.toJson(createCommand(args[0], args[1])).getBytes());
            oStream.write("\n".getBytes());
            oStream.flush();
            Thread.sleep(3000); 
            //oStream.write("QUIT\n");
            //oStream.flush();
            oStream.close();
            socket.close(); 
        }
        catch(Exception io){
            io.printStackTrace();
        }
    }

    public DBAToolsConnector(String hostIP, int port){
        gson = new GsonBuilder().create();
        this.hostIP = hostIP;
        this.port = port;
    }



    public void sendCommand(String executable, String params){
        
        try{
            socket = new Socket(hostIP,port);
            System.out.println("Conectado");
            System.out.println("Default Charset=" + Charset.defaultCharset());
            oStream = new BufferedOutputStream(socket.getOutputStream()); //, Charset.forName("UTF-8"));
            oStream.write(gson.toJson(createCommand(executable, params)).getBytes());
            oStream.write("\n".getBytes());
            oStream.flush();

            //oStream.write("QUIT\n");
            //oStream.flush();
            oStream.close();
            socket.close(); 
        }
        catch(Exception io){
            io.printStackTrace();
        }
    }

    private DBAToolsCommand createCommand(String executable, String params){
        DBAToolsCommand command = new DBAToolsCommand(executable,params);
        return command;
    }
}