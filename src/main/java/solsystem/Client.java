package solsystem;

import java.lang.*;
import java.io.*;
import java.net.*;

/**
 * Created by BlakeCormorant on 21/10/14.
 */
public class Client implements MPUpdater {
    private Socket skt;
    private BufferedReader in;

    public void Connect() {
        try {
            System.out.println("Trying to connect...");
            skt = new Socket("localhost", 20596);
            in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
            System.out.print("Received string: '");

            while (!in.ready()) {}
            System.out.println(in.readLine()); // Read one line and output it

            System.out.print("'\n");
            in.close();
        }
        catch(Exception e) {
            System.out.print("Client failed to connect!\n");
        }
    }

    public void Update(){
        //System.out.println("Client Update...");
    }
}
