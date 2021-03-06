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
    private Thread receiverThread;

    private boolean gameDayUpdated = false;
    private double lastGameDayUpdate = 0.0;

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

            packet = new MPPacket((MPPacket)ois.readObject());

            System.out.printf("Packet contains value: %f", packet.value);

        }
        catch(Exception e) {
            System.out.println("Client failed to connect: " + e.getMessage());
        }

        // This is where we will kick off the new thread
        receiverThread = new Thread() {
            @Override
            public void run(){
                Receiver();
            }
        };
        receiverThread.start();
    }


    private void Receiver() {
        while (true) {
            try {
                System.out.println("Client reading...");
                //packet = new MPPacket((MPPacket) ois.readObject());
                packet = (MPPacket) ois.readObject();

                //System.out.println("read it...");
            }
            catch (Exception e) {
                System.out.println("Failed to update: " + e.getMessage());
            }

            System.out.printf("Packet contains value: %f\n", packet.value);

            // TODO Take action here depending upon what's in the packet

            switch(packet.type){
                case MPPacket.TIME_UPDATE:
                    lastGameDayUpdate = packet.value;
                    gameDayUpdated = true;
                case MPPacket.SHIP_POS_UPDATE:
                    UpdateShipPos(packet.ref, packet.value);
            }
        }
    }

    public void Update(double day, Game g){
        // TODO This update routine could do what it needs to do with the data that has been received,
        // TODO but that has been received in some thread running elsewhere, prob in this class.
        // This happens pretty fast
        // System.out.println("Client Update...");
        // If we're a client then we are waiting to pick up time checks from the server.

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

    private void UpdateShipPos(int ref, double pos){

    }

    // Perhaps a better framework for game updates:
    public double GetGameDayUpdate(double day){
        if(gameDayUpdated){
            gameDayUpdated = false;
            return lastGameDayUpdate;
        }
        return day;
    }
}
