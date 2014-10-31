package solsystem;

import java.io.IOException;

/**
 * Created by BlakeCormorant on 26/10/14.
 * TODO Do I want to make this cloneable?
 */
public class MPPacket implements java.io.Serializable{
    public byte type;
    public byte ref;
    public double value;

    public MPPacket(MPPacket mpp){
        this.type = mpp.type;
        this.ref = mpp.ref;
        this.value = mpp.value;
    }

    public MPPacket(byte type, byte ref, double value){
        this.type = type;
        this.ref = ref;
        this.value = value;
    }

    public MPPacket(){
        this.type = 0;
        this.ref = 0;
        this.value = 0.0;
    }

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        //stream.writeObject(type);
        stream.writeByte(type);
        stream.writeByte(ref);
        stream.writeDouble(value);
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        //type = (String) stream.readObject();
        this.type = stream.readByte();
        this.ref = stream.readByte();
        this.value = stream.readDouble();
    }

    @Override
    public String toString() {
        return "MPPacket{" +
                "type=" + type +
                ", ref=" + ref +
                ", value=" + value +
                '}';
    }
}
