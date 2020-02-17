package Core;

import javax.persistence.*;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    private static int APPLICATION_PORT = 8080;
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("Core");

    public static void main(String[] args){
        //getSubs();
        SpringApplication.run(Main.class, args);
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void getSubs() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT c FROM Table_Subs c WHERE c.id IS NOT NULL";
        TypedQuery<Table_Subs> tq = em.createQuery(strQuery, Table_Subs.class);
        List<Table_Subs> subs;
        try {
            subs = tq.getResultList();
            subs.forEach(sub->System.out.println(sub.getserverid()));
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }
}