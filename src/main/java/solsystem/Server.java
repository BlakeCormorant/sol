package solsystem;

import java.lang.*;
import java.io.*;
import java.net.*;

/**
 * Created by BlakeCormorant on 21/10/14.
 */
public class Server implements MPUpdater {
    private ServerSocket srvr;
    private Socket skt;
    private PrintWriter out;

    public void Connect() {
        String data = "This is my test string";
        try {
            System.out.print("Server waiting for connection...\n");
            srvr = new ServerSocket(20596);
            skt = srvr.accept();
            System.out.print("Server has connected!\n");
            out = new PrintWriter(skt.getOutputStream(), true);
            System.out.print("Sending string: '" + data + "'\n");
            out.print(data);
            out.close();
            skt.close();
            srvr.close();
        }
        catch(Exception e) {
            System.out.print("Server failed to initialise!\n");
        }
    }

    public void Update(){
        //System.out.println("Server Update...");
    }

}
