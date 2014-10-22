package solsystem;

import java.lang.*;
import java.io.*;
import java.net.*;

/**
 * Created by BlakeCormorant on 21/10/14.
 */
public class Server {
    public static void main(String args[]) {
        String data = "This is my test string";
        try {
            ServerSocket srvr = new ServerSocket(1234);
            Socket skt = srvr.accept();
            System.out.print("Server has connected!\n");
            PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
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
}