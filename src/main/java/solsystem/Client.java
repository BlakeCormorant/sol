package solsystem;

import java.lang.*;
import java.io.*;
import java.net.*;

/**
 * Created by BlakeCormorant on 21/10/14.
 */
public class Client implements MPUpdater {
    private Socket skt;
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
            packet = new MPPacket((MPPacket)ois.readObject());

            System.out.printf("Packet contains value: %f", packet.value);

            //in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
            //System.out.print("Received string: '");

            //while (!in.ready()) {}
            //System.out.println(in.readLine()); // Read one line and output it

            //System.out.print("'\n");

        }
        catch(Exception e) {
            System.out.println("Client failed to connect: " + e.getMessage());
        }
    }

    public void Update(double day){
        // This happens pretty fast
        // System.out.println("Client Update...");
        // If we're a client then we are waiting to pick up time checks from the server.
        try {
            System.out.println("Client reading...");
            packet = new MPPacket((MPPacket) ois.readObject());
            System.out.println("read it...");
        }
        catch(Exception e){
            System.out.println("Failed to update: " + e.getMessage());
        }
        System.out.printf("Packet contains value: %f", packet.value);
    }

    public void Disconnect(){
        try{
            System.out.println("Client disconnecting...");
            ois.close();
            System.out.println("Object input stream closed...");
            is.close();
            System.out.println("Input stream closed.");
        }
        catch(Exception e) {
            System.out.println("Client failed to disconnect: " + e.getMessage());
        }

    }
}
