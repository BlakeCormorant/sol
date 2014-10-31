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
    private MPPacket packet;
    ObjectOutputStream oout;

    public void Connect() {
        packet = new MPPacket((byte)0x1, (byte)0x2, 3.4);

        String data = "This is my test string";
        try {
            System.out.print("Server waiting for connection...\n");

            srvr = new ServerSocket(20596);
            skt = srvr.accept();
            System.out.print("Server has connected!\n");

            oout = new ObjectOutputStream(skt.getOutputStream());

            System.out.println("ObjectOutputStream created...");

            // write something in the file
            oout.writeObject(packet);
            System.out.println("Flushing...");

            oout.flush();


        }
        catch(Exception e) {
            System.out.println("Server failed to initialise: " + e.getMessage());
        }
    }

    public void Update(double day){
        // TODO: If we're the server then about every second we should send a time update.
        // This could be done just by looking at the game time and updating on a modulus...
        double dayMod = day%10.0;
        //System.out.printf("dayMod: %f", dayMod);
        if(dayMod < 0.1){
            System.out.printf("Server sending time update %f...\n", day);
            packet.value = day;
            try {
                oout.writeObject(packet);
            }
            catch(Exception e) {
                System.out.println("Server failed to send update: " + e.getMessage());
            }
        }
    }

    public void Disconnect(){
        try{
            System.out.println("Server Disconnecting...");
            oout.close();
            System.out.println("Output Stream Closed...");
            skt.close();
            System.out.println("Socket Closed...");
            srvr.close();
            System.out.println("Server Closed...");
        }
        catch(Exception e) {
            System.out.println("Server failed to disconnect: " + e.getMessage());
        }

    }

}
