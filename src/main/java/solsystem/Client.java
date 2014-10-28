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
    private InputStream is;
    private ObjectInputStream ois;
    private MPPacket packet;

    public void Connect() {
        try {
            System.out.println("Trying to connect...");
            skt = new Socket("localhost", 20596);
            System.out.println("Created socket...");

            // create an ObjectInputStream for the file we created before
            is = skt.getInputStream();
            System.out.println("Referenced input stream...");

            ois = new ObjectInputStream(is);
            System.out.println("Created new object input stream...");

            // read and print an object and cast it as string
            //System.out.println("Object as a string: " + (String) ois.readObject());

            // read and print an object and cast it as string

            //packet = new MPPacket((MPPacket)(ois.readObject()));
            packet = new MPPacket();
            System.out.println("Created packet...");
            //packet = (MPPacket)ois.readObject();
            ois.readObject(); // TODO Figure out why this fails

            System.out.println("Assigned packet...");

            System.out.printf("Packet contains value: %f" + packet.value);

            //in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
            //System.out.print("Received string: '");

            //while (!in.ready()) {}
            //System.out.println(in.readLine()); // Read one line and output it

            //System.out.print("'\n");
            //in.close();
        }
        catch(Exception e) {
            System.out.print("Client failed to connect!\n");
        }
    }

    public void Update(double day){
        //System.out.println("Client Update...");
        // If we're a client then we are waiting to pick up time checks from the server.
    }
}
