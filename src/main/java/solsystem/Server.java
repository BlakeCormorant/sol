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
    //private PrintWriter out;
    private MPPacket packet;
    //private FileOutputStream fos;
    ObjectOutputStream oout;

    public void Connect() {
        packet = new MPPacket((byte)0x1, (byte)0x2, 3.4);

        String data = "This is my test string";
        try {
            System.out.print("Server waiting for connection...\n");
            srvr = new ServerSocket(20596);
            skt = srvr.accept();
            System.out.print("Server has connected!\n");



            // create a new file with an ObjectOutputStream
            //fos = new FileOutputStream("test.txt");
            oout = new ObjectOutputStream(skt.getOutputStream());

            System.out.println("ObjectOutputStream created...");

            // write something in the file
            oout.writeObject(packet);
            System.out.println("Flushing...");

            oout.flush();

            oout.close();


            //out = new PrintWriter(skt.getOutputStream(), true);
            //System.out.print("Sending string: '" + data + "'\n");
            //out.print(data);
            //out.close();
            skt.close();
            srvr.close();
        }
        catch(Exception e) {
            System.out.print("Server failed to initialise!\n");
        }
    }

    public void Update(double day){
        // TODO: If we're the server then about every second we should send a time update.
        // This could be done just by looking at the game time and updating on a modulus...
        double dayMod = day%10.0;
        //System.out.printf("dayMod: %f", dayMod);
        if(dayMod < 0.1){
            System.out.printf("Server sending time update %f...\n", day);
        }
    }

}
