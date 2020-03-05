package Core.CachePackage;

import org.springframework.stereotype.Component;
import java.io.*;

@Component
public class Serializer {

    private static final String PREFIX = "/cache/";

    public void serialize(String key, Serializable ser) {
        try {
            FileOutputStream fos = new FileOutputStream(PREFIX + ser.getClass().toString()+key);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(ser);
            out.close();
        } catch (IOException ex) {
        }
    }

    public Serializable deserialize(Long key) {
        Serializable ser = null;
        try {
            FileInputStream fis = new FileInputStream(PREFIX + ser.getClass().toString() + key);
            ObjectInputStream in = new ObjectInputStream(fis);
            ser = (Serializable) in.readObject();
            in.close();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        return ser;
    }
}
