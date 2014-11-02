package solsystem;

import java.io.IOException;

/**
 * Created by BlakeCormorant on 26/10/14.
 * TODO Do I want to make this cloneable?
 */
public class MPPacket implements java.io.Serializable{
    /*
    static public enum PacketType{
        TIME_UPDATE (0),
        SHIP_POS_UPDATE (1),
        NUM_PACKET_TYPES (2);
        public int ptype;

        PacketType(int ptype){
            this.ptype = ptype;
        }

        //private int ptype() { return ptype; }
    }
    */

    static final public int TIME_UPDATE = 0;
    static final public int SHIP_POS_UPDATE = 1;

    public int type;
    public int ref;
    public double value;
    public double value2;


    public MPPacket(MPPacket mpp){
        this.type = mpp.type;
        this.ref = mpp.ref;
        this.value = mpp.value;
        this.value2 = mpp.value2;
    }

    public MPPacket(int type, int ref, double value){
        this.type = type;
        this.ref = ref;
        this.value = value;
        this.value2 = value2;
    }
    /*
    public MPPacket(){
        this.type = 0;
        this.ref = 0;
        this.value = 0.0;
    }
    */
    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        //stream.writeObject(type);
        stream.writeInt(type);
        stream.writeInt(ref);
        stream.writeDouble(value);
        stream.writeDouble(value2);
        //stream.write(type);
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        //type = (String) stream.readObject();
        this.type = stream.readInt();
        this.ref = stream.readInt();
        this.value = stream.readDouble();
        this.value2 = stream.readDouble();
    }

    @Override
    public String toString() {
        return "MPPacket{" +
                "type=" + type +
                ", ref=" + ref +
                ", value=" + value +
                ", value2=" + value2 +
                '}';
    }
}
