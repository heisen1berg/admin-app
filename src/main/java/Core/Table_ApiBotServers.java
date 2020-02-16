package Core;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "ApiBotServers")
public class Table_ApiBotServers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServerID", unique = true)
    private int id;

    @Column(name = "ServerURL")
    private int serverURL;

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getURL() {
        return serverURL;
    }

    public void setURL(int serverURL) {
        this.serverURL = serverURL;
    }
}