package Core;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Subs")
public class Table_Subs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SubID", unique = true)
    private int id;

    //@ManyToOne(targetEntity = Table_ApiBotServers.class)
    //@JoinColumn(name= "ServerID",referencedColumnName ="ServerID")
    @Column(name = "ServerID", nullable=false)
    private int serverid;

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public int getserverid() {
        return serverid;
    }

    public void setserverid(int serverid) {
        this.serverid = serverid;
    }
}
